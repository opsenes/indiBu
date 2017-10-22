package com.internship.mts.internproject.network;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.internship.mts.internproject.core.IndibuApplication;

public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = (HashSet<String>) PreferenceManager
                    .getDefaultSharedPreferences(IndibuApplication.getInstance())
                    .getStringSet("PREF_COOKIES", new HashSet<String>());

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor sharedPreferenceEditor = PreferenceManager
                    .getDefaultSharedPreferences(IndibuApplication.getInstance())
                    .edit();
            sharedPreferenceEditor.putStringSet("PREF_COOKIES", cookies).apply();
            sharedPreferenceEditor.commit();
        }

        return originalResponse;
    }
}