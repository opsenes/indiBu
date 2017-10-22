package com.internship.mts.internproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.internship.mts.internproject.utils.onboardingUtils.OnboardingUtils;

public class SectionsAdapterPager extends FragmentPagerAdapter {


    public SectionsAdapterPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PlaceHolderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case OnboardingUtils.FIRST_PAGE:
                return "SECTION 1";
            case OnboardingUtils.SECOND_PAGE:
                return "SECTION 2";
            case OnboardingUtils.THIRD_PAGE:
                return "SECTION 3";
        }
        return null;
    }

}
