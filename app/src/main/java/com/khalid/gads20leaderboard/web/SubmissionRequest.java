package com.khalid.gads20leaderboard.web;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmissionRequest {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("link")
    private String link;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @NonNull
    @Override
    public String toString() {
        return "Posts{" + "name=" + name + ", lastName=" + lastName +
                ", email=" + email + ", link=" + link + "}";
    }
}
