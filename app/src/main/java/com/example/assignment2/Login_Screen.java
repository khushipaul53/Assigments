package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

@SuppressWarnings("unused")
public class Login_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);





        final View parentLayout = findViewById(android.R.id.content);
        TextView Register=findViewById(R.id.tv_onclick_register);
        Button login=findViewById(R.id.btn_login);




//on clicking the Register  button will perform the function
//        intent change, from login screen to registeration screen,

        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){

                Intent registerIntent=new Intent(Login_Screen.this,Registeration_screen.class);
                startActivityForResult(registerIntent,0);
            }
        });

        final EditText email=findViewById(R.id.ed_login_email);
        final EditText password=findViewById(R.id.ed_login_password);


        //  on clicking on login button all validation will be check like email validation and password validation if wrong input is typed
        //then it will show message on snack bar .
        login.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                String email_login=email.getText().toString();

                String Expn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                String password_login=password.getText().toString();

                if (email_login.matches(Expn) && password_login.length()!=0)

                {
                           // to show message on bottom of the screen
                    Snackbar snackbar = Snackbar.make(parentLayout, "Logged In Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();


                }
                    else if(email_login.matches(Expn) && password_login.length()==0)
                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Please fill Password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else

                {
                    Snackbar snackbar = Snackbar.make(parentLayout, "Invalid Email", Snackbar.LENGTH_LONG);
                    snackbar.show();


                }

                try {
                    // this is used so that after the edit text are over on entering the keyboard gets hidden

                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                } catch (Exception e) {

                }


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });

     //this function is used to toggle the password

        ImageView image = (ImageView) findViewById(R.id.image_login_show_hide);
        image.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });





    }


}





