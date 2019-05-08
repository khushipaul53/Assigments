package com.example.assignment2;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeration_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_screen);


        //ids of edit text
        final View parentLayout = findViewById(android.R.id.content);
        final EditText register_name=findViewById(R.id.register_name);
        final EditText register_gender=findViewById(R.id.register_gender);
        final EditText register_usertype=findViewById(R.id.register_usertype);
        final EditText register_occupation=findViewById(R.id.register_occupation);
        final ImageView btn_back=findViewById(R.id.register_back);


        //here back button is used to go back to login screen from registeration screen
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){


//                Intent signupIntent=new Intent(Registeration_screen.this,Login_Screen.class);
//                startActivity(signupIntent);
                //onBackButton(btn_back);
                finish();
            }
        });



        TextView Signup=findViewById(R.id.tv_onclick_signup);

        Signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){

                Intent signupIntent=new Intent(Registeration_screen.this,Login_Screen.class);
                startActivityForResult(signupIntent,0);
            }
        });



      Button continue_otp=findViewById(R.id.button_otp);







      //this is for checking validation for all fields and to move from registeration screen to otp screen
        continue_otp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View View)
            {
                String name=register_name.getText().toString();
                String gender=register_gender.getText().toString().toLowerCase();
                String user_type=register_usertype.getText().toString();
                String occupation=register_occupation.getText().toString();

                if(name.length()==0)
                {

                    Snackbar snackbar = Snackbar.make(parentLayout, "PLease Enter Your Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else  if(gender.length()==0)
                {

                    Snackbar snackbar = Snackbar.make(parentLayout, "PLease Enter Gender", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if((gender.equals("female"))==false && (gender.equals("male"))==false)

                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "PLease enter Valid Gender", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }


                else if(user_type.length()==0)
                {

                    Snackbar snackbar = Snackbar.make(parentLayout, "PLease enter user Type", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

               else if(occupation.length()==0)
                {

                    Snackbar snackbar = Snackbar.make(parentLayout, "PLease enter Occupation", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


               else
                {


                    Intent otpIntent=new Intent(Registeration_screen.this,otp_screen.class);
                    startActivityForResult(otpIntent,0);
                }







            }


        });





    }
    public void onBackButton(View view)
    {
        super.onBackPressed();



    }
}
