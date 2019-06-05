package com.example.assignment5.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.assignment5.Fragment.HomeFragment;
import com.example.assignment5.Fragment.StudentAddFragment;

public class PagerViewAdapter extends FragmentPagerAdapter {

    private String title[] = {"Student List","Add Student Details"};
    FragmentManager ffm;

    public PagerViewAdapter(FragmentManager fm){
        super(fm);
        ffm=fm;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();

            case 1:
                return new StudentAddFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
