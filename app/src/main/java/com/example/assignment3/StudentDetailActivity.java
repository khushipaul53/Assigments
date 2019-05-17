package com.example.assignment3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailActivity extends AppCompatActivity {


    Intent intent = getIntent();
    Context mcontext;
    EditText etName, etRollno, etClss;
    String mname, mrollno, mClss;

    // StudentListAdapter studentAdapter;

    HelperClass helper;



    //Intent intent=getIntent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        etName = findViewById(R.id.et_name_studentDetails);
        etRollno = findViewById(R.id.et_rollno_studentDetails);
        etClss = findViewById(R.id.et_class_studentDetails);
        Button btnSubmit = findViewById(R.id.btn_submit_studentDetails);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {


            mname = bundle.getString("NAME");
            mrollno = bundle.getString("ROLLNO");
            mClss = bundle.getString("CLASS");

            if (bundle.getString("ACTION").equals("EDIT"))
            {
                etName.setText(mname);
                etRollno.setText(mrollno);
               etClss.setText(mClss);

            }
            else {

                etName.setText(mname);
                etName.setEnabled(false);
                etRollno.setText(mrollno);
                etRollno.setEnabled(false);
                etClss.setText(mClss);
                etClss.setEnabled(false);
                btnSubmit.setVisibility(View.INVISIBLE);
            }


        }


//
//            if (intent!= null) {
//                mname = intent.getStringExtra("name");
//                mrollno = intent.getStringExtra("rollno");
//                mClss = intent.getStringExtra("Class");
//
//            }
//
//            etName.setText(mname);
//            etName.setEnabled(false);
//            etRollno.setText(mrollno);
//            etRollno.setEnabled(false);
//            etClss.setText(mClss);
//            etClss.setEnabled(false);
//
//        }

        helper = new HelperClass();


        mcontext = getApplicationContext();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mname = etName.getText().toString();
                mrollno = etRollno.getText().toString();
                mClss = etClss.getText().toString();

                if (helper.Validations(mcontext, mname, mrollno, mClss)) {
                    Intent intent = new Intent();
                    intent.putExtra("NAME", mname);
                    intent.putExtra("ROLLNO", mrollno);
                    intent.putExtra("CLASS", mClss);
                    setResult(Activity.RESULT_OK, intent);
                    finish();


                }
//                Intent IntentbtnSubmit=new Intent(StudentDetailActivity.this,StudentListActivity.class);
//                startActivityForResult(IntentbtnSubmit,0);

            }


        });


    }
}











