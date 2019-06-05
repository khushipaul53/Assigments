package com.example.assignment5.BroadcasrtRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.assignment5.R;


public class  BroadcastRecivers  extends BroadcastReceiver {

        private Context acitivityContext;

        public BroadcastRecivers() {

            //required empty constructor.
        }

        public BroadcastRecivers(Context acitivityContext) {
            this.acitivityContext = acitivityContext;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(acitivityContext,R.string.update,Toast.LENGTH_SHORT).show();

        }

    }

