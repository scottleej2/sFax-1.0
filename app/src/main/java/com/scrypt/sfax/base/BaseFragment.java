package com.scrypt.sfax.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseFragment extends Fragment {

  protected Context context;
  private Unbinder unbinder;

  protected static final String INSTANCE_ID_KEY = "instance_id";

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(layoutRes(), container, false);
    unbinder = ButterKnife.bind(this, view);

    onViewBound(view);

    observeLiveData();

    return view;
  }

  @Override
  public void onDestroyView() {
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }

    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public void handleHttpErrorException(HttpException e) {
    switch(e.code()) {
      case 401:
        break;
      case 404:
        break;
      case 500:
        break;
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  // should implement on sub classes
  //

  @LayoutRes
  protected abstract int layoutRes();

  protected abstract void onViewBound(View view);

  protected abstract void observeLiveData();
}
