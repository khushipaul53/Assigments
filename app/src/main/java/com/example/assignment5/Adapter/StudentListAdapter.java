package com.example.assignment5.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment5.Util.ClickListener;
import com.example.assignment5.Model.StudentList;
import com.example.assignment5.R;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {


    private ArrayList<StudentList> studentList;
    private ClickListener mListener;

    public StudentListAdapter(ArrayList<StudentList> studentList,ClickListener listener) {
        this.studentList = studentList;
        mListener = listener;
    }



    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);

        StudentViewHolder holder = new StudentViewHolder(view,mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder studentViewHolder, int i) {

        studentViewHolder.tvName.setText(studentList.get(i).getName());
        studentViewHolder.tvStandard.setText(studentList.get(i).getmStandard());
        studentViewHolder.tvRoll.setText(studentList.get(i).getRollno());


        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(studentViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    //Inner class that handles the View of the recycler view.
    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvStandard, tvRoll;

        //Constructor
        public StudentViewHolder(@NonNull View itemView, ClickListener listener) {

            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_studentList);
            tvStandard = itemView.findViewById(R.id.tv_class_studentList);
            tvRoll = itemView.findViewById(R.id.tv_rollNo_studentList);

        }

    }

}


