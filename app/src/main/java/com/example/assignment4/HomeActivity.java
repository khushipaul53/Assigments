   package com.example.assignment4;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Adapters.PagerViewAdapter;
import com.example.Adapters.StudentListAdapter;
import com.example.Fragments.AddStudentFragment;
import com.example.Fragments.HomeFragment;
import com.example.Util.HelperClass;

import java.util.ArrayList;
import java.util.Comparator;

   public class HomeActivity extends AppCompatActivity implements HomeFragment.ClickListener, AddStudentFragment.MyClickListener     {

       private RecyclerView recyclerView;
       private RecyclerView.LayoutManager layoutManager;
       private HomeFragment.ClickListener mClickListener;
       int Position;
            PagerViewAdapter PagerViewAdapter;
       ArrayList<Fragment>mFragmentsList=new ArrayList<>();
       ViewPager mViewPager;
       private Button btnSubmit;
       private Bundle bundle;
       private Context mcontext;
       private EditText etName, etRollno, etClss;
       private String mname, mrollno, mClss;
       private HelperClass helper=new HelperClass();
       private HomeFragment homeFragment=new HomeFragment();
       private RecyclerView.Adapter studentAdapter;
       private String action;
       private StudentListAdapter studentListAdapter;
       private StudentListAdapter.MyViewHolder myViewHolder;
       private ArrayList<StudentList> List = new ArrayList<>();
       private Button btnAdd;
       public final static String NAME="NAME";
       public final static String ROLLNO="ROLLNO";
       public final static String CLASS="CLASS";
       public final static String EDIT="EDIT";

       ArrayList<String> mFragmentsNameList=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = findViewById(R.id.id_viewPager);
        TabLayout tabLayout=findViewById(R.id.id_tabLayout);

        addPagesTofragmentList();
        getItems();

        PagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager(),mFragmentsList,mFragmentsNameList);
        mViewPager.setAdapter(PagerViewAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        tabLayout.setupWithViewPager(mViewPager);
        studentAdapter=new StudentListAdapter(List);


    }


       public void MyClick(Bundle bundle)
       {
           etName=findViewById(R.id.et_name_studentDetails);
           etRollno=findViewById(R.id.et_rollno_studentDetails);
           etClss=findViewById(R.id.et_class_studentDetails);



           mname = etName.getText().toString();
           mrollno = etRollno.getText().toString();
           mClss =etClss.getText().toString();
           bundle.putString(NAME,mname);
           bundle.putString(ROLLNO,mrollno);
           bundle.putString(CLASS, mClss);

           HomeFragment homeFragment = (HomeFragment) mFragmentsList.get(0);

           homeFragment.getData(bundle);

        mViewPager.setCurrentItem(0);

       }



       private void addPagesTofragmentList() {
            // add example frgament 1
            Fragment fragment = new HomeFragment();
            ((HomeFragment)fragment).setInstanceForInterface(this);
            mFragmentsList.add(fragment);
            // add example fragment 2
            Fragment fragment1 = new AddStudentFragment();
            ((AddStudentFragment)fragment1).setInstanceForInterface(this);
            mFragmentsList.add(fragment1);
        }



       private void getItems()
        {
            mFragmentsNameList.add(getString(R.string.viewpager1));
            mFragmentsNameList.add(getString(R.string.viewPager2));

        }

       @Override
       public void myClick(String edit, Bundle bundle) {

       }


//


   }



