package com.example.assignment5.Dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.assignment5.Model.StudentList;
import com.example.assignment5.Service.Async;
import com.example.assignment5.Service.IntentServices;
import com.example.assignment5.Service.Services;
import com.example.assignment5.Util.Constants;

public class GenerateDialog {

    Context mContext;


    public GenerateDialog(Context mContext) {
        this.mContext = mContext;
    }


    public void generateDialogOnTouch(final StudentList studentToHandle, final String operationOnStudent) {

        final String[] items = {"Service","Intent Services","Async"};
        final int useService = 0, useIntentService = 1, useAsyncTasks = 2;

        //Alert Dialog that has context of this activity.
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Choose Database");
        //Sets the items of the Dialog.
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                switch (which) {
                    case useService:

                        Intent forService = new Intent(mContext,
                                Services.class);
                        forService.putExtra(Constants.STUDENT_FOR_DB, studentToHandle);
                        forService.putExtra(Constants.OPERATION,operationOnStudent);
                        mContext.startService(forService);
                        break;


                    case useIntentService:

                        Intent forIntentService = new Intent(mContext,
                                IntentServices.class);
                        forIntentService.putExtra(Constants.STUDENT_FOR_DB, studentToHandle);
                        forIntentService.putExtra(Constants.OPERATION,operationOnStudent);
                        mContext.startService(forIntentService);

                        break;

                    case useAsyncTasks:
                        Async backgroundAsyncTasks = new Async(mContext);
                        backgroundAsyncTasks.execute(studentToHandle,operationOnStudent,null);

                        break;
                    default:
                        break;
                }


            }
        });
        AlertDialog mAlert = builder.create();
        mAlert.show();

    }



}
