package com.scrypt.sfax.base;

import android.arch.lifecycle.AndroidViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {

  Class<? extends AndroidViewModel> value();
}
