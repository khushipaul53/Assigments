package com.example.assignment3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentListActivity extends AppCompatActivity implements StudentListAdapter.MyClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    int Position;
    String action;
    StudentListAdapter studentAdapter;
    String mname;
    String mrollno;
    EditText etName,etRollno,etClss;
    String mClss;
    HelperClass helper;
    Button btnSubmit;
    RecyclerView.LayoutManager newLayoutManager;
   GridLayoutManager gridLayoutManager;
    StudentListAdapter.MyViewHolder myViewHolder;
    ArrayList<StudentList>List = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        etName = findViewById(R.id.et_name_studentDetails);
        etRollno = findViewById(R.id.et_rollno_studentDetails);
        etClss = findViewById(R.id.et_class_studentDetails);



        recyclerView = (RecyclerView) findViewById(R.id.id_recylcer_studentlist);
         btnSubmit = (Button)findViewById(R.id.btn_submit_studentDetails);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
        List.add(new StudentList("Khushi","59","7"));
        List.add(new StudentList("Div","80","7"));

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
      gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);

        recyclerView.setLayoutManager(layoutManager);

        studentAdapter = new StudentListAdapter(List);

        recyclerView.setAdapter(studentAdapter);
    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        studentAdapter.setOnItemClickListener(this);
        helper=new HelperClass();

    }


// to show the functionality of alertdialog

    public void onItemClick(final int position, View v) {
        new AlertDialog.Builder(this)

              .setTitle(getString(R.string.Alert_options))
                .setPositiveButton(getString(R.string.Alert_delete), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       studentAdapter.deleteItem(position);


                    }
               })
                .setNegativeButton(getString(R.string.ALert_update), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Position=position;
//                        action="edit";
//                        StudentDetailActivity sd=new StudentDetailActivity(action);

//


                        StudentList s=List.get(position);
                        mname =s.getName();
                        mrollno  =s.getRollNo();
                        mClss=s.getmClass();
                        Bundle bundle=new Bundle();
                        bundle.putString("NAME",mname);
                        bundle.putString("ROLLNO",mrollno);
                        bundle.putString("CLASS",mClss);
                        bundle.putString("ACTION","EDIT");
                      //  Log.i("Bhim","This: "+name);
                        Intent intent=new Intent(StudentListActivity.this,StudentDetailActivity.class);
                             intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK,intent);
                        startActivityForResult(intent,1);




                    }
                })
                .setNeutralButton(getString(R.string.Alert_view), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {





                        mname = List.get(Position).getName();
                        mrollno = List.get(Position).getRollNo();
                        mClss = List.get(Position).getmClass();
                        Bundle bundle=new Bundle();
                        bundle.putString("NAME",mname);
                        bundle.putString("ROLLNO",mrollno);
                        bundle.putString("CLASS",mClss);
                        bundle.putString("ACTION","VIEW");
                        Intent intent=new Intent(StudentListActivity.this,StudentDetailActivity.class);

                        setResult(Activity.RESULT_OK,intent);
//                        action="view";
//                        StudentDetailActivity sd=new StudentDetailActivity(action);
                                 intent.putExtras(bundle);

                                  startActivity(intent);





                   }
              })
                .show();
    }


    //for data exchange from detailsactivity to list activty
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//

        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {


                List.remove(Position);
                studentAdapter.notifyItemRemoved(Position);
                mname = data.getStringExtra("NAME");
                mrollno = data.getStringExtra("ROLLNO");
                mClss = data.getStringExtra("CLASS");
                List.add(Position,new StudentList(mname,mrollno,mClss));
                studentAdapter.notifyItemRemoved(Position);

            }
        }

        if(requestCode==2) {
               if (resultCode == RESULT_OK) {
                   mname = data.getStringExtra("NAME");
                   mrollno = data.getStringExtra("ROLLNO");
                   mClss = data.getStringExtra("CLASS");
                   List.add(new StudentList(mname, mrollno, mClss));
                  studentAdapter.notifyDataSetChanged();

               }
           }






    }


    void openStudentDetail(View view) {
        Intent intent = new Intent(StudentListActivity.this, StudentDetailActivity.class);
        startActivityForResult(intent, 2);

    }

    //menu option bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the actionbar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_listorgrid:
                newLayoutManager=recyclerView.getLayoutManager();
               if(newLayoutManager instanceof GridLayoutManager) {
                   recyclerView.setLayoutManager(layoutManager);


                }
               else if(newLayoutManager instanceof LinearLayoutManager) {
                   recyclerView.setLayoutManager(gridLayoutManager);

               }
                studentAdapter.notifyDataSetChanged();
                return true;


            case R.id.id_btn_sortName_studentList:
              // Collections.sort(arrayList,helper.compareName());
                    sortList();
                studentAdapter.notifyDataSetChanged();
//                studentAdapter = new StudentListAdapter(arrayList);
//                recyclerView.setAdapter(studentAdapter);
                return true;


            case R.id.id_btn_sortId_studentList:
                helper.compareId(List);
                studentAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void sortList() {
        Collections.sort(List, new CustomComparator());
//        for(int i=0;i<arrayList.size();i++)
//            log(""+mStudentsList.get(i).getName());
//        mRecyclerAdapter.notifyDataSetChanged();
    }

    public class CustomComparator implements Comparator<StudentList>{
        @Override
        public int compare(StudentList stu1, StudentList stu2) {
//            if(mIsSortByName)
                return stu1.getName().compareTo(stu2.getName());
//            return stu1.getRollNo().compareTo(stu2.getRollNum());
        }
    }


}