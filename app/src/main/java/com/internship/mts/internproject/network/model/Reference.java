package com.internship.mts.internproject.network.model;

import java.util.Date;
import com.google.gson.annotations.SerializedName;


public class Reference {

    @SerializedName("id")
    private long id;

    @SerializedName("writerPhotoUrl")
    private String photoUrl;

    @SerializedName("writerNickname")
    private String nickname;

    @SerializedName("rating")
    private int starRating;

    @SerializedName("comment")
    private String comment;

    @SerializedName("dateOfCreation")
    private long referenceDate;

    public Reference(String photoUrl,
                     String nickname,
                     int starRating,
                     String comment,
                     long referenceDate) {
        this.photoUrl = photoUrl;
        this.nickname = nickname;
        this.starRating = starRating;
        this.comment = comment;
        this.referenceDate = referenceDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getComment() {
        return comment;
    }

    public long getReferenceDate() {
        return referenceDate;
    }

}
