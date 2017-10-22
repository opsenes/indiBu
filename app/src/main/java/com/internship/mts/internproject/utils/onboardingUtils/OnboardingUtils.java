package com.internship.mts.internproject.utils.onboardingUtils;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class OnboardingUtils {

    public static final int FIRST_PAGE = 0;
    public static final int SECOND_PAGE = 1;
    public static final int THIRD_PAGE = 2;

    public static void makeTransparentStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
