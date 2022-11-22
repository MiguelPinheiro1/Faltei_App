package com.example.faltei;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Panorama extends AppCompatActivity {
    //listview para exibir a lista de contatos
    private ListView minhaLista;

    //adapter da listView
    private ArrayAdapter adapter;

    //array para a lista de contatos
    private static ArrayList<Disciplina> exibeLista;

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);


        db = new DataBase(this);

        minhaLista = (ListView) findViewById(R.id.listView);
        //chama o método findAll que devolve um array e guarda em exibeLista
        exibeLista = db.findAll();
        //criação de uma instância de um ListAdapter utilizando um layout nativo
        adapter = new ArrayAdapter<Disciplina>(this,android.R.layout.simple_list_item_1, exibeLista);

        //associação a ListView com o adapter
        minhaLista.setAdapter(adapter);
    }
}