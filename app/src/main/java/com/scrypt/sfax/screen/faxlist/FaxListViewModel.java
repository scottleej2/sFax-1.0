package com.scrypt.sfax.screen.faxlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.scrypt.sfax.Util.Keys;
import com.scrypt.sfax.repointerface.RepoService;
import com.scrypt.sfax.data.Fax;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class FaxListViewModel extends AndroidViewModel {

  private final MutableLiveData<List<Fax>> faxes = new MutableLiveData<>();
  private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
  private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

  private final MutableLiveData<List<Fax>> faxesLiveData = new MutableLiveData<>();

  @Inject RepoService repoServiceManager;

  @Inject
  FaxListViewModel(Context application) {
    super((Application)application);
  }


  LiveData<Boolean> getError() {
    return repoLoadError;
  }

  LiveData<Boolean> getLoading() {
    return loading;
  }

  LiveData<List<Fax>> getFaxesLiveData() {
    return faxesLiveData;
  }


  @Override
  protected void onCleared() {

  }


  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Get Fax list
  //

  public void callGetFaxList(String id) {

    String userToken = getApplication().getSharedPreferences(Keys.Preferences.APP_PREFERENCES, Context.MODE_PRIVATE)
            .getString(Keys.AppPreferenceKeys.USER_TOKEN, null);

    repoServiceManager.getFaxList(userToken, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(e ->onOkCallGetFaxList(e),onErrorCallGetFaxList());


  }

  private void onOkCallGetFaxList(List<Fax> faxList) {
    faxesLiveData.setValue(faxList);

  }

  private Consumer<Throwable> onErrorCallGetFaxList() {
    return throwable -> {
      Log.d("LOG",throwable.toString());

    };
  }




}
