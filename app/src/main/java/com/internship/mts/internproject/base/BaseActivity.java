package com.internship.mts.internproject.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.internship.mts.internproject.LoginActivity;
import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.IndiBuServiceApi;
import com.internship.mts.internproject.R;
import com.internship.mts.internproject.network.model.ErrorResponseModel;
import com.internship.mts.internproject.network.model.UserLocalStore;

import java.io.IOException;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        createOrRestoreFragment();
    }

    protected void setupToolbar(Toolbar toolbar, @StringRes int titleResId) {
        toolbar.setTitle(titleResId);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected IndiBuServiceApi getService() {
        return ApiService.getInstance().getApi(IndiBuServiceApi.class);
    }

    protected void showProgress(@NonNull String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        }
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void addRequest(Call call, final Callback externalCallback){
        addRequest(call, externalCallback, true);
    }

    protected void addRequest(Call call, final Callback externalCallback, final boolean showProgress){

        if (showProgress) {
            showProgress(getResources().getString(R.string.please_wait));
        }


        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    externalCallback.onResponse(call, response);
                } else if (response.code() == 403) {
                    Intent loginIntent = LoginActivity.newIntent(BaseActivity.this);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                } else {
                    ErrorResponseModel errorResponseModel;
                    try {
                        errorResponseModel = new Gson().fromJson(response.errorBody().string(), ErrorResponseModel.class);
                    } catch (IOException e) {
                        errorResponseModel = new ErrorResponseModel(getResources().getString(R.string.unexcepted_error));
                    }
                    externalCallback.onFailure(call, new Exception(errorResponseModel.getMessage()));
                }
                if (showProgress) {
                    dismissProgress();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                if(UserLocalStore.getInstance().getLoggedInUser() == null){
                    Intent loginIntent = LoginActivity.newIntent(BaseActivity.this);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }
                externalCallback.onFailure(call, t);
                if (showProgress) {
                    dismissProgress();
                }
            }
        });
    }
    
    private void createOrRestoreFragment() {
        Fragment fragment = getFragment();
        String fragmentTag = getFragmentTag();
        if (fragment != null && fragmentTag != null) {
            restoreFragment();
        }
    }

    @Nullable
    protected Fragment getFragment() {
        return null;
    }

    @Nullable
    protected String getFragmentTag() {
        return null;
    }

    protected void restoreFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getFragmentTag());

        if (fragment == null) {
            replaceFragment(getFragment(), getFragmentTag());
        }
    }

    protected void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(getContainerFragmentLayoutId(), fragment, tag)
                .commit();
    }

    public <F extends Fragment> F getContainerFragment() {
        //noinspection unchecked
        return (F) getSupportFragmentManager().findFragmentByTag(getFragmentTag());
    }

    protected int getContainerFragmentLayoutId() {
        return 0;
    }

}
