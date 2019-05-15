package com.scrypt.sfax.di.module;


import android.arch.lifecycle.AndroidViewModel;

import com.scrypt.sfax.home.AppEntryActivity;
import com.scrypt.sfax.home.AppEntryViewModel;
import com.scrypt.sfax.screen.faxlist.FaxListViewModel;
import com.scrypt.sfax.screen.login.LoginViewModel;
import com.scrypt.sfax.base.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(AppEntryViewModel.class)
  abstract AndroidViewModel bindAppEntryViewModel(AppEntryViewModel viewModel);

  @Binds
  @IntoMap
  @ViewModelKey(LoginViewModel.class)
  abstract AndroidViewModel bindLoginViewModel(LoginViewModel viewModel);

  @Binds
  @IntoMap
  @ViewModelKey(FaxListViewModel.class)
  abstract AndroidViewModel bindFaxListViewModel(FaxListViewModel viewModel);


}
