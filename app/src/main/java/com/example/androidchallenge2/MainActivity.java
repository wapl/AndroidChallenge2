package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     int CREATEREQUESTCODE=3,CATEGORYREQUESTCODE=4;
     RecyclerView recyclerView;
     PostAdapter adapter;
     RecyclerView.LayoutManager manager;
     Spinner spinner;
     DatabaseReference databaseCategory;
     DatabaseReference databasePost;
     ArrayList<String> options=new ArrayList<String>();
     ArrayList<String> Categories=new ArrayList<String>();
     String filter="";
        TextView txt3;
     Button addPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPost=findViewById(R.id.btnAddPost);
        recyclerView = findViewById(R.id.recycler_area);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        spinner=findViewById(R.id.spinner);
        txt3=findViewById(R.id.textView3);
        databaseCategory=FirebaseDatabase.getInstance().getReference("Categories");
        databasePost=FirebaseDatabase.getInstance().getReference("Posts");
        options.add("Creation Date");



        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    protected void onStart() {

        super.onStart();
        databasePost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    PostModel postModel=postSnapshot.getValue(PostModel.class);

                        if(postModel.getCategory().equals(filter) || filter=="")
                        {
                            adapter.addItem(postModel);
                        }




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild("Important") && !dataSnapshot.hasChild("Professional") && !dataSnapshot.hasChild("Personal")  )
                {

                    databaseCategory.child("Persona").setValue("Personal");
                    databaseCategory.child("Professional").setValue("Professional");
                    databaseCategory.child("Important").setValue("Important");
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String Category=postSnapshot.getValue(String.class);
                    if(!Categories.contains(Category))
                    {
                        Categories.add(Category);
                        options.add(Category);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addPost(View v)
    {
        Intent intent=new Intent(this,CreatePost.class);
        intent.putStringArrayListExtra("CATEGORIES",Categories);
        startActivityForResult(intent,CREATEREQUESTCODE);
    }
    public void addCategory(View v)
    {
        Intent intent=new Intent(this,CreateCategory.class);
        startActivityForResult(intent,CATEGORYREQUESTCODE);
    }
    public void Sort(View v)
    {
       adapter.Filter(spinner.getSelectedItem().toString());
       if(spinner.getSelectedItem().toString().equals("Creation Date"))
           txt3.setText("MATCH");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CREATEREQUESTCODE)
        {
            if(resultCode==1)
            {

                String id=databasePost.push().getKey();
                PostModel postModel=new PostModel(data.getStringExtra("Title"),data.getStringExtra("Description"),data.getStringExtra("Category"),id);
                databasePost.child(id).setValue(postModel);
                adapter.addItem(postModel);


            }
        }
        else if (requestCode==CATEGORYREQUESTCODE)
        {
            if(resultCode==10)
            {
                String Category=data.getStringExtra("ADDED");
                if(!Categories.contains(Category))
                {
                    databaseCategory.child(Category).setValue(Category);
                }

            }
        }

    }

}
