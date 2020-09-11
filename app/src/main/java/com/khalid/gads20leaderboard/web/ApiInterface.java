package com.khalid.gads20leaderboard.web;

import com.khalid.gads20leaderboard.data.HighLearner;
import com.khalid.gads20leaderboard.data.HighSkiller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/hours")
    Call<List<HighLearnerResponse>> getHighLearners();

    @GET("/api/skilliq")
    Call<List<HighSkillerResponse>> getHighSkillers();
}
