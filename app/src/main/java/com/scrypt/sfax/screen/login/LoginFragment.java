package com.scrypt.sfax.screen.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.scrypt.sfax.R;
import com.scrypt.sfax.base.BaseApplication;
import com.scrypt.sfax.base.BaseFragment;
import com.scrypt.sfax.base.ViewModelFactory;
import com.scrypt.sfax.home.AppEntryActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {

  @Inject ViewModelFactory viewModelFactory;

  @BindView(R.id.usernameText) TextView userName;
  @BindView(R.id.passwordText) TextView password;

  private LoginViewModel viewModel;

  @LayoutRes
  @Override
  protected int layoutRes() {
    return R.layout.fragment_login;
  }

  @Override
  public void onAttach(Context context) {
    this.context = context;
    BaseApplication.getApplicationComponent(context).inject(this);
    super.onAttach(context);
  }

  @Override
  protected void onViewBound(View view) {
    viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(LoginViewModel.class);
  }

  @Override
  protected void observeLiveData() {
    viewModel.getAuthLiveData().observe(this, result -> {
      if (result != null && result.isSuccess()) {
        // authenticate confirmed
        Intent intent = new Intent(context, AppEntryActivity.class);
        startActivity(intent);
        getActivity().finish();  // finish login activity
      } else {
        //TODO - authenticate failed - need dialog to display message
        userName.setText(result.getMsg());
      }
    });

    viewModel.getHttpExceptionLiveData().observe(this, result -> {
        super.handleHttpErrorException(result);
    });

  }

  @OnClick(R.id.logInButton)
  public void loginButton() {
    viewModel.callAuthenticate(
            userName.getText().toString(), password.getText().toString(), null, "1");

  }


}
