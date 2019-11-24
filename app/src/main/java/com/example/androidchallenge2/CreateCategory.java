package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCategory extends AppCompatActivity {

    EditText txtCreate;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        txtCreate=findViewById(R.id.txtCreate);
        btnCreate=findViewById(R.id.btnCreate);
    }
    public void add(View v)
    {
        Intent intent=new Intent();
        intent.putExtra("ADDED",txtCreate.getText().toString());
        setResult(10,intent);
        finish();
    }

}
