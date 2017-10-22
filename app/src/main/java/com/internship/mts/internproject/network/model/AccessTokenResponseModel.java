package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class AccessTokenResponseModel {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("error")
    private String error;

    private AccessTokenResponseModel() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getError() {
        return error;
    }
}
