package com.scrypt.sfax.di.module;


import com.scrypt.sfax.sfaxrepomanager.restservice.service.RestfulService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public abstract class NetworkServiceModule {

  @Provides
  @Singleton
  static RestfulService provideRepoService(@Named("api") Retrofit retrofit) {
    return retrofit.create(RestfulService.class);
  }

  @Provides
  @Named("network_scheduler")
  static Scheduler provideNetworkScheduler() {
    return Schedulers.io();
  }

}
