package com.gs.praktikum7sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAction extends AppCompatActivity {
    database database;
    Button btnSimpan;
    EditText nama, nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_action);

        database = new database(this);
        nama = (EditText) findViewById(R.id.inputNama);
        nilai = (EditText) findViewById(R.id.inputnilai);

        btnSimpan=(Button)findViewById(R.id.simpanData);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                String sql = "INSERT INTO mahasiswa(nama,nilai) VALUES('"+nama.getText().toString()+"','"+Integer.parseInt(nilai.getText().toString())+"')";
                db.execSQL(sql);
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}