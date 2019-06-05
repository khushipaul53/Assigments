package com.example.assignment5.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment5.BroadcasrtRecievers.BroadcastRecivers;
import com.example.assignment5.Comunicator.Comunicate;
import com.example.assignment5.Dialog.GenerateDialog;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.R;
import com.example.assignment5.Service.Async;
import com.example.assignment5.Service.IntentServices;
import com.example.assignment5.Service.Services;
import com.example.assignment5.Util.Constants;
import com.example.assignment5.Util.HelperClass;

import java.util.ArrayList;
import java.util.Objects;

public class StudentAddFragment extends Fragment {
    private String oldIdOfStudent;
    private BroadcastRecivers studentBroadcastReceiver;
    private Button mSaveStudentButton;
    private EditText etStudentName, etStudentRoll, etStudentStandard;
    private Context mContext;
    private Comunicate mCommunicator;
    private HelperClass Validate;
    private ArrayList<StudentList> mStudentList = new ArrayList<StudentList>();
    private GenerateDialog generateDialog;


    @Override
    public void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(Constants.FILTER_ACTION_KEY);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(studentBroadcastReceiver,intentFilter);

    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(studentBroadcastReceiver);

    }


    public StudentAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_add_student,container,false);
        init(view);
        return view;
    }

    public void init(View view) {
        generateDialog = new GenerateDialog(mContext);
        studentBroadcastReceiver = new BroadcastRecivers(mContext);
        etStudentName = view.findViewById(R.id.et_name_studentDetails);
        etStudentRoll = view.findViewById(R.id.et_rollno_studentDetails);
        etStudentStandard = view.findViewById(R.id.et_class_studentDetails);

        mSaveStudentButton = view.findViewById(R.id.btn_submit_studentDetails);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        try {
            mCommunicator=(Comunicate) mContext;
        }catch (ClassCastException e) {
            throw new ClassCastException(getString(R.string.exception));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCommunicator = null;
    }

    public void addOrUpdateStudentInCreateStudentFragment(Bundle bundle){

        if(Objects.equals(bundle.getString(Constants.CODE_TO_ADD_STUDENT), Constants.UPDATE_IT)) {

            StudentList studentTemplate = bundle.getParcelable(Constants.THISSTUDENT);
            assert studentTemplate != null;

            //Set the Edittext fields as per the student that needs to be updated.
            etStudentName.setText(studentTemplate.getName());
            etStudentRoll.setText(studentTemplate.getRollno());
            etStudentStandard.setText(studentTemplate.getmStandard());


            mStudentList=bundle.getParcelableArrayList(Constants.STUDENT_LIST_FROM_MAIN);

            oldIdOfStudent = studentTemplate.getRollno();

            editMode();

        }else if(bundle.getString(Constants.CODE_TO_ADD_STUDENT).equals(Constants.ADD_IT)){

            mStudentList=bundle.getParcelableArrayList(Constants.STUDENT_LIST_FROM_MAIN);

            onClickButton();
        }
    }

    public void viewMode(StudentList student){

        etStudentName.setText(student.getName());
        etStudentRoll.setText(student.getRollno());
        etStudentStandard.setText(student.getmStandard());

        mSaveStudentButton.setVisibility(View.GONE);
        etStudentName.setEnabled(false);
        etStudentRoll.setEnabled(false);
        etStudentStandard.setEnabled(false);

        etStudentName.setFocusable(false);
        etStudentRoll.setFocusable(false);
        etStudentStandard.setFocusable(false);

    }

    public void editMode() {
        etStudentRoll.setEnabled(false);
        mSaveStudentButton.setText(Constants.UPDATE_STUDENT_DETAILS);
        mSaveStudentButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //to check if the the entered name is in valid format
                if (!Validate.isValidName(etStudentName.getText().toString().trim())) {
                    etStudentName.requestFocus();
                    etStudentName.setError(getString(R.string.validName));
                }
                //to check if the the entered roll number is in valid format
                else if (!Validate.isValidRollNo(etStudentRoll.getText().toString().trim())) {
                    etStudentRoll.requestFocus();
                    etStudentRoll.setError(getString(R.string.validRollno));
                }


                else{
                    String name = etStudentName.getText().toString().trim();
                    String roll = etStudentRoll.getText().toString().trim();
                    String standard = etStudentStandard.getText().toString().trim();

                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.CODE_TO_ADD_STUDENT, Constants.UPDATE_IT);
                    bundle.putString(Constants.NAME, name);
                    bundle.putString(Constants.ROLL_NO, roll);
                    bundle.putString(Constants.STANDARD, standard);

                    generateDialog(bundle,Constants.UPDATE_IT, oldIdOfStudent);
                    clearDetails();
                }
            }
        });
    }

    private void generateDialog(final Bundle sendBundle,final String operationOnStudent, final String oldIdOfStudent) {

        final StudentList studentToHandle = new StudentList();

        studentToHandle.setName(sendBundle.getString(Constants.NAME));
        studentToHandle.setStandard(sendBundle.getString(Constants.STANDARD));
        studentToHandle.setRollno(sendBundle.getString(Constants.ROLL_NO));


        final String[] items = {Constants.SERVICE,
                Constants.INTENTSERVICE,
                Constants.ASYNC};
        final int useService = 0, useIntentService = 1, useAsyncTasks = 2;

        //Alert Dialog that has context of this activity.
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("options");
        //Sets the items of the Dialog.
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                switch (which) {
                    case useService:
                        Intent forService = new Intent(mContext,Services.class);
                        forService.putExtra(Constants.STUDENT_FOR_DB, studentToHandle);
                        forService.putExtra(Constants.OPERATION,operationOnStudent);
                        forService.putExtra(Constants.OLD_ID_OF_STUDENT,oldIdOfStudent);
                        mContext.startService(forService);

                        break;

                    case useIntentService:
                        Intent forIntentService = new Intent(mContext,
                                IntentServices.class);
                        forIntentService.putExtra(Constants.STUDENT_FOR_DB, studentToHandle);
                        forIntentService.putExtra(Constants.OPERATION,operationOnStudent);
                        forIntentService.putExtra(Constants.OLD_ID_OF_STUDENT,oldIdOfStudent);
                        mContext.startService(forIntentService);

                        break;

                    case useAsyncTasks:
                        Async backgroundAsyncTasks = new Async(mContext);
                        backgroundAsyncTasks.execute(studentToHandle,operationOnStudent,oldIdOfStudent);

                        break;
                }
                mCommunicator.Add(sendBundle);

            }
        });

        AlertDialog mAlert = builder.create();
        mAlert.show();

    }


    public void onClickButton() {

        Objects.requireNonNull(getActivity()).setTitle(getString(R.string.addStudent));
        mSaveStudentButton.findViewById(R.id.btn_submit_studentDetails);
        etStudentRoll.setEnabled(true);
        mSaveStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Validate.isValidName(etStudentName.getText().toString().trim())) {
                    etStudentName.requestFocus();
                    Toast.makeText(mContext,getString(R.string.error),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Validate.isValidRollNo(etStudentRoll.getText().toString().trim())) {
                    etStudentRoll.requestFocus();
                    Toast.makeText(mContext, Constants.INVALIDROLL,
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Validate.isUniqueRollNo(etStudentRoll.getText().toString().trim(),mStudentList)) {
                    etStudentRoll.requestFocus();
                    Toast.makeText(getContext(), Constants.ROLLNOTUNIQUE,
                            Toast.LENGTH_LONG).show();
                    return;
                }


                String name = etStudentName.getText().toString().trim();
                String roll = etStudentRoll.getText().toString().trim();
                String standard = etStudentStandard.getText().toString().trim();


                Bundle bundle= new Bundle();
                bundle.putString(Constants.CODE_TO_ADD_STUDENT,Constants.ADD_IT);
                bundle.putString(Constants.NAME,name);
                bundle.putString(Constants.ROLL_NO,roll);
                bundle.putString(Constants.STANDARD,standard);


                etStudentName.getText().clear();
                etStudentRoll.getText().clear();
                etStudentStandard.getText().clear();


                generateDialog(bundle,Constants.ADD_IT,roll);
            }
        });
    }

    public void clearDetails() {
        etStudentName.getText().clear();
        etStudentRoll.getText().clear();
        etStudentStandard.getText().clear();

    }

}



