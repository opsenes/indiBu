package com.internship.mts.internproject.utils;

import android.text.TextUtils;

import com.google.android.gms.common.api.Api;
import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.model.UserLocalStore;

public class UserUtil {
    public static boolean isLoggedUser(String nickname) {
        //noinspection ConstantConditions
        return TextUtils.equals(UserLocalStore.getInstance().getLoggedInUser().getNickname(), nickname);
    }

    public static String getUserPhotoUrl(String nickname){
        return ApiService.getBaseUrlHttp() + "/photo/user/get?nickname=" + nickname;
    }
}
