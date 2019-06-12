package com.clicklabs.apicalls.activity.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.activity.ShowPostsActivity;
import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.util.Constants;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment {

    private TextView tvName, tvId, tvUserName, tvEmail;
    private Button btnShowPosts,btnMoreDetails;
    private Context mContext;
    private String userId;
    private TextView tvNoUserSelected;
    private LinearLayout llTextFields;
    public int adapterPosition;
    private ArrayList mUserList;


    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        init(view);


        if(userId==null){
            noUserSelected();
        }

        return view;
    }

    /**
     * Initialized the components
     * @param view View type inflated for the corresponding XML.
     */
    private void init(View view) {
        tvName = view.findViewById(R.id.tvName);
        tvId = view.findViewById(R.id.tvUserId);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvEmail = view.findViewById(R.id.tvUserEmail);
        tvNoUserSelected = view.findViewById(R.id.tv_no_user_selected);
        llTextFields = view.findViewById(R.id.ll_text_fields);
        btnShowPosts = view.findViewById(R.id.btnShowUserPosts);
        btnMoreDetails=view.findViewById(R.id.btnMoreDetails);
        btnShowPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowPostsActivity.class);
                if(userId!=null){

                    intent.putExtra(Constants.Keys.USERID,userId);
                }
                startActivity(intent);
            }
        });


        btnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Dialog dialog = new Dialog(this);

                dialog.setContentView(R.layout.dialog_box);
                TextView tvId = (TextView) dialog.findViewById(R.id.dialogue_tv_id);
                tvId.setText("ID"+mUserList.get(adapterPosition).getId());
                TextView tvName = (TextView) dialog.findViewById(R.id.dialogue_tv_name);
                tvName.setText("Name"+mUserList.get(adapterPosition).getName());
                TextView tvUserName = (TextView) dialog.findViewById(R.id.dialogue_tv_username);
                tvUserName.setText("UserName"+mUserList.get(adapterPosition).getUsername());
                TextView tvEmail = (TextView) dialog.findViewById(R.id.dialogue_tv_email);
                tvEmail.setText("Email"+mUserList.get(adapterPosition).getEmail());
                TextView tvPhone = (TextView) dialog.findViewById(R.id.dialogue_tv_phone);
                tvPhone.setText("Phone"+mUserList.get(adapterPosition).getPhone());
                TextView tvWebsite = (TextView) dialog.findViewById(R.id.dialogue_tv_website);
                tvWebsite.setText("Website"+mUserList.get(adapterPosition).getWebsite());
                dialog.show();
                }

        });
    }

    /**
     * Checks if any user is not selected. Shows a message to select the user from the Fragment.
     */
    private void noUserSelected(){

        llTextFields.setVisibility(View.GONE);
        tvNoUserSelected.setVisibility(View.VISIBLE);
        tvNoUserSelected.setText(Constants.TextStrings.NOUSERSELECTED);
        btnShowPosts.setVisibility(View.GONE);

    }

    /**
     * Used by the Fragment Communicator interface to show the details selected the first fragment.
     * @param mUser user selected on First Fragment's on touch listener.
     */
    @SuppressLint("SetTextI18n")
    public void showDetails(final User mUser){

        llTextFields.setVisibility(View.VISIBLE);
        tvNoUserSelected.setVisibility(View.GONE);
        tvName.setText(mUser.getName());
        tvId.setText(mUser.getId().toString());
        userId = mUser.getId().toString();
        tvUserName.setText(mUser.getUsername());
        tvEmail.setText(mUser.getEmail());
        btnShowPosts.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }



}
