package com.example.musicDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Music extends AppCompatActivity {
    Button saveB;
    Button delB;
    EditText titleT;
    EditText contentT;
    MusicDB musicDB;
    MusicClass music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        musicDB = new MusicDB(this);
        delB = (Button) findViewById(R.id.deleteBtn);
        titleT = (EditText) findViewById(R.id.titleTxt);
        contentT = (EditText)findViewById(R.id.contentTxt);
    }
    public void save(View v)
    {
        String title = titleT.getText().toString();
        String content = contentT.getText().toString();
        music = new MusicClass(title, content);
        musicDB.addMusic(music);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void delete(View v)
    {
        System.out.print("delMusic Start");
        String title = titleT.getText().toString();
        String content = contentT.getText().toString();
        music = new MusicClass(title, content);

        musicDB.delMusic(music);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
