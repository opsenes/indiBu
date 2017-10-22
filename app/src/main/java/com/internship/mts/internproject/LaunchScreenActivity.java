package com.internship.mts.internproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils;
import com.internship.mts.internproject.view.HomePageActivity;

public class LaunchScreenActivity extends BaseActivity {

    private static final int SPLASH_TIME = 3000;
    private static final String PREF_COOKIES = "PREF_COOKIES";
    private Intent intent;

    @Override
    protected int getLayoutRes() {
        OnboardingUtils.makeTransparentStatusBar(getWindow());
        return R.layout.activity_launch_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getStringSet(PREF_COOKIES, null) == null){
            intent = OnboardingActivity.newIntent(this);
        }
        else {
            intent = HomePageActivity.newIntent(this);
        }

        final Handler handler = new Handler();
        handler.postDelayed(() -> startActivity(intent), SPLASH_TIME);
    }
}
