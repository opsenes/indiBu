package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public static LoginRequest createFromEmail(String email, String password) {
        return new LoginRequest(null, email, password);
    }

    public static LoginRequest createFromNickname(String nickname, String password) {
        return new LoginRequest(nickname, null, password);
    }

    private LoginRequest(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

}
