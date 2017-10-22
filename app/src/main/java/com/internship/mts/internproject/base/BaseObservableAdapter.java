package com.internship.mts.internproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseObservableAdapter<M, B extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseObservableAdapter.BindingHolderWrapper> {

    private ObservableArrayList<M> dataset;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onBindViewHolder(B binding, M item, int position);

    protected BaseObservableAdapter(ObservableArrayList<M> dataset) {
        this.dataset = dataset;
        this.dataset.addOnListChangedCallback(new ObservableAdapterNotifierCallback(this));
    }

    @Override
    public BaseObservableAdapter.BindingHolderWrapper onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolderWrapper(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getLayoutId(),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(BaseObservableAdapter.BindingHolderWrapper holder, int position) {
        //noinspection unchecked
        onBindViewHolder((B) holder.binding, dataset.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class BindingHolderWrapper extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public BindingHolderWrapper(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.executePendingBindings();
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
