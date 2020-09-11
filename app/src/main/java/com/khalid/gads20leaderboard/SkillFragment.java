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

import com.khalid.gads20leaderboard.data.DummyData;
import com.khalid.gads20leaderboard.data.HighLearner;
import com.khalid.gads20leaderboard.data.HighSkiller;
import com.khalid.gads20leaderboard.data.LearnerViewModel;
import com.khalid.gads20leaderboard.data.SkillerVIewModel;
import com.khalid.gads20leaderboard.web.HighLearnerResponse;
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.ArrayList;
import java.util.List;

public class SkillFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HighSkiller> mHighSkillers = new ArrayList<>();
    private List<HighSkillerResponse> mHighSkillerResponses = new ArrayList<>();
    private static String TAG = "SkillFragment";
    private SkillerVIewModel mViewModel;
    private SkillerRecyclerAdapter mAdapter;

    public SkillFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new SkillerRecyclerAdapter(mHighSkillerResponses);
        mViewModel = ViewModelProviders.of(this).get(SkillerVIewModel.class);
        mViewModel.init();
        mViewModel.getSkillerResponseLiveData().observe(this,
                new Observer<List<HighSkillerResponse>>() {
                    @Override
                    public void onChanged(List<HighSkillerResponse> highSkillerResponses) {
                        if (highSkillerResponses != null) {
                            mAdapter.setData(highSkillerResponses);
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);


        mRecyclerView = view.findViewById(R.id.skilling_fragment_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreateView: starting layout manager");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onCreateView: completed recycler view integration");

        updateUI();

        return view;
    }

    public void updateUI(){
        mViewModel.getAllSkillers();
    }
}