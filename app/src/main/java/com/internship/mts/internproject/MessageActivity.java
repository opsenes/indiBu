package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.view.MessageFragment;

public class MessageActivity extends BaseActivity {

    private static final String TAG = "messageFragment";

    public static Intent newIntent(Context context) {
        return new Intent(context, MessageActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.message_activity;
    }

    @Override
    protected int getContainerFragmentLayoutId() {
        return R.id.message_activity_fragment_container;
    }

    @Override
    protected Fragment getFragment() {
        return new MessageFragment();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

}