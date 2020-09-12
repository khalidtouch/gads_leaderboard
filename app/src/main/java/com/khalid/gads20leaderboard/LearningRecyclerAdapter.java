package com.khalid.gads20leaderboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.khalid.gads20leaderboard.web.HighLearnerResponse;

import java.util.List;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.LearningViewHolder> {


    private List<HighLearnerResponse> mHighLearnerResponses;
    private static String TAG = "adapter";
    private Context mContext;
    private boolean mIsLocal;

    public LearningRecyclerAdapter(List<HighLearnerResponse> highLearnersResponses) {
        mHighLearnerResponses = highLearnersResponses;
    }


    @NonNull
    @Override
    public LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learners_item_layout,
                parent, false);
        mContext = parent.getContext();
        Log.d(TAG, "onCreateViewHolder: initialized all parameters");
        return new LearningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Binding views to recycler view");
        HighLearnerResponse learner = mHighLearnerResponses.get(position);
        if(learner.getBadgeUrl() == null) {
            holder.mImageView.setImageResource(learner.getPlaceHolderImageUrl());
        } else {
            Glide.with(mContext).load(learner.getBadgeUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.mImageView);
        }
        Log.d(TAG, "onBindViewHolder: image loaded, processing subtitle");
        String subtitle = learner.getHours() + " Learning hours, " + learner.getCountry();
        holder.mDevName.setText(learner.getDevName());
        holder.mDevDetails.setText(subtitle);
        Log.d(TAG, "onBindViewHolder: view binded");
    }

    @Override
    public int getItemCount() {
        return mHighLearnerResponses.size();
    }

    public void setData(List<HighLearnerResponse> learners) {
        mHighLearnerResponses = learners;
        notifyDataSetChanged();
    }

    public static class LearningViewHolder extends RecyclerView.ViewHolder {
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
