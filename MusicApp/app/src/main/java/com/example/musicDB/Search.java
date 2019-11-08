package com.example.musicDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search extends AppCompatActivity {


    EditText s;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        s = (EditText) findViewById(R.id.editText);
        b= (Button) findViewById(R.id.searchBtn);

    }

    public void search (View view){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("query",s.getText().toString());
        startActivity(intent);


    }

}
