package com.khalid.gads20leaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mChildFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mChildFragments = new Fragment[] {
          new LearnersFragment(),
          new SkillFragment()
        };
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mChildFragments[position];
    }

    @Override
    public int getCount() {
        return mChildFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HomeActivity.mChildFragmentNames[position];
    }
}
