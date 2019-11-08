package com.example.musicDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MusicDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MusicDB";
    private static final String TABLE_NAME = "Music";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_CONTENT = "content";

    public MusicDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Music_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY,"+COL_TITLE+" TEXT,"+COL_CONTENT+" TEXT)";
        db.execSQL(CREATE_Music_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addMusic(MusicClass music)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, music.getTitle());
        values.put(COL_CONTENT, music.getContent());

        db.insert(TABLE_NAME, null,values);
        db.close();
    }

    void updateMusic(MusicClass Music, MusicClass Music2)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
          db.execSQL("UPDATE Music SET title='"+Music2.getTitle()+"' WHERE "+COL_TITLE+"='"+Music.getTitle()+"' AND "+COL_CONTENT+"='"+Music.getContent()+"'");
          db.execSQL("UPDATE Music SET content='"+Music2.getContent()+"' WHERE "+COL_TITLE+"='"+Music2.getTitle()+"' AND "+COL_CONTENT+"='"+Music.getContent()+"'");

        db.close();
    }

    void delMusic(MusicClass music)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("start executing");

        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COL_TITLE+"='"+music.getTitle()+"' AND "+COL_CONTENT+"='"+music.getContent()+"'");
        System.out.println("done executing");
        db.close();
    }


    MusicClass getMusic(String title)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        MusicClass Music = new MusicClass();
        //Assume no duplicate titles
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TITLE+" = ?",new String [] {title});
        c.moveToFirst();
        if(c.isNull(c.getColumnIndex(COL_TITLE)))
        {
            db.close();
            return null;
        }
        Music.setTitle(c.getString(c.getColumnIndex(COL_TITLE)));
        Music.setTitle(c.getString(c.getColumnIndex(COL_TITLE)));
        Music.setContent(c.getString(c.getColumnIndex(COL_CONTENT)));
        db.close();
        return Music;
    }





    ArrayList<String> getTitles()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String temptTitle = "";
        ArrayList<String> titles = new ArrayList<>();
        String query = "SELECT "+ COL_TITLE +" FROM "+TABLE_NAME;
        //Assume no duplicate titles
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!(c.isAfterLast()))
        {
            temptTitle = c.getString(c.getColumnIndex(COL_TITLE));
            titles.add(temptTitle);
            c.moveToNext();
        }
        db.close();
        return titles;
    }
}
