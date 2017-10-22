package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.Reference;
import com.internship.mts.internproject.network.model.ReferencesResponseModel;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.view.ReferenceFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferenceActivity extends BaseActivity {

    private static final String TAG = "referenceFragment";

    @BindView(R.id.reference_activity_toolbar)
    Toolbar referenceToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, ReferenceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(referenceToolbar, R.string.reference_title);

        fetchReferences();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchReferences() {

        addRequest(getService().getReferences(0, UserLocalStore.getInstance().getLoggedInUser().getNickname()), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                ReferencesResponseModel references = (ReferencesResponseModel) response.body();
                ((ReferenceFragment) getContainerFragment()).fillReferenceContainer(references.getReferences());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.reference_activity;
    }

    @Override
    protected int getContainerFragmentLayoutId() {
        return R.id.reference_activity_fragment_container;
    }

    @Override
    protected Fragment getFragment() {
        return new ReferenceFragment();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

}
