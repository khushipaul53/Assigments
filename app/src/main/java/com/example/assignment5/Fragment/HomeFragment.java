package com.example.assignment5.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.assignment5.Activity.ViewActivity;
import com.example.assignment5.Adapter.StudentListAdapter;
import com.example.assignment5.Comunicator.Comunicate;
import com.example.assignment5.Database.DatabaseClass;
import com.example.assignment5.Dialog.GenerateDialog;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.R;
import com.example.assignment5.Util.ClickListener;
import com.example.assignment5.Util.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements ClickListener {


    private ArrayList<StudentList> mStudentList = new ArrayList<StudentList>();
    private String[] mDialogItems;
    private RecyclerView rvStudentList;
    private StudentListAdapter adapter;
    private int positionStudent;
    private DatabaseClass studentHelperDatabase;
    private Button addButton;
    private Context mContext;
    private Comunicate mCommunicator;
    private ClickListener mListener;
    private GenerateDialog generateDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        studentHelperDatabase = new DatabaseClass(getContext());
        studentHelperDatabase.getWritableDatabase();
        init(view);
        //Get the student list from database.
            mStudentList.addAll(studentHelperDatabase.refreshStudentListfromDb());

        if(mStudentList.size()>1) {
            adapter.notifyDataSetChanged();
        }
        setHasOptionsMenu(true);
        return view;
    }

    private void init(View view) {


        generateDialog = new GenerateDialog(mContext);
        mListener = this;
        adapter = new StudentListAdapter(mStudentList,mListener);
        mDialogItems = getResources().getStringArray(R.array.Dialog_Operations);
        rvStudentList = view.findViewById(R.id.id_recylcer_studentlist);
        rvStudentList.setHasFixedSize(true);
        rvStudentList.setLayoutManager(new LinearLayoutManager(mContext));

        //puts divider in view.
        rvStudentList.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        rvStudentList.setAdapter(adapter);
        addButton = view.findViewById(R.id.btn_add_studentList);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constants.CODE_TO_ADD_STUDENT,Constants.ADD_IT);
                bundle.putParcelableArrayList(Constants.STUDENT_LIST_FROM_MAIN,mStudentList);

                mCommunicator.Update(bundle);
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        try {
            mCommunicator=(Comunicate)mContext;
        }catch (ClassCastException e) {
            throw new ClassCastException("error");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCommunicator = null;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void addOrUpdateStudentInList(Bundle bundle) {

        //Add new student.
        if(Objects.equals(bundle.getString(Constants.CODE_TO_ADD_STUDENT), Constants.ADD_IT)) {
            StudentList student =new StudentList(bundle.getString(Constants.NAME),bundle.getString(Constants.ROLL_NO),bundle.getString(Constants.STANDARD));
            mStudentList.add(student);
            adapter.notifyDataSetChanged();

            //Update old student.
        }else if(Objects.equals(bundle.getString(Constants.CODE_TO_ADD_STUDENT), Constants.UPDATE_IT)){
            StudentList student=mStudentList.get(positionStudent);
            student.setRollno(bundle.getString(Constants.ROLL_NO));
            student.setName(bundle.getString(Constants.NAME));
            student.setStandard(bundle.getString(Constants.STANDARD));
            adapter.notifyDataSetChanged();
        }

    }



    private void editStudent(StudentList whichStudent){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CODE_TO_ADD_STUDENT,Constants.UPDATE_IT);
        bundle.putParcelable(Constants.THISSTUDENT,whichStudent);
        mCommunicator.Update(bundle);
    }


    private void viewDetails(StudentList student){

        Intent forView = new Intent(mContext, ViewActivity.class);

        forView.putExtra(Constants.CODE_TO_ADD_STUDENT,Constants.VIEW);
        forView.putExtra(Constants.THISSTUDENT,student);
        mContext.startActivity(forView);
    }


    private void studentDelete(final int position) {
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(mContext);
        deleteDialog.setTitle(R.string.options);
       // deleteDialog.setMessage(R.string.want_to_delete_or_not);
        deleteDialog.setPositiveButton(R.string.Delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                generateDialog.generateDialogOnTouch(mStudentList.get(position),Constants.DELETE_IT);
                mStudentList.remove(position);
                adapter.notifyDataSetChanged();

            }
        });
        deleteDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        deleteDialog.show();
    }



    public void onItemClick(int position) {

        //Set the position based on the Viewholder selected by the listener.
        positionStudent = position;

        final String[] items = {Constants.VIEWMESSAGE, Constants.EDITMESSAGE, Constants.DELETEMESSAGE};
        final int viewStudent = 0, editStudent = 1, deleteStudent = 2;

        //Alert Dialog that has context of this activity.
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.title);
        final StudentList whichStudent = mStudentList.get(position);


        //Sets the items of the Dialog.
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                switch (which) {
                    case viewStudent:
                        viewDetails(whichStudent);
                        break;

                    case editStudent:
                        editStudent(whichStudent);

                        break;
                    //Delete the Student.
                    case deleteStudent:
                        studentDelete(positionStudent);
                        break;
                }

            }
        });
        AlertDialog mAlert = builder.create();
        mAlert.show();
    }
}
