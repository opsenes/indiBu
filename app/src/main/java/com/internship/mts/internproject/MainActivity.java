package com.internship.mts.internproject;

import android.view.View;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.view.HomePageActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    /** TEMPORARY NAVIGATION ACTIVITY **/

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.signup_btn)
    public void goSignUp(View view) {
        startActivity(MessageActivity.newIntent(this));
    }

    @OnClick(R.id.profile_page_btn)
    public void goProfilePage(View view) {
        startActivity(ProfilePageActivity.newIntent(this));
    }

    @OnClick(R.id.deal_detail_btn)
    public void goDealDetail() {
        startActivity(DiscountDetailActivity.newIntent(this, 0));
    }

    @OnClick(R.id.coupon_detail_btn)
    public void goCouponDetail() {startActivity(CouponDetailActivity.newIntent(this, 0));
    }

    @OnClick(R.id.create_deal_btn)
    public void goCreateDeal() {
        startActivity(CreateDiscountActivity.newIntent(this));
    }

    @OnClick(R.id.create_coupon_btn)
    public void goCreateCoupon() {
        startActivity(CreateCouponActivity.newIntent(this));
    }

    @OnClick(R.id.homepage_btn)
    public void goHomePage(View view) {
        startActivity(HomePageActivity.newIntent(this));
    }
}
