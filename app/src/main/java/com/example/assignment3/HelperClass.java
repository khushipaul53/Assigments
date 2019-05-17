package com.example.assignment3;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HelperClass {


    public boolean Validations(Context context,String name, String rollno, String Class)
    {
        if(name.length()==0)
        {
            Toast.makeText(context,R.string.helperClass_toastName, Toast.LENGTH_SHORT).show();
            return false;


        }
       else if(rollno.length()==0)
        {
            Toast.makeText(context,R.string.helperClass_toastRoll, Toast.LENGTH_SHORT).show();
            return false;

        }
       else if(Class.length()==0)
        {
            Toast.makeText(context,R.string.helperClass_toastClass, Toast.LENGTH_SHORT).show();
            return false;

          }
       else

           return true;

    }

//    public void compareName(ArrayList<StudentList> arrayList)
//    {
//        Collections.sort(arrayList,new Comparator<StudentList>() {
//            @Override
//
//            public int compare(StudentList student1, StudentList student2) {
//                return (student2.getName().compareTo(student1.getName()));
//            }
//        });

    public static Comparator<StudentList> compareName(){
        Comparator comp = new Comparator<StudentList>() {
            @Override
            public int compare(StudentList o1, StudentList o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        return comp;
    }
     public void compareId(ArrayList<StudentList>arrayList)
    {
        Collections.sort(arrayList, new Comparator<StudentList>() {
            @Override
            public int compare(StudentList student1, StudentList student2) {
                return student1.getRollNo().compareTo(student2.getRollNo());
            }
        });
    }
}
