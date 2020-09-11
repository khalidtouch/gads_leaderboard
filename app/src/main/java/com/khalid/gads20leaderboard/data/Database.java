package com.khalid.gads20leaderboard.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {HighLearner.class, HighSkiller.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DATABASE_NAME = "dev.db";
    private static Database instance = null;

    public static synchronized Database getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class,
                    DATABASE_NAME).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract LearnerDao getLearningDao();
    public abstract SkillDao getSkillDao();
}
