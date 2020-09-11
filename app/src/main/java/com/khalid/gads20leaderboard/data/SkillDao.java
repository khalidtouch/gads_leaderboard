package com.khalid.gads20leaderboard.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface SkillDao {
    @Insert
    void insert(HighSkiller skiller);
    @Update
    void update(HighSkiller skiller);
    @Delete
    void delete(HighSkiller skiller);
    @Query("SELECT * FROM skill_table ORDER BY score DESC")
    LiveData<List<HighSkiller>> getSkillers();
    @Query("DELETE FROM skill_table")
    void clearAll();
}
