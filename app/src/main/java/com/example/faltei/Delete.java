package com.example.faltei;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.faltei.databinding.ActivityDeleteBinding;

public class Delete extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDeleteBinding binding;
    //variavel para inserir o nome do contato para apagar
    private EditText disciplina;
    //botão para apagar
    private Button btn_apaga;

    private DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        DB = new DataBase(this);

        disciplina =  findViewById(R.id.materiaDXML);
        btn_apaga = findViewById(R.id.deleteDXML);

        //listener do botão apagar chama o método apaga contado da classe ContatosDB
        btn_apaga.setOnClickListener(v -> {
            //se não encontrar o registro devolve 0 senão devolve -1
            int count = DB.deleteDisciplina(disciplina.getText().toString());
            //não encontrou contato
            if (count == 0) {
                Toast.makeText(Delete.this, "Matéria inexistente", Toast.LENGTH_SHORT).show();
                disciplina.setText("");
                // encontrou e apagou
            } else {
                Toast.makeText(Delete.this, "Matéria deletada", Toast.LENGTH_SHORT).show();
                disciplina.setText("");
            }
        });
    }
}