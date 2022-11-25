package com.example.faltei;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    private Button btnEdit;
    private Button btnComplet;

    private EditText mat;
    private EditText cod;
    private EditText prof;
    private EditText cre;



    private DataBase db;
    private Disciplina disciplina;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new DataBase(this);

        btnEdit = (Button) findViewById(R.id.editEXML);
        btnComplet = (Button) findViewById(R.id.completEXML);
        mat = (EditText) findViewById(R.id.materiaEXML);
        cod = (EditText) findViewById(R.id.codEXML);
        prof = (EditText) findViewById(R.id.profEXML);
        cre = (EditText) findViewById(R.id.credEXML);


        btnComplet.setOnClickListener(view -> {
            String matEdit = mat.getText().toString();
            disciplina = db.pesquisaDisciplina(matEdit);
            if (disciplina.get_id() == 0) {
                Toast.makeText(Edit.this, "Matéria inexistente", Toast.LENGTH_LONG).show();
                mat.setText("");
                cod.setText("");
                prof.setText("");
                cre.setText("");

            }

            else {
                mat.setText(disciplina.getMat());
                cod.setText(disciplina.getCod());
                prof.setText(disciplina.getProf());
                cre.setText(String.valueOf(disciplina.getCre()));

            }
        });

        btnEdit.setOnClickListener(view -> {
            if (mat.getText().length() == 0) {
                Toast.makeText(Edit.this, "Insira a matéria", Toast.LENGTH_SHORT).show();
            }
            else {
                String materia = mat.getText().toString();
                String codigo = cod.getText().toString();
                String docente = prof.getText().toString();
                int creditos = Integer.parseInt(cre.getText().toString());

                disciplina.setMat(materia);
                disciplina.setCod(codigo);
                disciplina.setProf(docente);
                disciplina.setCre(creditos);


                long id = db.salvaDisciplina(disciplina);

                if (id != -1)
                    Toast.makeText(Edit.this,"Atualizado",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Edit.this,"Não foi possível editar.",Toast.LENGTH_LONG).show();

                mat.setText("");
                cod.setText("");
                prof.setText("");
                cre.setText("");

            }
        });
    }
}