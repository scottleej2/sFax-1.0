package com.scrypt.sfax.sfaxrepomanager.cacheservice;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.scrypt.sfax.sfaxrepomanager.cacheservice.number.NumberDao;
import com.scrypt.sfax.sfaxrepomanager.cacheservice.number.NumberEntity;

@Database(entities = {NumberEntity.class}, version = 1)
public abstract class CacheDatabase extends RoomDatabase {

  public abstract NumberDao NumberDao();

}
