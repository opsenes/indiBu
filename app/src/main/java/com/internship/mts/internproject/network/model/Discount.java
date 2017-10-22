package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Discount {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("photoUrl")
    private String photoUrl;

    @SerializedName("hotVoteCounter")
    private int likesNumber;

    @SerializedName("coldVoteCounter")
    private int dislikesNumber;

    @SerializedName("commentCount")
    private int commentsNumber;

    @SerializedName("created")
    private long publishDate;

    @SerializedName("author")
    private String author;

    @SerializedName("referencePoint")
    private double referencePoint;

    @SerializedName("referenceCount")
    private int referenceCount;

    @SerializedName("locationX")
    private double locationX;

    @SerializedName("locationY")
    private double locationY;

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Discount withTitle(String title){
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Discount withDescription(String description){
        this.description = description;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Discount withPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
        return this;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public Discount withLikes(int likes){
        this.likesNumber = likes;
        return this;
    }

    public int getDislikesNumber() {
        return dislikesNumber;
    }

    public Discount withDislikes(int dislikes){
        this.dislikesNumber = dislikes;
        return this;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public Discount withComments(int commentsNumber){
        this.commentsNumber = commentsNumber;
        return this;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public Discount withPublishDate(long publishDate){
        this.publishDate = publishDate;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Discount withAuthor(String author){
        this.author = author;
        return this;
    }

    public double getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(double referencePoint) {
        this.referencePoint = referencePoint;
    }

    public Discount withReference(double referencePoint){
        this.referencePoint = referencePoint;
        return this;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void userLiked() {
        //TODO ENES: Implement true false logic
        likesNumber++;
    }

    public void userDisliked() {
        //TODO ENES: Implement true false logic
        dislikesNumber++;
    }

    @Override
    public String toString()
    {
        return "Discount [hotVoteCounter = "+likesNumber+
                ", commentCount = "+commentsNumber+
                ", coldVoteCounter = "+dislikesNumber+
                ", id = "+id+
                ", author = "+author+
                ", title = "+title+
                ", created = "+publishDate+
                ", referencePoint = "+referencePoint+
                ", description = "+description+
                ", photoUrl = "+photoUrl+"]";
    }
}
