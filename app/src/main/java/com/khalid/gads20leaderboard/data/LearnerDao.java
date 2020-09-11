package com.khalid.gads20leaderboard.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LearnerDao {
    @Insert
    void insert(HighLearner learner);
    @Update
    void update(HighLearner learner);
    @Delete
    void delete(HighLearner learner);
    @Query("SELECT * FROM learner_table ORDER BY hours DESC")
    LiveData<List<HighLearner>> getLearners();
    @Query("DELETE FROM learner_table")
    void clearAll();

}
