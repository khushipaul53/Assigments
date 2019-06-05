package com.example.assignment5.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.assignment5.Handler.Handlers;

public class Services extends Service {

    Handlers handler= new Handlers();

    public Services() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.handleDb(intent,this);
        stopSelf();

        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
