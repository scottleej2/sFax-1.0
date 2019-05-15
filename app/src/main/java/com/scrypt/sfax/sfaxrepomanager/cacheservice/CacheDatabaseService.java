package com.scrypt.sfax.sfaxrepomanager.cacheservice;


import com.scrypt.sfax.sfaxrepomanager.cacheservice.number.NumberEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class CacheDatabaseService {

  private final CacheDatabase cacheDatabase;

  @Inject
  CacheDatabaseService(CacheDatabase cacheDatabase) {
    this.cacheDatabase = cacheDatabase;
  }

  public Maybe<List<NumberEntity>> getNumbers() {

    return Maybe.create( e -> {

      List<NumberEntity> numberEntities = cacheDatabase.NumberDao().getNumber();

      // TODO:  More code Need to check this data from database is still valid, or expired.

      if (!numberEntities.isEmpty()) {
        e.onSuccess(numberEntities);  // Maybe will signal to observer that output is successful
        e.onComplete();
      }

      // if above onSuccess is not called, then observer will receive EMPTY completed signal.
      e.onComplete();  // just signal to observer that process is completed.
    });
  }


  public void addNumber(NumberEntity numberEntity) {
    cacheDatabase.NumberDao().addNumber(numberEntity);
  }

  public void deleteNumber(NumberEntity numberEntity) {
    cacheDatabase.NumberDao().deleteNumber(numberEntity);
  }

  public void deleteAllNumber() {
    cacheDatabase.NumberDao().deleteAllNumber();
  }
}
