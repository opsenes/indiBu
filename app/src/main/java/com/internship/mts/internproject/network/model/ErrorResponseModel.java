package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;

public class ErrorResponseModel {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public ErrorResponseModel(String message) {
        this.message = message;
    }
}
