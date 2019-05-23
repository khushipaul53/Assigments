package com.example.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment4.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {
    private EditText etName, etRollno, etClss;
    private String mname, mrollno, mClss;
    private Button btnSubmit;
    public final static String NAME="NAME";
    public final static String ROLLNO="ROLLNO";
    public final static String CLASS="CLASS";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }


    public static ViewFragment newInstance(Bundle bundle) {

            ViewFragment fragment=new ViewFragment();
            if(bundle!=null)
            {
                fragment.setArguments(bundle);
            }
            return fragment;
        }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        etName = view.findViewById(R.id.et_name_studentDetails);
        etRollno = view.findViewById(R.id.et_rollno_studentDetails);
        etClss = view.findViewById(R.id.et_class_studentDetails);
        btnSubmit=view.findViewById(R.id.btn_submit_studentDetails);

        Bundle bundle =getArguments();
        if (bundle != null) {
//            etName.setText("  ");
//            etRollno.setText("  ");
//            etClss.setText("  ");
            etName.setText(bundle.getString(NAME));
            etRollno.setText(bundle.getString(ROLLNO));
            etClss.setText(bundle.getString(CLASS));
            etName.setEnabled(false);
            etRollno.setEnabled(false);
            etClss.setEnabled(false);
           // btnSubmit.setVisibility(View.INVISIBLE);

        }
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
