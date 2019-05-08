package com.example.assignment2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//Splash screen is used so that after 3 seconds the activity will move to next activity.
        //Handler is used for setting timmer.

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //for moving from main activity to login activity

                Intent login_screen=new Intent(MainActivity.this,Login_Screen.class);
                startActivityForResult(login_screen,0);
                MainActivity.this.finish();
            }
        }, 3000); //3 seconds



    }
}
