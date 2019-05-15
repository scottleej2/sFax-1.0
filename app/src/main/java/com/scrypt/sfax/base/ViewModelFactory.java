package com.scrypt.sfax.base;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends AndroidViewModel>, Provider<AndroidViewModel>> viewModels;

  // ViewModelModule will provide map of below type
  @Inject
  ViewModelFactory(Map<Class<? extends AndroidViewModel>, Provider<AndroidViewModel>> viewModels) {
    this.viewModels = viewModels;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    try {
      return (T) viewModels.get(modelClass).get();
    } catch (Exception e) {
      throw new RuntimeException("Error creating view model for class: " + modelClass.getSimpleName(), e);
    }
  }
}

