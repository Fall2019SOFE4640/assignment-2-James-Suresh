package com.example.musicDB;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class MusicInfo extends Activity {

    EditText title;
    EditText content;
    Button back;
    Intent intent;
    String T="";
    String C="";
    MediaPlayer mediaPlayer = new MediaPlayer();

    int pos=-1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_music);
        intent = getIntent();
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        String old_title = intent.getStringExtra("title");
        MusicClass note = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        note = musicDB.getMusic(old_title);
        title.setText(note.getTitle());
        content.setText(note.getContent());
        T=note.getTitle();
        C=note.getContent();




    }
    public void back(View v)
    {

        finish();
    }
    public void delete(View v)
    {
        System.out.print("delMusic Start");
        String title = T;
        String content = C;
        MusicClass music = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        music = musicDB.getMusic(T);
        musicDB.delMusic(music);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }
    public void save(View v)
    {
        System.out.print("delMusic Start");
        MusicClass note = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        note = musicDB.getMusic(T);
        MusicClass note2 = new MusicClass();

        note2.setTitle(title.getText().toString());
        note2.setContent(content.getText().toString());

        musicDB.updateMusic(note,note2);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void play(View v) throws IOException {
        MusicClass music = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        music = musicDB.getMusic(T);
        MusicClass note2 = new MusicClass();

        Uri myUri = Uri.parse(content.getText().toString()); // initialize Uri here

        if(pos==-1) {
            pos=0;
            mediaPlayer.setDataSource(getApplicationContext(), myUri);
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer player) {
                    player.start();
                }

            });

        }
        else{
            mediaPlayer.seekTo(pos);
            mediaPlayer.start();

        }
    }
    public void pause(View v) throws IOException {
        MusicClass note = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        note = musicDB.getMusic(T);
        MusicClass note2 = new MusicClass();

        Uri myUri = Uri.parse(content.getText().toString()); // initialize Uri here

        pos=mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();

    }
    public void stop(View v) throws IOException {
        MusicClass note = new MusicClass();
        MusicDB musicDB = new MusicDB(this);
        note = musicDB.getMusic(T);
        MusicClass note2 = new MusicClass();

        Uri myUri = Uri.parse(content.getText().toString()); // initialize Uri here
        pos=0;
        mediaPlayer.pause();


    }
}
