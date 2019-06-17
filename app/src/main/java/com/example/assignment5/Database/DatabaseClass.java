package com.example.assignment5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignment5.Model.StudentList;

import java.util.ArrayList;

public class DatabaseClass extends SQLiteOpenHelper {

    private static final String STUDENT_DB ="student.db";
    private static final String STUDENT_TABLE ="student_table";
    private static final int DATABASE_VERSION = 1;

    /* Declare Columns of the Table. */
    private static final String COL_1_STUDENT_ROLL = "_roll";
    private static final String COL_2_STUDENT_NAME = "name";
    private static final String COL_3_STUDENT_CLASS = "Class";

    private boolean success = false;



    public DatabaseClass(Context context) {

        super(context, STUDENT_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreateDb = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE +
                "(" + COL_1_STUDENT_ROLL + " BLOB PRIMARY KEY, "
                + COL_2_STUDENT_NAME + " BLOB , "+ COL_3_STUDENT_CLASS + ");" +
                "";
        db.execSQL(queryCreateDb);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);

    }

    public void addStudentinDb (StudentList student) throws SQLiteException {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_STUDENT_NAME, student.getName());
        values.put(COL_1_STUDENT_ROLL, student.getRollno());
        values.put(COL_3_STUDENT_CLASS, student.getmStandard());
        db.insert(STUDENT_TABLE, null, values);
        success = true;
        db.close();
    }


    public void deleteStudentInDb (StudentList studentTemplate) throws SQLiteException {

        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(STUDENT_TABLE, COL_1_STUDENT_ROLL + "=?", new String[]{studentTemplate.getRollno()});
        success = true;
        db.close();

    }




    public boolean updateStudentInDb (StudentList studentToUpdate, String oldRollId) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_STUDENT_NAME,studentToUpdate.getName());
        values.put(COL_1_STUDENT_ROLL,studentToUpdate.getRollno());
        values.put(COL_3_STUDENT_CLASS,studentToUpdate.getmStandard());


        try {

            db.update(STUDENT_TABLE, values, COL_1_STUDENT_ROLL + "=?",
                    new String[]{oldRollId});
            success = true;
            db.close();


            return true;
        } catch (SQLiteException e) {
            db.close();
            return false;
        }


    }


    public ArrayList<StudentList> refreshStudentListfromDb() {
        ArrayList<StudentList> listToInflate= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + STUDENT_TABLE+ ";";

        Cursor cursor = db.rawQuery(query,null);

        String Name = new String();
        String Rollno= new String();
        String Class = new String();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Rollno = cursor.getString(cursor.getColumnIndex(COL_1_STUDENT_ROLL));
            Name = cursor.getString(cursor.getColumnIndex(COL_2_STUDENT_NAME));
            Class = cursor.getString(  cursor.getColumnIndex(COL_3_STUDENT_CLASS));
            StudentList studentToShow = new StudentList(Name,Rollno,Class);

            listToInflate.add(studentToShow);

        }
        success = true;
        cursor.close();
        db.close();
        Log.e("Count",""+listToInflate.size());
        return listToInflate;

    }
    public StudentList StudentAvailable(String thisRollId) throws SQLiteException {
        SQLiteDatabase db = this.getReadableDatabase();
        StudentList studentToAdd = new StudentList();
        String query = "SELECT * FROM " + STUDENT_TABLE + " WHERE "
                + COL_1_STUDENT_ROLL + " = '" + String.valueOf(thisRollId) + "'";


        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            studentToAdd.setName(cursor.getString(cursor.getColumnIndex(COL_2_STUDENT_NAME)));
            studentToAdd.setRollno(cursor.getString(cursor.getColumnIndex(COL_1_STUDENT_ROLL)));
            studentToAdd.setStandard(cursor.getString(cursor.getColumnIndex(COL_3_STUDENT_CLASS)));

        }
        success = true;
        return studentToAdd;
    }

    public boolean isSuccess() {
        return success;
    }

}




