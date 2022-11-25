package com.example.faltei;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Panorama extends AppCompatActivity {

    private ListView minhaLista;

    private ArrayAdapter adapter;

    private static ArrayList<Disciplina> exibeLista;

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);


        db = new DataBase(this);

        minhaLista = (ListView) findViewById(R.id.listView);

        exibeLista = db.findAll();

        adapter = new ArrayAdapter<Disciplina>(this,android.R.layout.simple_list_item_1, exibeLista);

        minhaLista.setAdapter(adapter);
    }
}