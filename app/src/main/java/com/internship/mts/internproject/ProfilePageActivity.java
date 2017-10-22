package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.core.IndibuApplication;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.utils.UserUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePageActivity extends BaseActivity {

    private User user;

    @BindView(R.id.activity_profile_page_rating_bar_rate)
    RatingBar referenceRatingBar;

    @BindView(R.id.activity_profile_page_textview_nickname)
    TextView nicknameTextView;

    @BindView(R.id.activity_profile_page_textview_reference)
    TextView referenceTextView;

    @BindView(R.id.activity_profile_page_simpledraweeview_profile_image)
    SimpleDraweeView profileImageView;

    @BindView(R.id.profile_page_activity_toolbar)
    Toolbar profilePageToolbar;

    public static Intent newIntent(Context activity) {
        return new Intent(activity, ProfilePageActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(profilePageToolbar, R.string.profile_page_title);

        fetchUserInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillUserFields() {
        profileImageView.setImageURI(UserUtil.getUserPhotoUrl(user.getNickname()));
        nicknameTextView.setText(user.getNickname());
        referenceRatingBar.setRating((float) user.getAverageRating());
        referenceTextView.setText(getResources().getString(R.string.profile_page_reference_bar,
                user.getAverageRating(), user.getReviewNumberCounter()));
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void fetchUserInfo() {

        addRequest(getService().getUser(), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                User user = (User) response.body();
                UserLocalStore.getInstance().storeUserData(user);
                setUser(user);
                fillUserFields();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                setUser(UserLocalStore.getInstance().getLoggedInUser());
                if (user == null){
                    Intent loginIntent = LoginActivity.newIntent(ProfilePageActivity.this);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                } else {
                    fillUserFields();
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile_page;
    }

    @OnClick(R.id.activity_profile_page_button_profile_settings)
    public void goProfileSettings() {
        startActivity(ProfileSettingsActivity.newIntent(this));
    }

    @OnClick(R.id.activity_profile_page_button_reference)
    public void goReferencePage() {
        startActivity(ReferenceActivity.newIntent(this));
    }

    @OnClick(R.id.activity_profile_page_button_logout)
    public void logout() {
        SharedPreferences.Editor sharedPreferenceEditor = PreferenceManager
                .getDefaultSharedPreferences(IndibuApplication.getInstance())
                .edit();
        sharedPreferenceEditor.clear();
        sharedPreferenceEditor.apply();
        UserLocalStore.getInstance().clearUserData();
        Intent loginIntent = LoginActivity.newIntent(this);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }

}
