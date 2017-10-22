package com.internship.mts.internproject.view;

import android.databinding.ObservableArrayList;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseObservableAdapter;
import com.internship.mts.internproject.databinding.CouponItemBinding;
import com.internship.mts.internproject.network.model.Coupon;
import com.internship.mts.internproject.viewmodel.CouponViewModel;


public class CouponAdapter extends BaseObservableAdapter<Coupon, CouponItemBinding> {

    public CouponAdapter(ObservableArrayList couponList) {
        super(couponList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coupon_item;
    }

    @Override
    protected void onBindViewHolder(CouponItemBinding binding, Coupon item, int position) {
        binding.setCouponViewModel(new CouponViewModel(item));
    }

}
