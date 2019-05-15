package com.scrypt.sfax.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;


import retrofit2.HttpException;

public abstract class BaseViewModel extends AndroidViewModel {

  protected final MutableLiveData<HttpException> httpErrorExceptionLiveData = new MutableLiveData<>();

  public BaseViewModel(Context application) {
    super((Application)application);
  }

  public LiveData<HttpException> getHttpExceptionLiveData() {
    return httpErrorExceptionLiveData;
  }
}
