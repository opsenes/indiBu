package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Coupon {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("author")
    private String author;

    @SerializedName("price")
    private String price;

    @SerializedName("dateOfCreation")
    private long publishDate;

    @SerializedName("referencePoint")
    private double referencePoint;

    @SerializedName("referenceCount")
    private int referenceCount;


    public Coupon(String title, String description, long publishDate, String price, String author, double referencePoint) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.price = price;
        this.author = author;
        this.referencePoint = referencePoint;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(double referencePoint) {
        this.referencePoint = referencePoint;
    }

    public String getPrice() {
        return price;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int ReferenceCount) {
        this.referenceCount = referenceCount;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
