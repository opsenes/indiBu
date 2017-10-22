package com.internship.mts.internproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<M, B extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseAdapter.BindingHolderWrapper> {

    private List<M> dataset;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onBindViewHolder(B binding, M item, int position);

    protected BaseAdapter(List<M> dataset) {
        this.dataset = dataset;
    }

    @Override
    public BaseAdapter.BindingHolderWrapper onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolderWrapper(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getLayoutId(),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(BaseAdapter.BindingHolderWrapper holder, int position) {
        //noinspection unchecked
        onBindViewHolder((B) holder.binding, dataset.get(position), position);
    }

    public void addItemToHead(M item) {
        dataset.add(0, item);
        notifyDataSetChanged();
    }

    public void addItemToTail(M item) {
        dataset.add(item);
        notifyDataSetChanged();
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
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
