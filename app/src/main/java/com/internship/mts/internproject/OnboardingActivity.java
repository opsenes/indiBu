package com.internship.mts.internproject;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils;
import com.internship.mts.internproject.view.HomePageActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils.FIRST_PAGE;
import static com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils.SECOND_PAGE;
import static com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils.THIRD_PAGE;


public class OnboardingActivity extends BaseActivity {

    SectionsAdapterPager mSectionsPagerAdapter;

    @BindView(R.id.activity_onboarding_view_pager)
    ViewPager viewPager;

    @BindView(R.id.activity_onboarding_image_button_next)
    ImageButton nextButton;

    @BindView(R.id.activity_onboarding_button_skip)
    Button skipButton;

    @BindView(R.id.activity_onboarding_button_login)
    Button loginButton;

    @BindView(R.id.activity_onboarding_image_view_intro_indicator_0)
    ImageView zero;

    @BindView(R.id.activity_onboarding_image_view_intro_indicator_1)
    ImageView one;

    @BindView(R.id.activity_onboarding_image_view_intro_indicator_2)
    ImageView two;

    @BindView(R.id.activity_onboarding_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    ImageView[] indicators;

    private int currentVisiblePage = 0;   //  to track currentVisiblePage position

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, OnboardingActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        OnboardingUtils.makeTransparentStatusBar(getWindow());
        return R.layout.activity_onboarding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSectionsPagerAdapter = new SectionsAdapterPager(getSupportFragmentManager());

        indicators = new ImageView[]{zero, one, two};

        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setCurrentItem(currentVisiblePage);
        updateIndicators(currentVisiblePage);

        final int[] onboardingBackgroundcolorList = new int[]{
                ContextCompat.getColor(this, R.color.dark_purple),
                ContextCompat.getColor(this, R.color.purple_main2),
                ContextCompat.getColor(this, R.color.purple_main3)};

        final ArgbEvaluator evaluator = new ArgbEvaluator();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int evaluateBackgroundColor = (Integer) evaluator.evaluate(positionOffset,
                        onboardingBackgroundcolorList[position],
                        onboardingBackgroundcolorList[position == THIRD_PAGE ? position : position + 1]);
                viewPager.setBackgroundColor(evaluateBackgroundColor);
            }

            @Override
            public void onPageSelected(int position) {

                currentVisiblePage = position;
                updateIndicators(currentVisiblePage);

                switch (position) {
                    case FIRST_PAGE:
                        viewPager.setBackgroundColor(onboardingBackgroundcolorList[FIRST_PAGE]);
                        break;
                    case SECOND_PAGE:
                        viewPager.setBackgroundColor(onboardingBackgroundcolorList[SECOND_PAGE]);
                        break;
                    case THIRD_PAGE:
                        viewPager.setBackgroundColor(onboardingBackgroundcolorList[THIRD_PAGE]);
                        break;
                }

                nextButton.setVisibility(position == THIRD_PAGE ? View.GONE : View.VISIBLE);
                loginButton.setVisibility(position == THIRD_PAGE ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    @OnClick(R.id.activity_onboarding_button_skip)
    public void skipOnboarding(View view){
        startActivity(LoginActivity.newIntent(getBaseContext()));
    }

    @OnClick(R.id.activity_onboarding_button_login)
    public void goToLoginPage(View view){
        startActivity(LoginActivity.newIntent(getBaseContext()));
    }

    @OnClick(R.id.activity_onboarding_image_button_next)
    public void goNextPage(View view){
        currentVisiblePage += 1;
        viewPager.setCurrentItem(currentVisiblePage, true);
    }
}