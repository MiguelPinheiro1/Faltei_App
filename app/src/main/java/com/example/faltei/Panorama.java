package com.example.faltei;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Panorama extends AppCompatActivity {

    private ListView myList;

    //adapter da listView
    private ArrayAdapter adapter;

    //array para a lista de contatos
    private static ArrayList<Disciplina> exibList;

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);


        db = new DataBase(this);

        myList = (ListView) findViewById(R.id.listview);
        //chama o método findAll que devolve um array e guarda em exibeLista
        exibList = db.findAll();
        //criação de uma instância de um ListAdapter utilizando um layout nativo
        adapter = new ArrayAdapter<Disciplina>(this, android.R.layout.simple_list_item_1, exibList);

        //associação a ListView com o adapter
        myList.setAdapter(adapter);
    }
}