package com.scrypt.sfax.di.component;


import com.scrypt.sfax.di.module.ApplicationModule;
import com.scrypt.sfax.di.module.DatabaseCacheModule;
import com.scrypt.sfax.di.module.NetworkServiceModule;
import com.scrypt.sfax.di.module.RepoServiceModule;
import com.scrypt.sfax.di.module.ViewModelModule;
import com.scrypt.sfax.home.AppEntryActivity;
import com.scrypt.sfax.screen.contactlist.ContactListFragment;
import com.scrypt.sfax.screen.faxlist.FaxListFragment;
import com.scrypt.sfax.screen.login.LoginFragment;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ViewModelModule.class,
        RepoServiceModule.class,
        NetworkServiceModule.class,
        DatabaseCacheModule.class,
})
public interface ApplicationComponent {

  void inject(AppEntryActivity activity);
  void inject(LoginFragment fragment);
  void inject(FaxListFragment fragment);
  void inject(ContactListFragment fragment);

//  void inject(DetailsFragment detailsFragment);
}
