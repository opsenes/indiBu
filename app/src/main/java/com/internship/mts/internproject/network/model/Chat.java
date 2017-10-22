package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Chat {

    @SerializedName("id")
    private long id;

    @SerializedName("couponId")
    private long couponId;

    @SerializedName("couponTitle")
    private String couponTitle;

    @SerializedName("buyerNickname")
    private String buyerNickname;

    @SerializedName("sellerNickname")
    private String sellerNickname;

    @SerializedName("dateOfCreation")
    private long dateOfCreation;

    @SerializedName("dateOfUpdate")
    private long dateOfUpdate;

    @SerializedName("lastMessageBody")
    private String lastMessageBody;

    public String getCouponTitle() {
        return couponTitle;
    }

    public String getBuyerNickname() {
        return buyerNickname;
    }

    public String getSellerNickname() {
        return sellerNickname;
    }

    public long getDateOfUpdate() {
        return dateOfUpdate;
    }

    public String getLastMessageBody() {
        return lastMessageBody;
    }
}
