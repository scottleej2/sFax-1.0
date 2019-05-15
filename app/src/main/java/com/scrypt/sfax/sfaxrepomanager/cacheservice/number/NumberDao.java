package com.scrypt.sfax.sfaxrepomanager.cacheservice.number;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NumberDao {

  @Query("SELECT * FROM number")
  List<NumberEntity> getNumber();

  @Insert
  void addNumber(NumberEntity number);

  @Delete
  void deleteNumber(NumberEntity number);

  @Query("DELETE FROM number")
  void deleteAllNumber();

}
