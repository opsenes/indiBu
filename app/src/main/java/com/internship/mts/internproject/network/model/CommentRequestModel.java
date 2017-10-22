package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class CommentRequestModel {

    @SerializedName("body")
    private String commentText;

    @SerializedName("dealId")
    private long dealId;

    public CommentRequestModel(String commentText, long dealId) {
        this.commentText = commentText;
        this.dealId = dealId;
    }
}
