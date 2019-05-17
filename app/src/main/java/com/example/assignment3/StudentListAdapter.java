package com.example.assignment3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder> {


    ArrayList<StudentList>list;
    private static StudentListAdapter.MyClickListener sClickListener;



    public StudentListAdapter(ArrayList<StudentList>list) {
        this.list=list;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

   public TextView name,rollno,mClass;
   public Button btnAdd;


        public MyViewHolder(LinearLayout ll)


        {
            super(ll);
           name=(TextView) ll.findViewById(R.id.tv_name_studentList);
             rollno=(TextView)ll.findViewById(R.id.tv_rollNo_studentList);
             mClass=(TextView)ll.findViewById(R.id.tv_class_studentList);
            btnAdd=ll.findViewById(R.id.btn_add_studentList);
            ll.setOnClickListener(this);

        }
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

     void setOnItemClickListener(StudentListAdapter.MyClickListener myClickListener) {
        this.sClickListener = myClickListener;
    }
    @Override
    public StudentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        LinearLayout linearLayout=(LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item,parent,false);

        MyViewHolder holder=new MyViewHolder(linearLayout);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        StudentList Slist=list.get(position);
        holder.name.setText((Slist.getName()));
        holder.rollno.setText((Slist.getRollNo()));
        holder.mClass.setText((Slist.getmClass()));
        //   notifyDataSetChanged();
    }
    void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    interface MyClickListener{
       void onItemClick(int position, View v);
    }

}


