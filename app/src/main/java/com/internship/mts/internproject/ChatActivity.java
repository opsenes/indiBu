package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.ChatsResponseModel;
import com.internship.mts.internproject.view.ChatFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends BaseActivity {

    private static final String TAG = "chatFragment";

    @BindView(R.id.chat_activity_toolbar)
    Toolbar chatToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, ChatActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(chatToolbar, R.string.chat_title);
        fetchChats();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchChats() {

        addRequest(getService().getChats(), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                ChatsResponseModel chats = (ChatsResponseModel) response.body();
                ((ChatFragment) getContainerFragment()).fillChatContainer(chats.getChats());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.chat_activity;
    }

    @Override
    protected int getContainerFragmentLayoutId() {
        return R.id.chat_activity_fragment_container;
    }


    @Override
    protected Fragment getFragment() {
        return new ChatFragment();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
