package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
