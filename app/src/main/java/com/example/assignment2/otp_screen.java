package com.example.assignment2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class otp_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        final View parentLayout = findViewById(android.R.id.content);

        final EditText otp_1 = findViewById(R.id.ed_otp_1);
        final EditText otp_2 = findViewById(R.id.ed_otp_2);
        final EditText otp_3 = findViewById(R.id.ed_otp_3);
        final EditText otp_4 = findViewById(R.id.ed_otp_4);
        Button btn_submit=findViewById(R.id.btn_submit);
        Button btn_resend=findViewById(R.id.btn_resend);


        //on clicking on the resend button it will show the snackbar meassage of otp send and clear
        // //the opt typed in the edit texts.and the cursor will come in first position


        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar snackbar = Snackbar.make(parentLayout, "OTP sent again!!", Snackbar.LENGTH_LONG);
                snackbar.show();
                otp_1.getText().clear();
                otp_2.getText().clear();
                otp_3.getText().clear();
                otp_4.getText().clear();
                otp_1.requestFocus();

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                } catch (Exception e) {

                }


            }

        });




        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otp1=otp_1.getText().toString();
                String otp2=otp_2.getText().toString();
                String otp3=otp_3.getText().toString();
                String otp4=otp_4.getText().toString();

                if(otp1.length()==0)
                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Wrong Input", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else if(otp2.length()==0)
                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Wrong Input", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else if(otp3.length()==0) {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Wrong Input", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else if(otp4.length()==0)
                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Wrong Input", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else
                {
                    Intent submitIntent=new Intent(otp_screen.this,Login_Screen.class);
                    startActivityForResult(submitIntent,0);

                }
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                } catch (Exception e) {

                }

            }
        });

                otp_1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (otp_1.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    otp_2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        });
        otp_2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (otp_2.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    otp_3.requestFocus();
                }

                else if (otp_2.getText().toString().trim().length() == 0) {
                    otp_1.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        otp_3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (otp_3.getText().toString().trim().length() == 1) {
                    otp_4.requestFocus();
                } else if (otp_3.getText().toString().trim().length() == 0) {
                    otp_2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        otp_4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (otp_4.getText().toString().trim().length() == 0) {
                    otp_3.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
    }
}