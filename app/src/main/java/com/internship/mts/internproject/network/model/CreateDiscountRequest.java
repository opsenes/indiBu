package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CreateDiscountRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("categories")
    private ArrayList<String> categories;

    @SerializedName("locationX")
    private double locationX;

    @SerializedName("locationY")
    private double locationY;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setLat(double locationX) {
        this.locationX = locationX;
    }

    public void setLong(double locationY) {
        this.locationY = locationY;
    }

}
