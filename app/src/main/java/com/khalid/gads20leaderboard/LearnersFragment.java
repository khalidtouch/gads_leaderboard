package com.khalid.gads20leaderboard;

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
import android.widget.Toast;

import com.khalid.gads20leaderboard.data.DummyData;
import com.khalid.gads20leaderboard.data.HighLearner;
import com.khalid.gads20leaderboard.data.LearnerViewModel;
import com.khalid.gads20leaderboard.web.ApiClient;
import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.HighLearnerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearnersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HighLearner> mHighLearners = new ArrayList<>();
    private List<HighLearnerResponse> mHighLearnerResponses = new ArrayList<>();
    private static String TAG = "LearnersFragment";
    private LearnerViewModel mViewModel;
    private LearningRecyclerAdapter mAdapter;

    public LearnersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new LearningRecyclerAdapter(mHighLearnerResponses);
        mViewModel = ViewModelProviders.of(this).get(LearnerViewModel.class);
        mViewModel.init();
        mViewModel.getLearnersResponseLiveData().observe(this,
                new Observer<List<HighLearnerResponse>>() {
                    @Override
                    public void onChanged(List<HighLearnerResponse> highLearnerResponses) {
                        if (highLearnerResponses != null) {
                            mAdapter.setData(highLearnerResponses);
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learners, container,
                false);


        //DummyData repository = DummyData.getInstance();
       // mHighLearners = DummyData.getAllLearners();
        mRecyclerView = view.findViewById(R.id.learning_fragment_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreateView: starting layout manager");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onCreateView: completed recycler view integration");
        updateUI();

        return view;
    }

    public void updateUI(){
        mViewModel.getAllLearners();
    }
}