package com.internship.mts.internproject.network.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponseModel {

    @SerializedName("content")
    private List<Comment> comments;

    @SerializedName("number")
    private int page;

    public List<Comment> getComments() {
        return comments;
    }
}
