package com.scrypt.sfax.sfaxrepomanager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.scrypt.sfax.data.Authenticate;
import com.scrypt.sfax.data.Fax;
import com.scrypt.sfax.data.FaxNumber;
import com.scrypt.sfax.data.Folder;
import com.scrypt.sfax.repointerface.RepoService;
import com.scrypt.sfax.sfaxrepomanager.cacheservice.CacheDatabaseService;
import com.scrypt.sfax.sfaxrepomanager.cacheservice.number.NumberEntity;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetFoldersResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetNumbersResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.SearchFaxesResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.service.RestfulService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;

@Singleton
public class SFaxRepoManager implements RepoService {
  private final RestfulService restService;
  private final CacheDatabaseService cacheService;
  private final Scheduler scheduler;

  @Inject
  public SFaxRepoManager(RestfulService restfulService,
                     CacheDatabaseService cacheDatabaseService,
                     @Named("network_scheduler") Scheduler scheduler) {
    this.restService = restfulService;
    this.cacheService = cacheDatabaseService;
    this.scheduler = scheduler;
  }

  //////////////////////////////////////////////////////////////////////////////////////
  //  Authenticate
  //

  @Override
  public Single<Authenticate> getAuthentication(String request) {
    return restService.authenticate(request)
            .map(Authenticate::fromAuthenticateResponse)
            .doOnSuccess(response -> {
              Log.d("LOG",response.toString());
            })
            .doOnError(e -> {
              Log.d("LOG", e.toString());
            })
            .subscribeOn(scheduler);
  }


  ////////////////////////////////////////////////////////////////////////////////////////
  // GetNumbers
  //

  @Override
  public Maybe<List<FaxNumber>> getFaxNumbers(String userToken) {

    return Maybe.concat(cacheFaxNumbers(userToken),apiFaxNumbers(userToken))
            .firstElement()
            .subscribeOn(scheduler);
  }

  private Maybe<List<FaxNumber>> cacheFaxNumbers(String userToken) {

    return cacheService.getNumbers()
            // if above getNumbers Maybe has empty(onComplete call without onSuccess),
            // then it will calls onComplete without going to map below.
            .map(numberEntities -> {

              List<FaxNumber> numbers = new ArrayList<>();

              if (!numberEntities.isEmpty()) {
                for (NumberEntity entity : numberEntities) {
                  numbers.add(FaxNumber.fromNumberEntity(entity));
                }
              }
              return numbers;
            });
  }

  private Maybe<List<FaxNumber>> apiFaxNumbers(String userToken) {

    return restService.getNumbers(userToken)

            .map(getNumbersResponse -> {

              List<FaxNumber> numbers = new ArrayList<>(getNumbersResponse.getNumbers().size());

              if (getNumbersResponse.isSuccess()) {

                // clear cache before refresh
                cacheService.deleteAllNumber();

                // prepare fax number list
                for (GetNumbersResponse.NumberResponse res : getNumbersResponse.getNumbers()) {

                  numbers.add(FaxNumber.fromGetNumbersResponse(res));

                  // save to cache
                  cacheService.addNumber(
                          new NumberEntity(res.getId(),
                                  Boolean.getBoolean(res.getActive()),
                                  Boolean.getBoolean(res.getActiveOutbound()),
                                  Boolean.getBoolean(res.getInbound()),
                                  Boolean.getBoolean(res.getOutbound()),
                                  Integer.parseInt(res.getUnread()),
                                  res.getName(),
                                  res.getNumber()));
                }
              }
              return numbers;
            })
            .toMaybe();
  }

  ////////////////////////////////////////////////////////////////////////////////////////
  // GetFolders
  //

  @Override
  public Single<List<Folder>> getFolders(String userToken, String numberId) {

    //return Maybe.concat(cacheFolders(userToken,numberId),apiFolders(userToken,numberId))
    return apiFolders(userToken,numberId)
            //.firstElement()
            .subscribeOn(scheduler);
  }

  private Maybe<List<Folder>> cacheFolders(String userToken, String numberId) {
/*
    return cacheService.getNumbers()
            // if above getNumbers Maybe has empty(onComplete call without onSuccess),
            // then it will calls onComplete without going to map below.
            .map(numberEntities -> {

              List<FaxNumber> numbers = new ArrayList<>();

              if (!numberEntities.isEmpty()) {
                for (NumberEntity entity : numberEntities) {
                  numbers.add(FaxNumber.fromNumberEntity(entity));
                }
              }
              return numbers;
            });
            */
    return null;
  }

  private Single<List<Folder>> apiFolders(String userToken, String numberId) {

    return restService.getFolders(userToken, numberId)

            .map(getFoldersResponse -> {

              List<Folder> folders = new ArrayList<>(getFoldersResponse.getFolders().size());

              if (getFoldersResponse.isSuccess()) {

                // clear cache before refresh
                // cacheService.deleteAllNumber();

                // prepare folder list
                for (GetFoldersResponse.FolderResponse res : getFoldersResponse.getFolders()) {

                  folders.add(Folder.fromFoldersResponse(res));

                  /*
                  // save to cache
                  cacheService.addNumber(
                          new NumberEntity(res.getId(),
                                  Boolean.getBoolean(res.getActive()),
                                  Boolean.getBoolean(res.getActiveOutbound()),
                                  Boolean.getBoolean(res.getInbound()),
                                  Boolean.getBoolean(res.getOutbound()),
                                  Integer.parseInt(res.getUnread()),
                                  res.getName(),
                                  res.getNumber()));
                                  */
                }
              }
              return folders;
            });
            //.toMaybe();
  }


  ////////////////////////////////////////////////////////////////////////////////////////
  // GetFaxList
  //

  @Override
  public Single<List<Fax>> getFaxList(String userToken, String folderId) {

    return apiFaxList(userToken,folderId)
            .subscribeOn(scheduler);
  }

  private Single<List<Fax>> apiFaxList(String userToken,String folderId) {

    return restService.getFaxes(userToken,folderId)

            .map(searchFaxesResponse -> {

              List<Fax> faxes = new ArrayList<>(searchFaxesResponse.getFaxResponses().size());

              // prepare fax number list
              for (SearchFaxesResponse.FaxResponse res : searchFaxesResponse.getFaxResponses()) {

                faxes.add(Fax.fromFaxResponse(res));

              }

              return faxes;

            });
  }


}
