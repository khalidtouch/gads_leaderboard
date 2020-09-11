package com.khalid.gads20leaderboard.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.khalid.gads20leaderboard.R;

@Entity(tableName = "learner_table")
public class HighLearner implements Leaderboard{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;


    @ColumnInfo(name = "name")
    private String mDevName;


    @ColumnInfo(name = "country")
    private String mCountry;

    @Ignore
    private int mPlaceHolderImageUrl;

    @ColumnInfo(name = "badge_url")
    private String mBadgeUrl;


    @ColumnInfo(name = "hours")
    private Integer mHours;

    public HighLearner(String devName, String country, String badgeUrl, Integer hours ) {
        mDevName = devName;
        mCountry = country;
        mBadgeUrl = badgeUrl;
        mHours = hours;
        mPlaceHolderImageUrl = R.drawable.ic_cloud_example;
    }

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

    public int getPlaceHolderImageUrl() {
        return mPlaceHolderImageUrl;
    }

    public Integer getHours() {
        return mHours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
