package com.example.assignment5.Service;

import android.app.IntentService;
import android.content.Intent;

import com.example.assignment5.Handler.Handlers;

public class IntentServices extends IntentService {

    Handlers handler= new Handlers();


    public IntentServices(String name) {
        super(name);
    }

    public IntentServices() {
        super("nothing");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        handler.handleDb(intent,this);

    }


}