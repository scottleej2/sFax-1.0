package com.scrypt.sfax.screen.faxlist;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scrypt.sfax.R;
import com.scrypt.sfax.data.Fax;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaxListAdapter extends RecyclerView.Adapter<FaxListAdapter.FaxViewHolder> {

  private final List<Fax> data = new ArrayList<>();
  private final FaxListSelectedListener faxListSelectedListener;

  FaxListAdapter(FaxListViewModel viewModel, LifecycleOwner lifecycleOwner,
                                              FaxListSelectedListener faxListSelectedListener) {
    this.faxListSelectedListener = faxListSelectedListener;

    // receives livedata from viewmodel to update list data
    viewModel.getFaxesLiveData().observe(lifecycleOwner, faxes -> {
      data.clear();
      if (faxes != null) {
        data.addAll(faxes);
      }

      notifyDataSetChanged(); //TODO: Use DiffUtil when we have AutoValue models
    });

    setHasStableIds(true);
  }

  @Override
  public FaxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.fax_list_content, parent, false);

    return new FaxViewHolder(view, faxListSelectedListener);
  }

  @Override
  public void onBindViewHolder(FaxViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  @Override
  public long getItemId(int position) {
    return data.get(position).getId();
  }

  static final class FaxViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_faxName) TextView faxName;
    @BindView(R.id.text_faxDate) TextView faxDate;

    private Fax fax;

    FaxViewHolder(View itemView, FaxListSelectedListener faxListSelectedListener) {
      super(itemView);

      ButterKnife.bind(this, itemView);

      itemView.setOnClickListener(v -> {
        if (fax != null) {
          faxListSelectedListener.onFaxListSelected(fax);
        }
      });

    }

    void bind(Fax fax) {
      this.fax = fax;

      faxName.setText(fax.getName());
      //faxDate.setText(fax.getDate().toString());
    }

  }

}
