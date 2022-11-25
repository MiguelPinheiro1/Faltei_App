package com.example.faltei;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Faltas extends AppCompatActivity {

    private Button btnEditF;
    private Button btnCompletF;

    private EditText mat;
    private EditText falta;

    private DataBase db;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltas);

        db = new DataBase(this);

        btnCompletF = (Button) findViewById(R.id.completFXML);
        btnEditF = (Button) findViewById(R.id.editFXML);
        mat = (EditText) findViewById(R.id.materiaFXML);
        falta = (EditText) findViewById(R.id.totalfFXML);

        btnCompletF.setOnClickListener(view -> {
            String matFaltas = mat.getText().toString();

            disciplina = db.pesquisaDisciplina(matFaltas);
            if (disciplina.get_id() == 0) {
                Toast.makeText(Faltas.this, "Matéria inexistente", Toast.LENGTH_LONG).show();
                mat.setText("");
                falta.setText("");
            }
            else {
                mat.setText(disciplina.getMat());
                falta.setText(String.valueOf(disciplina.getFalta()));
            }
        });

        btnEditF.setOnClickListener(view -> {
            if (mat.getText().length() == 0) {
                Toast.makeText(Faltas.this, "Insira a matéria", Toast.LENGTH_SHORT).show();
            }
            else {
                String materia = mat.getText().toString();
                int faltas =Integer.parseInt(falta.getText().toString());
                disciplina.setMat(materia);
                disciplina.setFalta(faltas);
                long id = db.salvaDisciplina(disciplina);
                if (id != -1)
                    Toast.makeText(Faltas.this,"Atualizado",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Faltas.this,"Não foi possível editar.",Toast.LENGTH_LONG).show();

                mat.setText("");
                falta.setText("");
            }
        });
    }
}
