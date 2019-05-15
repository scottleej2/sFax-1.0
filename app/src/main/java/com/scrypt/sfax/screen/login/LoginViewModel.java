package com.scrypt.sfax.screen.login;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.scrypt.sfax.Util.Keys;
import com.scrypt.sfax.base.BaseViewModel;
import com.scrypt.sfax.repointerface.RepoService;
import com.scrypt.sfax.data.Authenticate;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.HttpException;

public class LoginViewModel extends BaseViewModel {

  private final MutableLiveData<Authenticate> authLiveData = new MutableLiveData<>();

  @Inject RepoService repoServiceManager;

  @Inject
  LoginViewModel(Context application) {
    super((Application)application);

    //Solution s = new Solution();

    //s.minWindow("ADOBECODEBANC", "ABC");
  }

  public LiveData<Authenticate> getAuthLiveData() {
    return authLiveData;
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Login Authenticate
  //
  public void callAuthenticate(String username, String password, String pin, String extendPin) {

    String request = String.format("username=%s&password=%s&pin=%s&extendPin=%s",
            //(username == null? "" : username),
            //(password == null? "" : password),
            (username == null? "" : "scott@support"),
            (password == null? "" : "qatest789"),

            (pin == null? "" : pin),
            (extendPin == null? "" : extendPin));

    repoServiceManager.getAuthentication(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onOkCallAuthenticate,this::onErrorCallAuthenticate);

    // this::onOkCallAuthenticate is same as e->onOkCallAuthenticate(e)

  }

  private void onOkCallAuthenticate(Authenticate a) {

    if (a.isSuccess()) {
      getApplication().getSharedPreferences(Keys.Preferences.APP_PREFERENCES, Context.MODE_PRIVATE)
              .edit()
              .putString(Keys.AppPreferenceKeys.USER_TOKEN, a.getUserToken())
              .apply();
    }

    authLiveData.setValue(a);
  }

  private void onErrorCallAuthenticate(Throwable e) {
    Log.d("LOG",e.toString());

    super.httpErrorExceptionLiveData.setValue((HttpException)e);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////
  //

}