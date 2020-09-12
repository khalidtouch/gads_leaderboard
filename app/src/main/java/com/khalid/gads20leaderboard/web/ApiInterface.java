package com.khalid.gads20leaderboard.web;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/api/hours")
    Call<List<HighLearnerResponse>> getHighLearners();

    @GET("/api/skilliq")
    Call<List<HighSkillerResponse>> getHighSkillers();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<SubmissionRequest> submit(
      @Field("entry.1824927963") String email,
      @Field("entry.1877115667") String name,
      @Field("entry.2006916086") String lastName,
      @Field("entry.284483984") String link
    );
}
