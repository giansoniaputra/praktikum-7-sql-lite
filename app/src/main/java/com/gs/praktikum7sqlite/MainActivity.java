package com.gs.praktikum7sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView listview;
    protected Cursor cursor;
    database database;
    Button tambah;

    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambah = (Button)findViewById(R.id.btnTambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CreateAction.class);
                startActivity(i);
            }
        });

        ma=this;
        database = new database(this);
        RefreshList();
    }

    public void RefreshList()
    {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor=db.rawQuery("SELECT * FROM mahasiswa", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for(int i = 0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            daftar[i] = i+1 +" | "+cursor.getString(0).toString()+ " | "+cursor.getInt(1);
        }
        listview = (ListView) findViewById(R.id.ListMHS);
        listview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        listview.setSelected(true);

        ((ArrayAdapter)listview.getAdapter()).notifyDataSetInvalidated();
    }
}