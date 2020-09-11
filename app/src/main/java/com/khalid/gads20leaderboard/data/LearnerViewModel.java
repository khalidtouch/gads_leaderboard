package com.khalid.gads20leaderboard.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.khalid.gads20leaderboard.web.HighLearnerResponse;

import java.util.List;

public class LearnerViewModel extends AndroidViewModel {

    private DataRepository mRepository;
    private LiveData<List<HighLearner>> highLearners;
    private LiveData<List<HighLearnerResponse>> learnersResponse;

    public LearnerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DataRepository(application);
        highLearners = mRepository.getHighLearners();
    }

    public void insert(HighLearner learner) {
        mRepository.insert(learner);
    }

    public void update(HighLearner learner) {
        mRepository.update(learner);
    }

    public void delete(HighLearner learner) {
        mRepository.delete(learner);
    }

    public void clearAll() {
        mRepository.clearAllLearners();
    }

    public LiveData<List<HighLearner>> getHighLearners(){
        return highLearners;
    }

    /**
     * web service
     */

    public void init() {
        learnersResponse = mRepository.getAllLearnersLiveData();
    }
    public LiveData<List<HighLearnerResponse>> getLearnersResponseLiveData() {
        return learnersResponse;
    }

    public void getAllLearners() {
        mRepository.getAllLearners();
    }

}
