package com.khalid.gads20leaderboard.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.khalid.gads20leaderboard.R;

@Entity(tableName = "skill_table")
public class HighSkiller implements Leaderboard {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String mDevName;

    @SerializedName("country")
    @ColumnInfo(name = "country")
    private String mCountry;
    @Ignore
    private int mPlaceHolderImageUrl;

    @SerializedName("badgeUrl")
    @ColumnInfo(name = "badge_url")
    private String mBadgeUrl;

    @SerializedName("score")
    @ColumnInfo(name = "score")
    private Integer mScore;

    public HighSkiller(String devName, String country, String badgeUrl, Integer score) {
        mDevName = devName;
        mCountry = country;
        mBadgeUrl = badgeUrl;
        mScore = score;
        mPlaceHolderImageUrl = R.drawable.ic_star_example;
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

    public Integer getScore() {
        return mScore;
    }

    @Override
    public int getPlaceHolderImageUrl() {
        return mPlaceHolderImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

