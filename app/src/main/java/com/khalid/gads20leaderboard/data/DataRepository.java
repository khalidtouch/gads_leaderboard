package com.khalid.gads20leaderboard.data;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.HighLearnerResponse;
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    private LearnerDao mLearnerDao;
    private SkillDao mSkillDao;
    private LiveData<List<HighLearner>> highLearners;
    private LiveData<List<HighSkiller>> highSkillers;


    private static final String BASE_URL = "https://gadsapi.herokuapp.com";
    private MutableLiveData<List<HighLearnerResponse>> learnerResponse;
    private MutableLiveData<List<HighSkillerResponse>> skillerResponse;
    private ApiInterface mApiInterface;

    public DataRepository(Application application) {
        Database database = Database.getInstance(application);
        mLearnerDao = database.getLearningDao();
        highLearners = mLearnerDao.getLearners();
        mSkillDao = database.getSkillDao();
        highSkillers = mSkillDao.getSkillers();

        initiateServiceOne();
        initiateServiceTwo();
    }


    /**
     * web service
     */

    private void initiateServiceOne() {
        learnerResponse = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        mApiInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    private void initiateServiceTwo() {
        skillerResponse = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        mApiInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    public void getAllLearners() {
        mApiInterface.getHighLearners()
                .enqueue(new Callback<List<HighLearnerResponse>>() {
                    @Override
                    public void onResponse(Call<List<HighLearnerResponse>> call, Response<List<HighLearnerResponse>> response) {
                        learnerResponse.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<HighLearnerResponse>> call, Throwable t) {
                        learnerResponse.postValue(null);
                    }
                });
    }

    public void getAllSkillers() {
        mApiInterface.getHighSkillers()
                .enqueue(new Callback<List<HighSkillerResponse>>() {
                    @Override
                    public void onResponse(Call<List<HighSkillerResponse>> call,
                                           Response<List<HighSkillerResponse>> response) {
                        skillerResponse.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<HighSkillerResponse>> call, Throwable t) {
                        learnerResponse.postValue(null);
                    }
                });
    }

    public LiveData<List<HighLearnerResponse>> getAllLearnersLiveData() {
        return learnerResponse;
    }

    public LiveData<List<HighSkillerResponse>> getAllSkillersLiveData() {
        return skillerResponse;
    }




    /**
     * section for top learners
     */
    public void insert(HighLearner learner){
        new InsertLearnerTask(mLearnerDao).execute(learner);
    }

    public void update(HighLearner learner){
        new UpdateLearnerTask(mLearnerDao).execute(learner);
    }

    public void delete(HighLearner learner){
        new DeleteLearnerTask(mLearnerDao).execute(learner);
    }

    public void clearAllLearners(){
        new ClearAllLearnersTask(mLearnerDao).execute();
    }

    public LiveData<List<HighLearner>> getHighLearners() {
        return highLearners;
    }

    private static class InsertLearnerTask extends AsyncTask<HighLearner, Void, Void> {
        private LearnerDao mDao;
        public InsertLearnerTask(LearnerDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(HighLearner... highLearners) {
            mDao.insert(highLearners[0]);
            return null;
        }
    }

    private static class UpdateLearnerTask extends AsyncTask<HighLearner, Void, Void> {
        private LearnerDao mDao;

        public UpdateLearnerTask(LearnerDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(HighLearner... highLearners) {
            mDao.update(highLearners[0]);
            return null;
        }
    }

    private static class DeleteLearnerTask extends AsyncTask<HighLearner, Void, Void> {
        private LearnerDao mDao;
        public DeleteLearnerTask(LearnerDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(HighLearner... highLearners) {
            mDao.delete(highLearners[0]);
            return null;
        }

    }

    private static class ClearAllLearnersTask extends AsyncTask<Void, Void, Void> {
        private LearnerDao mDao;
        public ClearAllLearnersTask(LearnerDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mDao.clearAll();
            return null;
        }
    }

    /**
     * section for high skill-ers
     */

    public void insert(HighSkiller skiller){
        new InsertHighSkillersTask(mSkillDao).execute(skiller);
    }

    public void update(HighSkiller skiller){
        new UpdateHighSkillersTask(mSkillDao).execute(skiller);
    }

    public void delete(HighSkiller skiller){
        new DeleteHighSkillerTask(mSkillDao).execute(skiller);
    }

    public void clearAllSkillers(){
        new ClearAllSkillersTask(mSkillDao).execute();
    }

    public LiveData<List<HighSkiller>> getHighSkillers() {
        return highSkillers;
    }

    private static class InsertHighSkillersTask extends AsyncTask<HighSkiller, Void, Void> {
        private SkillDao mDao;
        public InsertHighSkillersTask(SkillDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(HighSkiller... highSkillers) {
            mDao.insert(highSkillers[0]);
            return null;
        }
    }

    private static class UpdateHighSkillersTask extends AsyncTask<HighSkiller, Void, Void> {
        private SkillDao mDao;

        public UpdateHighSkillersTask(SkillDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(HighSkiller... highSkillers) {
            mDao.update(highSkillers[0]);
            return null;
        }
    }

    private static class DeleteHighSkillerTask extends AsyncTask<HighSkiller, Void, Void> {
        private SkillDao mDao;
        public DeleteHighSkillerTask(SkillDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(HighSkiller... highSkillers) {
            mDao.delete(highSkillers[0]);
            return null;
        }

    }

    private static class ClearAllSkillersTask extends AsyncTask<Void, Void, Void> {
        private SkillDao mDao;
        public ClearAllSkillersTask(SkillDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mDao.clearAll();
            return null;
        }
    }

}
