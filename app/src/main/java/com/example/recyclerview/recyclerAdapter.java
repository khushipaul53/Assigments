package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {


    private ArrayList<recycler> mDataset;


    public recyclerAdapter(ArrayList<recycler> myDataset) {
        mDataset = myDataset;
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // each data item is just a string in this case
        public TextView name;
        public TextView age;
        public TextView phone;
        ImageView image;
        ImageView delete;
        public TextView likes;
        public Button btn_like;
        RatingBar rating_bar;


        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_name);
            age = v.findViewById(R.id.tv_age);
            phone = v.findViewById(R.id.tv_Phone);
            image = (ImageView) v.findViewById(R.id.photo);
            delete = v.findViewById(R.id.image_cross);
            likes = v.findViewById(R.id.tv_likes);
            btn_like = v.findViewById(R.id.btn_likes);
           rating_bar=v.findViewById(R.id.ratingbar);


            rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    if(fromUser)
                    {
                        recycler re=mDataset.get(getAdapterPosition());
                        re.setRating(rating);

                    }
                }
            });


            btn_like.setOnClickListener(this);
            delete.setOnClickListener(this);

        }


        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_likes: {
                    recycler temp = mDataset.get(getAdapterPosition());
                    temp.count++;
                    notifyItemChanged(getAdapterPosition());
                    break;

                }
                case R.id.image_cross:
                {
                    mDataset.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),mDataset.size());
                    break;
                }

            }
        }
    }




    // Provide a suitable constructor (depends on the kind of dataset)



    // Create new views (invoked by the layout manager)
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater   = LayoutInflater.from(parent.getContext());
        View view =(RelativeLayout)inflater.inflate(R.layout.list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        recycler cdata=mDataset.get(position);

        holder.name.setText(cdata.getmName());
        holder.age.setText(cdata.getmAge());
        holder.phone.setText(cdata.getmPhone());
        holder.likes.setText(""+cdata.count);
        Picasso.get().load("https://cdn.pixabay.com/photo/2018/02/09/21/46/rose-3142529__340.jpg").into(holder.image);
        holder.rating_bar.setRating(cdata.mrating);








    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}