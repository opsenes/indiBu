package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("averageRating")
    private double averageRating;

    @SerializedName("reviewNumberCounter")
    private int reviewNumberCounter;

    @SerializedName("email")
    private String email;

    @SerializedName("food")
    private boolean food;

    @SerializedName("cosmetics")
    private boolean cosmetics;

    @SerializedName("electronics")
    private boolean electronics;

    @SerializedName("clothing")
    private boolean clothing;

    @SerializedName("photoUrl")
    private String photoUrl;

    public User(String nickname,
                String firstName,
                String lastName,
                double averageRating,
                int reviewNumberCounter,
                String email,
                boolean food,
                boolean cosmetics,
                boolean electronics,
                boolean clothing,
                String photoUrl) {

        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRating = averageRating;
        this.reviewNumberCounter = reviewNumberCounter;
        this.email = email;
        this.food = food;
        this.cosmetics = cosmetics;
        this.electronics = electronics;
        this.clothing = clothing;
        this.photoUrl = photoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewNumberCounter() {
        return reviewNumberCounter;
    }

    public void setReviewNumberCounter(int reviewNumberCounter) {
        this.reviewNumberCounter = reviewNumberCounter;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(boolean cosmetics) {
        this.cosmetics = cosmetics;
    }

    public boolean isElectronics() {
        return electronics;
    }

    public void setElectronics(boolean electronics) {
        this.electronics = electronics;
    }

    public boolean isClothing() {
        return clothing;
    }

    public void setClothing(boolean clothing) {
        this.clothing = clothing;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", averageRating=" + averageRating +
                ", reviewNumberCounter=" + reviewNumberCounter +
                ", email='" + email + '\'' +
                ", food=" + food +
                ", cosmetics=" + cosmetics +
                ", electronics=" + electronics +
                ", clothing=" + clothing +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}