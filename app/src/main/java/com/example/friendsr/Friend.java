package com.example.friendsr;

import java.io.Serializable;

public class Friend implements Serializable {

    // Declare fields to store information of a user
    private String name, bio;
    private float rating;
    private int idDrawable;
    public float profileLiked;

    // Method to create friend with name, bio and id
    public Friend(String name, String bio, int id_drawable) {
        this.name = name;
        this.bio = bio;
        this.idDrawable = id_drawable;
        profileLiked = 0;
    }

    // Getters and setters
    public float getProfileLiked() { return profileLiked; }

    public void setProfileLiked(float profile_liked) {
        this.profileLiked = profile_liked;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public float getRating() {
        return rating;
    }

    public int getId_drawable() {
        return idDrawable;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
