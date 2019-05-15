package com.scrypt.sfax.base;

import android.app.Application;
import android.content.Context;

import com.scrypt.sfax.di.component.ApplicationComponent;
import com.scrypt.sfax.di.component.DaggerApplicationComponent;
import com.scrypt.sfax.di.module.ApplicationModule;
import com.scrypt.sfax.di.module.NetworkModule;

public class BaseApplication extends Application {

  private ApplicationComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(this))
                .build();
  }

  public static ApplicationComponent getApplicationComponent(Context context) {
    return ((BaseApplication) context.getApplicationContext()).component;
  }

}
