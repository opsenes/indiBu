package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.Coupon;
import com.internship.mts.internproject.utils.DateFormatUtil;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CouponDetailActivity extends BaseActivity {

    private Coupon coupon;
    private static final String COUPON_ID_KEY = "couponId";

    @BindView(R.id.coupon_details_textview_title)
    TextView titleTextView;

    @BindView(R.id.coupon_details_textview_decription)
    TextView descriptionTextView;

    @BindView(R.id.coupon_details_textview_postdate)
    TextView postDateTextView;

    @BindView(R.id.coupon_details_textview_price_number)
    TextView priceTextView;

    @BindView(R.id.coupon_details_textview_postby)
    TextView authorTextView;

    @BindView(R.id.coupon_detail_activity_text_view_author_reference)
    TextView referencePointCountTextView;

    @BindView(R.id.coupon_detail_activity_toolbar)
    Toolbar couponToolbar;

    public static Intent newIntent(Context context, int couponId) {
        return new Intent(context, CouponDetailActivity.class).putExtra(COUPON_ID_KEY, couponId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(couponToolbar, R.string.coupon_detail_title);
        fetchCouponDetails();
    }

    private void fetchCouponDetails() {
        addRequest(getService().getCouponDetail(getIntent().getIntExtra(COUPON_ID_KEY, 0)), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Coupon coupon = (Coupon) response.body();
                setCoupon(coupon);
                setCouponDetails(coupon);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
            Log.i("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    private void setCouponDetails(Coupon coupon) {
        titleTextView.setText(coupon.getTitle());
        descriptionTextView.setText(coupon.getDescription());
        postDateTextView.setText(DateFormatUtil.formatDate(coupon.getPublishDate()));
        priceTextView.setText(coupon.getPrice());
        authorTextView.setText(coupon.getAuthor());

        referencePointCountTextView.setText(getResources().getString(
                R.string.coupon_detail_activity_reference_bar,
                coupon.getReferencePoint(), coupon.getReferenceCount()
        ));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_coupon_detail;
    }

}

