package com.example.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.Adapters.StudentListAdapter;
import com.example.Util.HelperClass;
import com.example.assignment4.HomeActivity;
import com.example.assignment4.R;


public class AddStudentFragment extends Fragment {

    private MyClickListener mClickListener;
    private Button btnSubmit;
    private Bundle bundle;
    private String action;
    private Context mcontext;
    private EditText etName, etRollno, etClss;
    private String mname, mrollno, mClss;
    private HelperClass helper = new HelperClass();
    public final static String NAME="NAME";
    public final static String ROLLNO="ROLLNO";
    public final static String CLASS="CLASS";
    ;

    public AddStudentFragment() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        etName=view.findViewById(R.id.et_name_studentDetails);
        etRollno=view.findViewById(R.id.et_rollno_studentDetails);
        etClss=view.findViewById(R.id.et_class_studentDetails);
        init(view);
        if (bundle != null) {
            mname = bundle.getString(NAME);
            mrollno = bundle.getString(ROLLNO);
            mClss = bundle.getString(CLASS);
            etName.setText(mname);
            etRollno.setText(mrollno);
            etClss.setText(mClss);


        }

        return view;

    }
    public void getBtnAction(String action)
    {
        this.action=action;
    }

    public void init( final View view){
        btnSubmit=view.findViewById(R.id.btn_submit_studentDetails);
        bundle = new Bundle();




        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            etName=view.findViewById(R.id.et_name_studentDetails);
            etRollno=view.findViewById(R.id.et_rollno_studentDetails);
            etClss=view.findViewById(R.id.et_class_studentDetails);
                mcontext=getActivity().getApplicationContext();


                mname = etName.getText().toString();
                mrollno = etRollno.getText().toString();
                mClss =etClss.getText().toString();

                if(helper.Validations(mcontext,mname,mrollno,mClss)) {

                    bundle.putString(NAME,mname);
                    bundle.putString(ROLLNO,mrollno);
                    bundle.putString(CLASS, mClss);
                    mClickListener.MyClick(bundle);

                }




            }



        });

    }


    public static AddStudentFragment newInstance(Bundle mBundle) {
        AddStudentFragment addStudentFragment = new AddStudentFragment();

        if (mBundle != null) {
            addStudentFragment.setArguments(mBundle);
        }
        return addStudentFragment;
    }

    public void setInstanceForInterface(MyClickListener clickListener) {
        this.mClickListener=clickListener;
    }
    public interface MyClickListener{
        void MyClick(Bundle bundle);
    }
}