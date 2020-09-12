package com.khalid.gads20leaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.ApiService;
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HighSkillerResponse> mHighSkillerResponses = new ArrayList<>();
    private static String TAG = "SkillFragment";


    public SkillFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);


        mRecyclerView = view.findViewById(R.id.skilling_fragment_recycler_view);
        SkillerRecyclerAdapter adapter = new SkillerRecyclerAdapter(mHighSkillerResponses);
        Log.d(TAG, "onCreateView: starting layout manager");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreateView: completed recycler view integration");

        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);

        Call<List<HighSkillerResponse>> call = apiInterface.getHighSkillers();
        call.enqueue(new Callback<List<HighSkillerResponse>>() {
            @Override
            public void onResponse(Call<List<HighSkillerResponse>> call,
                                   Response<List<HighSkillerResponse>> response) {
                mHighSkillerResponses = response.body();
                Log.d(TAG, "onResponse: Response started");
                adapter.setData(mHighSkillerResponses);
            }

            @Override
            public void onFailure(Call<List<HighSkillerResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });


        return view;
    }

}