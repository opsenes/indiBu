package com.internship.mts.internproject.network;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.internship.mts.internproject.core.IndibuApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private static final String PREF_COOKIES = "PREF_COOKIES";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet<String>) PreferenceManager
                .getDefaultSharedPreferences(IndibuApplication.getInstance())
                .getStringSet(PREF_COOKIES, new HashSet<String>());

        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}