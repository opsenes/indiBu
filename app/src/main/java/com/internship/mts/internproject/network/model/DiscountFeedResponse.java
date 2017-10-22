package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DiscountFeedResponse {

    @SerializedName("content")
    private ArrayList<Discount> discountList = null;

    @SerializedName("first")
    private Boolean first;

    @SerializedName("last")
    private Boolean last;

    @SerializedName("number")
    private Integer number;

    @SerializedName("numberOfElements")
    private Integer numberOfElements;

    @SerializedName("size")
    private Integer size;

    @SerializedName("totalElements")
    private Integer totalElements;

    @SerializedName("totalPages")
    private Integer totalPages;

    public ArrayList<Discount> getDiscountList() {
        return discountList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+discountList+
                ", numberOfElements = "+numberOfElements+
                ", last = "+last+
                ", totalElements = "+totalElements+
                ", number = "+number+
                ", first = "+first+
                ", totalPages = "+totalPages+
                ", size = "+size+"]";
    }

}
