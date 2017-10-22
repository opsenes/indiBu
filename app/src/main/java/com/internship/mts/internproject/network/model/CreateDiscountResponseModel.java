package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class CreateDiscountResponseModel {

    @SerializedName("dealId")
    private long discountId;

    public long getDiscountId() {
        return discountId;
    }
}
