package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.CreateCouponModel;
import com.internship.mts.internproject.utils.validation.TitleValidatorUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCouponActivity extends BaseActivity {

    @BindView(R.id.activity_create_coupon_edittext_title)
    EditText editTextTitle;

    @BindView(R.id.activity_create_coupon_edittext_description)
    EditText editTextDescription;

    @BindView(R.id.activity_create_coupon_edittext_price)
    EditText editTextPrice;

    @BindView(R.id.create_coupon_activity_toolbar)
    Toolbar createCouponToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateCouponActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(createCouponToolbar, R.string.create_coupon_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_coupon;
    }

    @OnClick(R.id.activity_create_coupon_button)
    public void createCoupon() {
        if (!validate()) {
            return;
        }

        if (TextUtils.equals(editTextPrice.getText().toString(), "")){
            editTextPrice.setText("0");
        }

        CreateCouponModel createCouponModel = new CreateCouponModel(
                editTextTitle.getText().toString(),
                editTextDescription.getText().toString(),
                Double.parseDouble(editTextPrice.getText().toString())
        );

        addRequest(getService().createCoupon(createCouponModel), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                finish();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Toast.makeText(CreateCouponActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        TitleValidatorUtil titleValidatorUtil = new TitleValidatorUtil(editTextTitle.getText().toString());
        if (titleValidatorUtil.validate() != 0) {
            valid = false;
            editTextTitle.setError(titleValidatorUtil.getErrorMessage(this, titleValidatorUtil.validate()));
        }
        return valid;
    }

}