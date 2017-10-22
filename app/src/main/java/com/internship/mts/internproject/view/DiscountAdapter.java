package com.internship.mts.internproject.view;

import android.databinding.ObservableArrayList;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseObservableAdapter;
import com.internship.mts.internproject.databinding.DiscountItemBinding;
import com.internship.mts.internproject.network.model.Discount;
import com.internship.mts.internproject.viewmodel.DiscountViewModel;

public class DiscountAdapter extends BaseObservableAdapter<Discount, DiscountItemBinding> {

    public DiscountAdapter(ObservableArrayList discountList) {
        super(discountList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.discount_item;
    }

    @Override
    protected void onBindViewHolder(DiscountItemBinding binding, Discount item, int position) {
        binding.setDiscountViewModel(new DiscountViewModel(item));
    }
}
