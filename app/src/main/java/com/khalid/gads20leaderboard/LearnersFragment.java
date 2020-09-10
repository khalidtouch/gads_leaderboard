package com.khalid.gads20leaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class LearnersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HighLearner> mHighLearners = new ArrayList<>();
    private static String TAG = "LearnersFragment";

    public LearnersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learners, container,
                false);
        // setup recyclerview
        DataRepository repository = DataRepository.getInstance();
        mHighLearners = DataRepository.getAllLearners();
        mRecyclerView = view.findViewById(R.id.learning_fragment_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreateView: starting layout manager");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        LearningRecyclerAdapter adapter = new LearningRecyclerAdapter(mHighLearners);
        mRecyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreateView: completed recycler view integration");
        return view;
    }
}