package com.internship.mts.internproject.network.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;


public class UserLocalStore {

    //Store user Data on a file
    private static final String FILE_NAME = "UserDetails";
    private static final String KEY_USER = "user";
    private static UserLocalStore INSTANCE;

    private SharedPreferences userSharedPreference;
    private Gson gson;

    public static UserLocalStore getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("UserLocalStore must be initialized");
        }
        return INSTANCE;
    }

    public static void init(Context context) {
        INSTANCE = new UserLocalStore(context);
    }

    private UserLocalStore(Context context) {
        userSharedPreference = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor editor = userSharedPreference.edit();
        String json = gson.toJson(user);
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    @Nullable
    public User getLoggedInUser() {
        String json = userSharedPreference.getString(KEY_USER, "");
        return gson.fromJson(json, User.class);
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor editor = userSharedPreference.edit();
        editor.putBoolean("loggedIn", loggedIn);
        editor.apply();
    }

    public void clearUserData() {
        //for user logging out
        SharedPreferences.Editor editor = userSharedPreference.edit();
        editor.clear();
        editor.apply();
    }

}