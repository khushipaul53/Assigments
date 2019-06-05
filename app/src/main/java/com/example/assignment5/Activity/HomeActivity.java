package com.example.assignment5.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.assignment5.Adapter.PagerViewAdapter;
import com.example.assignment5.Comunicator.Comunicate;
import com.example.assignment5.Fragment.HomeFragment;
import com.example.assignment5.Fragment.StudentAddFragment;
import com.example.assignment5.R;

public class HomeActivity extends AppCompatActivity implements Comunicate {


    private ViewPager viewPager;
    private PagerViewAdapter fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void init() {
        viewPager = findViewById(R.id.id_viewPager);
        fragmentAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabLayout = findViewById(R.id.id_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void changeTab() {
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(1);
        } else if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        }
    }
    @Override
    public void Add(Bundle bundle) {

        String tag = getString(R.string.tag) + R.id.id_viewPager + ":" + 0;
        HomeFragment studentListFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(tag);
        assert studentListFragment != null;
        studentListFragment.addOrUpdateStudentInList(bundle);
        changeTab();
    }



    @Override
    public void Update(Bundle bundle) {
        String tag = getString(R.string.tag) + R.id.id_viewPager + ":" + 1;
        StudentAddFragment addStudentFragment = (StudentAddFragment) getSupportFragmentManager().findFragmentByTag(tag);
        assert addStudentFragment != null;
        addStudentFragment.addOrUpdateStudentInCreateStudentFragment(bundle);
        changeTab();
    }

    public void onBackPressed() {

        if (viewPager.getCurrentItem() >= 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        } else {
            super.onBackPressed();
        }

    }
}

