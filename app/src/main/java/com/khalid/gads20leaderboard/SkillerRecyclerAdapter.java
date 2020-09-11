package com.khalid.gads20leaderboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khalid.gads20leaderboard.data.HighLearner;
import com.khalid.gads20leaderboard.data.HighSkiller;
import com.khalid.gads20leaderboard.web.HighSkillerResponse;

import java.util.List;

public class SkillerRecyclerAdapter extends RecyclerView.Adapter<SkillerRecyclerAdapter.SkillerViewHolder> {

    private List<HighSkiller> mHighSkillers;
    private List<HighSkillerResponse> mHighSkillerResponses;
    private static String TAG = "adapter";
    private boolean mIsLocal;

    public SkillerRecyclerAdapter(List<HighSkiller> highSkillers, boolean isLocal) {
        mHighSkillers = highSkillers;
        mIsLocal = isLocal;
    }
    public SkillerRecyclerAdapter(List<HighSkillerResponse> highSkillerResponses) {
        mHighSkillerResponses = highSkillerResponses;
    }

    @NonNull
    @Override
    public SkillerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skillers_item_layout,
                parent, false);
        Log.d(TAG, "onCreateViewHolder: initialized all parameters");
        return new SkillerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Binding views to recycler view");
        HighSkillerResponse skiller = mHighSkillerResponses.get(position);
        holder.mImageView.setImageResource(skiller.getPlaceHolderImageUrl());
        holder.mDevName.setText(skiller.getDevName());
        holder.mDevDetails.setText(skiller.getCountry());
        Log.d(TAG, "onBindViewHolder: view binded");
    }

    @Override
    public int getItemCount() {
        return mHighSkillerResponses.size();
    }

    public void setData(List<HighSkillerResponse> skillerResponses) {
        mHighSkillerResponses = skillerResponses;
        notifyDataSetChanged();
    }


    public class SkillerViewHolder extends RecyclerView.ViewHolder {
        private TextView mDevName, mDevDetails;
        private ImageView mImageView;

        public SkillerViewHolder(@NonNull View itemView) {
            super(itemView);
            mDevName = itemView.findViewById(R.id.s_developer_name);
            mDevDetails = itemView.findViewById(R.id.s_developer_details);
            mImageView = itemView.findViewById(R.id.s_learning_image);
        }

    }
}
