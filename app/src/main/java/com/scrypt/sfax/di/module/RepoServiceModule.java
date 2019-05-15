package com.scrypt.sfax.di.module;


import com.scrypt.sfax.repointerface.RepoService;
import com.scrypt.sfax.sfaxrepomanager.SFaxRepoManager;
import com.scrypt.sfax.sfaxrepomanager.cacheservice.CacheDatabaseService;
import com.scrypt.sfax.sfaxrepomanager.restservice.service.RestfulService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class RepoServiceModule {
  @Provides
  @Singleton
  static RepoService provideRepoService(RestfulService restfulService,
                                        CacheDatabaseService cacheDatabaseService,
                                        @Named("network_scheduler") Scheduler scheduler) {

    // providing proper RepoService module - can be different for each repository(network,cache)
    return new SFaxRepoManager(restfulService, cacheDatabaseService,scheduler);
  }
}
