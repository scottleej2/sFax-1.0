package com.scrypt.sfax.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.scrypt.sfax.Util.Keys;
import com.scrypt.sfax.base.BaseViewModel;
import com.scrypt.sfax.data.FaxNumber;
import com.scrypt.sfax.data.Folder;
import com.scrypt.sfax.repointerface.RepoService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class AppEntryViewModel extends BaseViewModel {

  private final MutableLiveData<List<FaxNumber>> faxNumbersLiveData = new MutableLiveData<>();
  private final MutableLiveData<List<Folder>> foldersLiveData = new MutableLiveData<>();
  private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

  @Inject RepoService repoServiceManager;

  @Inject
  AppEntryViewModel(Context application) {
    super((Application)application);
  }

  public LiveData<List<FaxNumber>> getFaxNumbersLiveData() {
    return faxNumbersLiveData;
  }
  public LiveData<List<Folder>> getFoldersLiveData() {
    return foldersLiveData;
  }
  public LiveData<Integer> getProgressLiveData() { return progressLiveData; }


  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Get Fax Numbers
  // Maybe<List<FaxNumber>> getFaxNumbers(String userToken);
  public void callGetFaxNumbers() {

    String userToken = getApplication().getSharedPreferences(Keys.Preferences.APP_PREFERENCES, Context.MODE_PRIVATE)
            .getString(Keys.AppPreferenceKeys.USER_TOKEN, null);


    progressLiveData.setValue(20);

    repoServiceManager.getFaxNumbers(userToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onOkCallGetFaxNumbers,this::onErrorCallGetFaxNumbers);

    // this::onOkCallAuthenticate is same as e->onOkCallAuthenticate(e)

  }

  private void onOkCallGetFaxNumbers(List<FaxNumber> faxNumberList) {

    progressLiveData.setValue(70);

    faxNumbersLiveData.setValue(faxNumberList);

    progressLiveData.setValue(100);

  }

  private void onErrorCallGetFaxNumbers(Throwable e) {
    Log.d("LOG",e.toString());

    super.httpErrorExceptionLiveData.setValue((HttpException)e);

    progressLiveData.setValue(100);
  }


  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Get Folders
  //

  public void callGetFolders(String numberId) {

    String userToken = getApplication().getSharedPreferences(Keys.Preferences.APP_PREFERENCES, Context.MODE_PRIVATE)
            .getString(Keys.AppPreferenceKeys.USER_TOKEN, null);

    progressLiveData.setValue(20);

    repoServiceManager.getFolders(userToken, numberId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(e ->onOkCallGetFolders(e),onErrorCallGetFolders());

    progressLiveData.setValue(40);

  }

  private void onOkCallGetFolders(List<Folder> folderList) {

    progressLiveData.setValue(70);

    foldersLiveData.setValue(folderList);

    progressLiveData.setValue(100);

  }

  private Consumer<Throwable> onErrorCallGetFolders() {
    return throwable -> {
      Log.d("LOG",throwable.toString());

      progressLiveData.setValue(100);

    };
  }

}
