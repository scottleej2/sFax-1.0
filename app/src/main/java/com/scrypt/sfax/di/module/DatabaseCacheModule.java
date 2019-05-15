package com.scrypt.sfax.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.scrypt.sfax.sfaxrepomanager.cacheservice.CacheDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseCacheModule {
  @Provides
  @Singleton
  static CacheDatabase provideCacheDatabase(Context context) {
    return Room.databaseBuilder(context, CacheDatabase.class, "dcache").build();
  }
}
