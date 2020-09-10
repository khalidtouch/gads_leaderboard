package com.khalid.gads20leaderboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.LearningViewHolder> {

    private List<HighLearner> mHighLearners;
    private static String TAG = "adapter";

    public LearningRecyclerAdapter(List<HighLearner> highLearners) {
        mHighLearners = highLearners;
    }

    @NonNull
    @Override
    public LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learners_item_layout,
                parent, false);
        Log.d(TAG, "onCreateViewHolder: initialized all parameters");
        return new LearningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Binding views to recycler view");
        HighLearner learner = mHighLearners.get(position);
        holder.mImageView.setImageResource(learner.getImageURL());
        holder.mDevName.setText(learner.getDevName());
        holder.mDevDetails.setText(learner.getDevDetails());
        Log.d(TAG, "onBindViewHolder: view binded");
    }

    @Override
    public int getItemCount() {
        return mHighLearners.size();
    }

    public class LearningViewHolder extends RecyclerView.ViewHolder {
        private TextView mDevName, mDevDetails;
        private ImageView mImageView;

        public LearningViewHolder(@NonNull View itemView) {
            super(itemView);
            mDevName = itemView.findViewById(R.id.developer_name);
            mDevDetails = itemView.findViewById(R.id.developer_details);
            mImageView = itemView.findViewById(R.id.learning_image);
        }

    }
}
