package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     PostAdapter adapter;
     RecyclerView.LayoutManager manager;
     Spinner spinner;
     DatabaseReference databaseCategory;
     ArrayList<String> options=new ArrayList<String>();
     ArrayList<String> Categories=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_area);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        spinner=findViewById(R.id.spinner);

        databaseCategory=FirebaseDatabase.getInstance().getReference("Categories");


        options.add("Creation Date");
        options.add("Personal Post");
        options.add("Professional Post");
        options.add("Important Post");


        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    protected void onStart() {

        super.onStart();

        options.add("Creation Date");

        Categories.add("Personal Post");
        options.add("Personal Post");

        Categories.add("Professional Post");
        options.add("Professional Post");

        Categories.add("Important Post");
        options.add("Important Post");
        databaseCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String Category=postSnapshot.getValue(String.class);
                    Categories.add(Category);
                    options.add(Category);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
