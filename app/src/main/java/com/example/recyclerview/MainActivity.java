package com.example.recyclerview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   private int count;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<recycler>myDataset=new ArrayList();

        myDataset.add(new recycler("Khushi","22","987432467"));
        myDataset.add(new recycler("Divpreet","21","987432456"));
        myDataset.add(new recycler("Sidharth","21","987432467"));
        myDataset.add(new recycler("Kartik Attri","22","987432467"));
        myDataset.add(new recycler("Kriti","21","987432467"));
        myDataset.add(new recycler("KartikGrover","21","987432467"));
        myDataset.add(new recycler("Komal","21","987432467"));
        myDataset.add(new recycler("Khushi","22","987432467"));
        myDataset.add(new recycler("Divpreet","21","987432456"));
        myDataset.add(new recycler("Sidharth","21","987432467"));
        myDataset.add(new recycler("Kartik Attri","22","987432467"));
        myDataset.add(new recycler("Kriti","21","987432467"));
        myDataset.add(new recycler("KartikGrover","21","987432467"));
        myDataset.add(new recycler("Komal","21","987432467"));


        // specify an adapter (see also next example)
        mAdapter = new recyclerAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


















    }
}
