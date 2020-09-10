package com.khalid.gads20leaderboard;

public class HighLearner implements Leaderboard{
    private String mDevName;
    private String mDevDetails;
    private int mImageURL;

    public HighLearner(String devName, String devDetails) {
        mDevName = devName;
        mDevDetails = devDetails;
        mImageURL = R.drawable.ic_cloud_example;
    }

    @Override
    public String getDevName() {
        return mDevName;
    }

    @Override
    public String getDevDetails() {
        return mDevDetails;
    }

    public int getImageURL() {
        return mImageURL;
    }
}
