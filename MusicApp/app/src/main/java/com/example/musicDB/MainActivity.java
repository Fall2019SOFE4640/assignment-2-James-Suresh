package com.example.musicDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/*
Name: James Suresh
id: 100556972

resources

code based on To do Lab done in labs
&
official dev code android studio
 */

public class MainActivity extends AppCompatActivity {

    Button newMusic;
    ListView musicList;
    MusicDB musicDB;
    private String search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newMusic = (Button) findViewById(R.id.newMusicBtn);
        musicList = (ListView) findViewById(R.id.musicList);

        ArrayList<String> titles = new ArrayList<>();
        musicDB = new MusicDB(this);

        MusicClass mp3 = new MusicClass();

        Intent intent = getIntent();
        if(intent.getExtras()!=null)
        {
            search = intent.getStringExtra("query");
        }
        titles = musicDB.getTitles();
        if (titles.size()==0) {
            mp3.setContent("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
            mp3.setTitle("SoundHelix Song 1");
            musicDB.addMusic(mp3);

            mp3.setContent("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3");
            mp3.setTitle("SoundHelix Song 2");
            musicDB.addMusic(mp3);

            mp3.setContent("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3");
            mp3.setTitle("SoundHelix Song 3");
            musicDB.addMusic(mp3);

            mp3.setContent("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3");
            mp3.setTitle("SoundHelix Song 4");
            musicDB.addMusic(mp3);
        }
        titles = musicDB.getTitles();


        if (search.length()>0) {
            //Toast message =  Toast.makeText(this,search,Toast.LENGTH_LONG);
           // message.show();
            int searchfound=0;
            for (int i=0; i<titles.size();i++)
            {
                    if (search.equals(titles.get(i)))
                    {
                        String title = titles.get(i) ;
                        Intent intent2 = new Intent(MainActivity.this, MusicInfo.class);
                        intent2.putExtra("title",title);
                        searchfound++;

                        startActivity(intent2);
                    }
            }
            if(searchfound==0)
            {
                Toast message =  Toast.makeText(this,"search not found",Toast.LENGTH_LONG);
                message.show();
            }


        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        musicList.setAdapter(adapter);


        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MusicInfo.class);
                intent.putExtra("title",title);


                startActivity(intent);
            }
        });


    }
    public void newMusic(View v)
    {
        Intent intent = new Intent(this, Music.class);
        startActivity(intent);
    }

    public void search(View v)
    {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    


}



