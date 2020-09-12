package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    public static String[] mChildFragmentNames;
    public static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mChildFragmentNames = new String[2];
        mChildFragmentNames = getResources().getStringArray(R.array.child_fragment_names);
        Button submitBtn = this.findViewById(R.id.submit_btn_one);
        ViewPager pager = this.findViewById(R.id.main_pager);
        TabLayout tabLayout = this.findViewById(R.id.tabLayout);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open Submission Activity
                Intent intent = new Intent(HomeActivity.this, SubmissionActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });


    }

}