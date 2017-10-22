package com.internship.mts.internproject.utils;

import com.internship.mts.internproject.network.ApiService;

public class PhotoUtil {
    public static String getDealPhotoUrl(String dealId) {
        return ApiService.getBaseUrlHttp() + "/photo/deal/get?dealId=" + dealId;
    }
}
