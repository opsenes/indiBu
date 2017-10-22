package com.internship.mts.internproject.core;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.internship.mts.internproject.network.model.UserLocalStore;

public class IndibuApplication extends Application {

    private static IndibuApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);

        UserLocalStore.init(this);
    }

    public static IndibuApplication getInstance() {
        return instance;
    }
}
