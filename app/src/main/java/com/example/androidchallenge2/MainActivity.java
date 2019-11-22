package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public PostAdapter adapter;
    public RecyclerView.LayoutManager manager;
    public Spinner spinner;

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
        options.add("View by Creation Date");
        options.add("View Personal Post");
        options.add("View Professional Post");
        options.add("View Important Post");


        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }
}
