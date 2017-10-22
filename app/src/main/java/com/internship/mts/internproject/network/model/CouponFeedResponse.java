package com.internship.mts.internproject.network.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponFeedResponse {

    @SerializedName("content")
    private ArrayList<Coupon> couponList = null;

    @SerializedName("last")
    private Boolean last;

    @SerializedName("totalElements")
    private Integer totalElements;

    @SerializedName("totalPages")
    private Integer totalPages;

    @SerializedName("size")
    private Integer size;

    @SerializedName("number")
    private Integer number;

    @SerializedName("first")
    private Boolean first;

    @SerializedName("numberOfElements")
    private Integer numberOfElements;

    public ArrayList<Coupon> getCouponList() {
        return couponList;
    }

    @Override
    public String toString()
    {
        return "CouponFeedResponse [numberOfElements = "+numberOfElements+
                ", last = "+last+
                ", totalElements = "+totalElements+
                ", number = "+number+
                ", first = "+first+
                ", coupon = "+couponList+
                ", totalPages = "+totalPages+
                ", size = "+size+"]";
    }
}
