package com.example.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerViewAdapter extends FragmentStatePagerAdapter{

        private List<Fragment> fragmentsList;
        private List<String> fragmentName;

        public PagerViewAdapter( FragmentManager fm, List<Fragment>fragments, List<String>fragmentName) {
            super(fm);
            this.fragmentsList = fragments;
            this.fragmentName=fragmentName;
        }

        @Override
        public Fragment getItem(final int position) {
            return this.fragmentsList.get(position);
        }
        @Override
        public int getCount() {
            if (fragmentsList == null) {
                return 0;
            } else {
                return fragmentsList.size();
            }
        }
        public CharSequence getPageTitle(int position)
        {
            return this.fragmentName.get(position);
        }
    }


