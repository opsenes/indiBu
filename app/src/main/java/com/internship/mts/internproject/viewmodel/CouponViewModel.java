package com.internship.mts.internproject.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.internship.mts.internproject.CouponDetailActivity;
import com.internship.mts.internproject.network.model.Coupon;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.utils.PriceFormatUtil;


public class CouponViewModel {
    private Coupon coupon;

    public CouponViewModel(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getTitle() {
        return coupon.getTitle();
    }

    public String getDescription() {
        return coupon.getDescription();
    }

    public String getPrice() {
        return PriceFormatUtil.formatPrice(coupon.getPrice());
    }

    public String getPublishDate() {
        return DateFormatUtil.formatDate(coupon.getPublishDate());
    }

    public String getAuthor() {
        return coupon.getAuthor();
    }

    public String getAuthorReference() {
        return  String.valueOf(coupon.getReferencePoint());
    }

    public void onCouponCardClicked(View view) {
        view.getContext().startActivity(CouponDetailActivity.newIntent(view.getContext(), coupon.getId()));
    }
}
