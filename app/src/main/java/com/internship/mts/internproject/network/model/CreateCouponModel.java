package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class CreateCouponModel {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private Double price;

    public CreateCouponModel(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
