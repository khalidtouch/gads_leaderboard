package com.khalid.gads20leaderboard.web;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khalid.gads20leaderboard.data.Leaderboard;

public class HighSkillerResponse implements Leaderboard{

    @SerializedName("name")
    @Expose
    private String mDevName;

    @SerializedName("country")
    @Expose
    private String mCountry;


    @SerializedName("badgeUrl")
    @Expose
    private String mBadgeUrl;

    @SerializedName("score")
    @Expose
    private Integer mScore;

    @Override
    public String getDevName() {
        return mDevName;
    }

    @Override
    public String getCountry() {
        return mCountry;
    }

    @Override
    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    @Override
    public int getPlaceHolderImageUrl() {
        return 0;
    }

    public Integer getScore() {
        return mScore;
    }
}