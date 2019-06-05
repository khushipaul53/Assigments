package com.example.assignment5.Service;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.assignment5.Database.DatabaseClass;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.R;

public class Async extends AsyncTask<Object,Void,Void> {

    private Context context;
    private SQLiteDatabase db;
    String oldIdofStudent;


    public Async(Context context) {
        this.context=context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//
        Toast.makeText(context, R.string.addtoDatabase, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Void doInBackground(Object... objects) {

        StudentList studentForDb = (StudentList) objects[0];
        String operationOnStudent = (String) objects[1];

        DatabaseClass dbHelper=new DatabaseClass(context);

        if(objects[2]!=null){
            oldIdofStudent = (String) objects[2];
        }



        switch (operationOnStudent){
            case "addIt":

                db=dbHelper.getWritableDatabase();
                dbHelper.addStudentinDb(studentForDb);
                db.close();
                break;

            case "updateIt":

                db=dbHelper.getWritableDatabase();
                dbHelper.updateStudentInDb(studentForDb,oldIdofStudent);
                db.close();
                break;
            case "deleteIt":
                db=dbHelper.getWritableDatabase();
                dbHelper.deleteStudentInDb(studentForDb);
                db.close();

            default:
                break;
        }
        return null;
    }
}

