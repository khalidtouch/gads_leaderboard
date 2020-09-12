package com.khalid.gads20leaderboard;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.ApiService;
import com.khalid.gads20leaderboard.web.HighLearnerResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearnersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HighLearnerResponse> mHighLearnerResponses = new ArrayList<>();
    private static String TAG = "LearnersFragment";

    public LearnersFragment() {
        // Required skempty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learners, container,
                false);


        mRecyclerView = view.findViewById(R.id.learning_fragment_recycler_view);
        LearningRecyclerAdapter adapter = new LearningRecyclerAdapter(mHighLearnerResponses);
        Log.d(TAG, "onCreateView: starting layout manager");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreateView: completed recycler view integration");

        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);

        Call<List<HighLearnerResponse>> call = apiInterface.getHighLearners();
        call.enqueue(new Callback<List<HighLearnerResponse>>() {
            @Override
            public void onResponse(Call<List<HighLearnerResponse>> call,
                                   Response<List<HighLearnerResponse>> response) {
                mHighLearnerResponses = response.body();
                Log.d(TAG, "onResponse: Response started");
                adapter.setData(mHighLearnerResponses);
            }

            @Override
            public void onFailure(Call<List<HighLearnerResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return view;
    }




}