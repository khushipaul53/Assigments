package com.example.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.Adapters.StudentListAdapter;
import com.example.Util.HelperClass;
import com.example.assignment4.HomeActivity;
import com.example.assignment4.R;
import com.example.assignment4.StudentList;
import com.example.assignment4.ViewActivity;

import java.util.ArrayList;
import java.util.Comparator;

import static android.app.Activity.RESULT_OK;


public class HomeFragment<firstFragInterface> extends Fragment implements StudentListAdapter.MyClickListener {
    ViewPager mViewPager;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int Position;
    private ClickListener mClickListener;
    private String action;
    private String mname;
    private String mrollno;
    private EditText etName, etRollno, etClss;
    private String mClss;
    private HelperClass helper;
    private Button btnSubmit;
    private StudentListAdapter studentListAdapter;
    private StudentListAdapter.MyViewHolder myViewHolder;
    private ArrayList<StudentList> List = new ArrayList<>();
    private Button btnAdd;
    public final static String NAME="NAME";
    public final static String ROLLNO="ROLLNO";
    public final static String CLASS="CLASS";
    public final static String EDIT="EDIT";



    public HomeFragment() {

        // Required empty public constructor

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        recyclerView=view.findViewById(R.id.id_recylcer_studentlist);
//        recyclerView=new RecyclerView(R.id.);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        studentListAdapter=new StudentListAdapter(List);

        studentListAdapter = new StudentListAdapter(List);

        studentListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(studentListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentListAdapter);


        init(view);
        return view;


    }

    public void getBtnAction(String action){
        this.action=action;
    }



    public void init(View view) {
        btnAdd = view.findViewById(R.id.btn_add_studentList);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mClickListener.myClick(EDIT,null);

            }
        });

    }

    public void getData(Bundle bundle) {
        mname = bundle.getString(NAME);
        mrollno = bundle.getString(ROLLNO);
        mClss = bundle.getString(CLASS);
        List.add(new StudentList(mname, mrollno, mClss));
        studentListAdapter.notifyDataSetChanged();

    }

    public void onItemClick(final int position, View v) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.Alert_options))
                .setPositiveButton(getString(R.string.Alert_delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        studentListAdapter.deleteItem(position);
                    }
                })
                .setNegativeButton(getString(R.string.ALert_update), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Position=position;
//                        action="edit";
//                        StudentDetailActivity sd=new StudentDetailActivity(action);
                        StudentList s=List.get(position);
                        mname =s.getName();
                        mrollno=s.getRollNo();
                        mClss=s.getmClass();
                        Bundle bundle=new Bundle();
                        List.remove(Position);
                        studentListAdapter.notifyItemRemoved(Position);
                        bundle.putString(NAME,mname);
                        bundle.putString(ROLLNO,mrollno);
                        bundle.putString(CLASS,mClss);
                        List.add(Position,new StudentList(mname, mrollno, mClss));
                        studentListAdapter.notifyDataSetChanged();

                      getBtnAction(EDIT);
                      mClickListener.myClick(EDIT,bundle);

                    }
                })
                .setNeutralButton(getString(R.string.Alert_view), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mname = List.get(Position).getName();
                        mrollno = List.get(Position).getRollNo();
                        mClss = List.get(Position).getmClass();
                        Bundle bundle=new Bundle();
                        bundle.putString(NAME,mname);
                        bundle.putString(ROLLNO,mrollno);
                        bundle.putString(CLASS,mClss);
                      //  bundle.putString("ACTION","VIEW");

                         Intent intent=new Intent(getActivity(), ViewActivity.class);
//                        StudentDetailActivity sd=new StudentDetailActivity(action);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
                .show();
    }




    public void setInstanceForInterface(ClickListener firstFragInterface) {
        this.mClickListener = firstFragInterface;
    }

    public static HomeFragment newInstance(Bundle mBundle) {
        HomeFragment homeFragment = new HomeFragment();

        if (mBundle != null) {
            homeFragment.setArguments(mBundle);
        }
        return homeFragment;
    }


    public interface ClickListener {
        void myClick(String edit,Bundle bundle);
    }

}


