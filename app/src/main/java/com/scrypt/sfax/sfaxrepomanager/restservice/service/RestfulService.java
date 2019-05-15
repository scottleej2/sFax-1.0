package com.scrypt.sfax.sfaxrepomanager.restservice.service;

import com.scrypt.sfax.sfaxrepomanager.restservice.model.AuthenticateResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.BaseResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetFoldersResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetNumbersResponse;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.SearchFaxesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestfulService {

  @GET("authenticate?product=mobile&version=4.0.0&os=Android&type=1")
  Single<AuthenticateResponse> authenticate(@Query("request") String request);

  @GET("requestPin")
  Single<BaseResponse> requestPin(@Query("userToken") String userToken);

  @GET("getNumbers")
  Single<GetNumbersResponse> getNumbers(@Query("userToken") String userToken);

  @GET("faxFolders")
  Single<GetFoldersResponse> getFolders(@Query("userToken") String userToken,
                                        @Query("numberID") String numberId);

  @GET("faxes")
  Single<SearchFaxesResponse> getFaxes(@Query("userToken") String userToken,
                                       @Query("faxFolderID") String faxFolderId
                                       );

}
