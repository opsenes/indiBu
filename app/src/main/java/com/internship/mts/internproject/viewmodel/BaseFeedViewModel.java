package com.internship.mts.internproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.util.Log;

public abstract class BaseFeedViewModel extends BaseObservable {
    protected int mode = 1;
    protected ObservableArrayList feedList;

    public BaseFeedViewModel() {
        feedList = new ObservableArrayList();
        loadFeedList();
    }

    public void setMode(int mode) {
        this.mode = mode;
        loadFeedList();
    }

    public ObservableArrayList getFeedList() {
        return feedList;
    }

    protected abstract void loadFeedList();

    protected void handleError(Throwable error) {
        Log.e("error", "network error");
        error.printStackTrace();
    }


}
