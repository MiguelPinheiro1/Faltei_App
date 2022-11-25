package com.example.faltei;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    private EditText disciplina;
    private Button btn_apaga;

    private DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        DB = new DataBase(this);

        disciplina =  findViewById(R.id.materiaDXML);
        btn_apaga = findViewById(R.id.deleteDXML);

        btn_apaga.setOnClickListener(v -> {
            int count = DB.deleteDisciplina(disciplina.getText().toString());
            if (count == 0) {
                Toast.makeText(Delete.this, "Matéria inexistente", Toast.LENGTH_SHORT).show();
                disciplina.setText("");
            } else {
                Toast.makeText(Delete.this, "Matéria deletada", Toast.LENGTH_SHORT).show();
                disciplina.setText("");
            }
        });
    }
}