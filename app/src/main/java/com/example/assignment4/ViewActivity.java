package com.example.assignment4;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Fragments.ViewFragment;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        inflateFragment();

    }

    private void inflateFragment() {
        Intent intent=new Intent();
        Bundle bundle=intent.getExtras();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ViewFragment viewFragment = ViewFragment.newInstance(bundle);
        fragmentTransaction.add(R.id.view_ll,viewFragment);
        fragmentTransaction.commit();
    }
}
