package com.example.assignment5.Handler;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.assignment5.Database.DatabaseClass;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.Util.Constants;

public class Handlers {

    public void handleDb(Intent intent, Context context) {
        DatabaseClass databaseHelper = new DatabaseClass(context);
        databaseHelper.getWritableDatabase();
        String oldIdofStudent = new String();

        if(intent.hasExtra(Constants.OLD_ID_OF_STUDENT)) {

            oldIdofStudent = intent.getStringExtra(Constants.OLD_ID_OF_STUDENT);
        }

        String operationOnStudent = intent.getStringExtra(Constants.OPERATION);

        StudentList studentForDb = intent.getParcelableExtra(Constants.STUDENT_FOR_DB);


        switch (operationOnStudent) {
            case Constants.ADD_IT:

                databaseHelper.addStudentinDb(studentForDb);

                break;
            case Constants.UPDATE_IT:

                databaseHelper.updateStudentInDb(studentForDb,oldIdofStudent);
                break;
            case Constants.DELETE_IT:

                databaseHelper.deleteStudentInDb(studentForDb);
                break;
        }




        if(databaseHelper.isSuccess()) {

            intent.setAction(Constants.FILTER_ACTION_KEY);
            String echoMessage = Constants.BROADCAST;
            LocalBroadcastManager.getInstance(context).
                    sendBroadcast(intent.putExtra(Constants.BROADCAST_MESSAGE, echoMessage));
        }

    }
}