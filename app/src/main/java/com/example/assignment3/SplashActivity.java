package com.example.assignment3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Splash screen is used so that after 3 seconds the activity will move to next activity.
        //Handler is used for setting timmer.

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {



                Intent IntentStudentList=new Intent(SplashActivity.this,StudentListActivity.class);
                startActivityForResult(IntentStudentList,0);

            }
        },3000);

    }
}
