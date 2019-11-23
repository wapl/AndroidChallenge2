package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     PostAdapter adapter;
     RecyclerView.LayoutManager manager;
     Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_area);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);
        spinner=findViewById(R.id.spinner);



        ArrayList<String> options=new ArrayList<String>();
        options.add("Creation Date");
        options.add("Personal Post");
        options.add("Professional Post");
        options.add("Important Post");


        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }
}
