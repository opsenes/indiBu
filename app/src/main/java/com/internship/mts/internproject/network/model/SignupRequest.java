package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("electronics")
    private Boolean electronics;

    @SerializedName("cosmetics")
    private Boolean cosmetics;

    @SerializedName("clothing")
    private Boolean clothing;

    @SerializedName("food")
    private Boolean food;

    public SignupRequest(String nickname, String firstname, String lastname, String email, String password,
                         Boolean electronics, Boolean cosmetics,
                         Boolean clothing, Boolean food) {
        this.nickname = nickname;
        this.firstName = firstname;
        this.lastName = lastname;
        this.password = password;
        this.email = email;
        this.electronics = electronics;
        this.cosmetics = cosmetics;
        this.clothing = clothing;
        this.food = food;
    }
}
