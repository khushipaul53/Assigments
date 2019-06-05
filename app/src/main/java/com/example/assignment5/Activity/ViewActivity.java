package com.example.assignment5.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.assignment5.Comunicator.Comunicate;

import com.example.assignment5.Fragment.StudentAddFragment;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.R;
import com.example.assignment5.Util.Constants;

public class ViewActivity extends AppCompatActivity implements Comunicate {
    private StudentAddFragment mStudentAddFragment;
    StudentList studentTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);
        studentTemplate=getIntent().getParcelableExtra(Constants.THISSTUDENT);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        mStudentAddFragment=new StudentAddFragment();
        fragmentTransaction.add(R.id.view_ll,mStudentAddFragment,"");
        fragmentTransaction.commit();


    }
    @Override
    protected void onStart() {
        super.onStart();
        mStudentAddFragment.viewMode(studentTemplate);
    }

    @Override
    public void Add(Bundle bundle) {

    }

    @Override
    public void Update(Bundle bundle) {

    }

}

