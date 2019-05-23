package com.example.Util;

import android.content.Context;
import android.widget.Toast;

import com.example.assignment4.R;
import com.example.assignment4.StudentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HelperClass

{

    public boolean Validations(Context context, String name, String rollno, String Class)
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


}
