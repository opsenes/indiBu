package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("writePhoto")
    private String photoUrl;

    @SerializedName("writerNickname")
    private String nickname;

    @SerializedName("body")
    private String commentText;

    @SerializedName("dateOfCreation")
    private long commentDate;

    public Comment(String photoUrl,
                   String nickname,
                   String commentText,
                   long commentDate) {
        this.photoUrl = photoUrl;
        this.nickname = nickname;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCommentText() {
        return commentText;
    }

    public long getCommentDate() {
        return commentDate;
    }

}
