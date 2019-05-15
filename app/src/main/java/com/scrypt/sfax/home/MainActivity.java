package com.scrypt.sfax.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scrypt.sfax.R;
import com.scrypt.sfax.screen.login.LoginFragment;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.screen_container, new LoginFragment())
              .commit();
    }
  }
}
