package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreatePost extends AppCompatActivity {

    EditText Title;
    EditText Description;
    Button addbtn;
    Spinner categories;
    ArrayList<String> Categories=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Title=findViewById(R.id.Title);
        Description=findViewById(R.id.Description);
        addbtn=findViewById(R.id.addBtn);
        categories=findViewById(R.id.Categories);

        Intent intent=getIntent();
        Categories=intent.getStringArrayListExtra("CATEGORIES");

        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(arrayAdapter);


    }

    public void Post(View v)
    {
        Intent intent=new Intent();
        intent.putExtra("Title",Title.getText().toString());
        intent.putExtra("Description",Description.getText().toString());
        intent.putExtra("Category",categories.getSelectedItem().toString());
        setResult(1,intent);
        finish();
    }

}
