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
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.List;

public class SkillerRecyclerAdapter extends RecyclerView.Adapter<SkillerRecyclerAdapter.SkillViewHolder> {


    private List<HighSkillerResponse> mHighSkillerResponses;
    private static String TAG = "adapter";
    private Context mContext;
    private boolean mIsLocal;

    public SkillerRecyclerAdapter(List<HighSkillerResponse> highSkillerResponses) {
        mHighSkillerResponses = highSkillerResponses;
    }


    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skillers_item_layout,
                parent, false);
        mContext = parent.getContext();
        Log.d(TAG, "onCreateViewHolder: initialized all parameters");
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Binding views to recycler view");
        HighSkillerResponse skiller = mHighSkillerResponses.get(position);
        if(skiller.getBadgeUrl() == null) {
            holder.mImageView.setImageResource(skiller.getPlaceHolderImageUrl());
        } else {
            Glide.with(mContext).load(skiller.getBadgeUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.mImageView);
        }
        Log.d(TAG, "onBindViewHolder: image loaded, processing subtitle");
        String subtitle = skiller.getScore() + " skill IQ Score, " + skiller.getCountry();
        holder.mDevName.setText(skiller.getDevName());
        holder.mDevDetails.setText(subtitle);
        Log.d(TAG, "onBindViewHolder: view binded");
    }

    @Override
    public int getItemCount() {
        return mHighSkillerResponses.size();
    }

    public void setData(List<HighSkillerResponse> skillers) {
        mHighSkillerResponses = skillers;
        notifyDataSetChanged();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        private TextView mDevName, mDevDetails;
        private ImageView mImageView;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            mDevName = itemView.findViewById(R.id.s_developer_name);
            mDevDetails = itemView.findViewById(R.id.s_developer_details);
            mImageView = itemView.findViewById(R.id.s_learning_image);
        }

    }
}
