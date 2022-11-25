package com.example.faltei;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    private Button btnAdd;

    private EditText mat;
    private EditText cod;
    private EditText prof;
    private EditText cre;
    private EditText falta;

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new DataBase(this);

        btnAdd = findViewById(R.id.addAXML);
        mat = findViewById(R.id.materiaAXML);
        cod = findViewById(R.id.codAXML);
        prof = findViewById(R.id.profAXML);
        cre = findViewById(R.id.credAXML);
        falta = findViewById(R.id.totalfAXML);


        btnAdd.setOnClickListener(view -> {
            if (mat.getText().length() == 0 ||
                cod.getText().length() == 0 ||
                prof.getText().length() == 0 ||
                cre.getText().length() == 0 ||
                falta.getText().length() == 0) {
                Toast.makeText(Add.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();

            } else {
                String materia = mat.getText().toString();
                String codigo = cod.getText().toString();
                String docente = prof.getText().toString();
                int creditos = Integer.parseInt(cre.getText().toString());
                int faltas = Integer.parseInt(falta.getText().toString());

                Disciplina discip = new Disciplina(0, materia, codigo, docente, creditos, faltas);

                long id = db.salvaDisciplina(discip);
                if (id != -1)
                    Toast.makeText(Add.this, "Matéria adicionada", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Add.this, "Não foi possível adicionar.", Toast.LENGTH_LONG).show();

                mat.setText("");
                cod.setText("");
                prof.setText("");
                cre.setText("");
                falta.setText("");
            }
        });
    }
}