package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserUpdateRequest {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("oldPassword")
    private String oldPassword;

    @SerializedName("newPassword")
    private String newPassword;

    @SerializedName("interestSet")
    private List<String> categories;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}