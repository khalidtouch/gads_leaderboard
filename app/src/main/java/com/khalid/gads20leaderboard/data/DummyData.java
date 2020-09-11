package com.khalid.gads20leaderboard.data;


import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private static List<HighLearner> mLearner;
    private static List<HighSkiller> mSkiller;
    private static DummyData INSTANCE = null;

    private DummyData() {}

    public static synchronized DummyData getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DummyData();
        }
        initDevs();
        return INSTANCE;
    }

    private static void initDevs() {
        mLearner = new ArrayList<>();
        mSkiller = new ArrayList<>();
        HighLearner highLearner = new HighLearner(
                "Khalid Isah", "Nigeria",
                "badge.com", 34);
        HighSkiller highSkiller = new HighSkiller(
          "Tony Stark", "Kenya", "badge.com",
                280);

        for(int i = 0; i < 5; i++) {
            mLearner.add(highLearner);
            mSkiller.add(highSkiller);
        }
    }

    public static List<HighLearner> getAllLearners() {
        return mLearner;
    }

    public static List<HighSkiller> getAllSkillers() {
        return mSkiller;
    }
}
