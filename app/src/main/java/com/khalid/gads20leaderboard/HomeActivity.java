package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    public static String[] mChildFragmentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mChildFragmentNames = new String[2];
        mChildFragmentNames = getResources().getStringArray(R.array.child_fragment_names);

        ViewPager pager = this.findViewById(R.id.main_pager);
        TabLayout tabLayout = this.findViewById(R.id.tabLayout);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);

        }
}