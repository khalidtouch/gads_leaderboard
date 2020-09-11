package com.khalid.gads20leaderboard.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalid.gads20leaderboard.web.HighLearnerResponse;
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.List;

public class SkillerVIewModel extends AndroidViewModel {

    private DataRepository mRepository;
    private LiveData<List<HighSkiller>> highSkillers;
    private LiveData<List<HighSkillerResponse>> skillerResponse;

    public SkillerVIewModel(@NonNull Application application) {
        super(application);
        mRepository = new DataRepository(application);
        highSkillers = mRepository.getHighSkillers();
    }

    public void insert(HighSkiller skiller) {
        mRepository.insert(skiller);
    }

    public void update(HighSkiller skiller) {
        mRepository.update(skiller);
    }

    public void delete(HighSkiller skiller) {
        mRepository.delete(skiller);
    }

    public void clearAll() {
        mRepository.clearAllSkillers();
    }

    public LiveData<List<HighSkiller>> getHighSkillers(){
        return highSkillers;
    }

    /**
     * web service
     */

    public void init() {
        skillerResponse = mRepository.getAllSkillersLiveData();
    }

    public LiveData<List<HighSkillerResponse>> getSkillerResponseLiveData() {
        return skillerResponse;
    }

    public void getAllSkillers() {
        mRepository.getAllSkillers();
    }

}

