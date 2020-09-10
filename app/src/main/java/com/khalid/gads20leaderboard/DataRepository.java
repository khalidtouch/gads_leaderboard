package com.khalid.gads20leaderboard;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private static List<HighLearner> mLearner;
    private static List<HighSkiller> mSkiller;
    private static DataRepository INSTANCE = null;

    private DataRepository() {}

    public static synchronized DataRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        initDevs();
        return INSTANCE;
    }

    private static void initDevs() {
        mLearner = new ArrayList<>();
        mSkiller = new ArrayList<>();
        HighLearner highLearner = new HighLearner(
                "Khalid Isah", "Batch A");
        HighSkiller highSkiller = new HighSkiller(
          "Tony Stark", "Batch B"
        );

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
