package com.scrypt.sfax.screen.faxlist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scrypt.sfax.R;
import com.scrypt.sfax.base.BaseApplication;
import com.scrypt.sfax.base.BaseFragment;
import com.scrypt.sfax.base.ViewModelFactory;
import com.scrypt.sfax.data.Fax;

import javax.inject.Inject;

import butterknife.BindView;

public class FaxListFragment extends BaseFragment implements FaxListSelectedListener {

  @Inject ViewModelFactory viewModelFactory;

  @BindView(R.id.fax_list) RecyclerView faxListView;

  private String folderId;
  private FaxListViewModel viewModel;


  @LayoutRes
  @Override
  protected int layoutRes() {
    return R.layout.fragment_faxlist;
  }

  @Override
  public void onAttach(Context context) {
    this.context = context;
    BaseApplication.getApplicationComponent(context).inject(this);
    super.onAttach(context);
  }

  @Override
  protected void onViewBound(View view) {
    viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(FaxListViewModel.class);

    faxListView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL));

    faxListView.setAdapter(new FaxListAdapter(viewModel, this, this));

    faxListView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    folderId = getArguments().getString("id");

    viewModel.callGetFaxList(folderId); // todo should be called after observeLiveData
  }

  @Override
  protected void observeLiveData() {
    viewModel.getFaxesLiveData().observe(this, faxes -> {
      if (faxes != null) {
        faxListView.setVisibility(View.VISIBLE);

        // faxlist data will be updated in the FaxListAdapter through viewmodel
      }

      /*
      viewModel.getError().observe(this, isError -> {
        //noinspection ConstantConditions
        if (isError) {
          //errorTextView.setVisibility(View.VISIBLE);
          faxListView.setVisibility(View.GONE);
          //errorTextView.setText(R.string.api_error_repos);
        } else {
          //errorTextView.setVisibility(View.GONE);
          //errorTextView.setText(null);
        }
      });

      viewModel.getLoading().observe(this, isLoading -> {
        //noinspection ConstantConditions
        //loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        if (isLoading) {
          //errorTextView.setVisibility(View.GONE);
          faxListView.setVisibility(View.GONE);
        }
      });
      */

    });

  }

  @Override
  public void onFaxListSelected(Fax fax) {
    Log.d("LOG","selected");
    // set selected fragment's viewmodel to transfer data
    /*
    SelectedRepoViewModel selectedRepoViewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
            .get(SelectedRepoViewModel.class);
    selectedRepoViewModel.setSelectedRepo(repo);
    */

    // jump to detail fragment
    /*
    getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.screen_container, new DetailsFragment())
            .addToBackStack(null)
            .commit();
            */
  }


}
