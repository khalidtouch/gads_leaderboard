package com.khalid.gads20leaderboard;

public class HighSkiller implements Leaderboard {
    private String mDevName;
    private String mDevDetails;
    private int mImageURL;

    public HighSkiller(String devName, String devDetails) {
        mDevName = devName;
        mDevDetails = devDetails;
        mImageURL = R.drawable.ic_star_example;
    }

    @Override
    public String getDevName() {
        return mDevName;
    }

    @Override
    public String getDevDetails() {
        return mDevDetails;
    }

    @Override
    public int getImageURL() {
        return mImageURL;
    }
}
