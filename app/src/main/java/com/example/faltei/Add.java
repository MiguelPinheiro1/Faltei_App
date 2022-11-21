package com.example.faltei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
        //variável corresponde ao botão de cadastrar
    private Button btnAdd;

    //variáveis correspondente aos campoos que serão preenchido para o cadastro do contato
    private EditText mat;
    private EditText cod;
    private EditText prof;

    //Variável criada para apontar para o Banco de dados
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //associando a variável ao um novo objeto  da classe que implementa o banco de dados.
        db = new DataBase(this);

        //"linkando" as váriaveis com as Views no XML
        btnAdd = findViewById(R.id.addAXML);
        mat = findViewById(R.id.materiaAXML);
        cod = findViewById(R.id.codAXML);
        prof = findViewById(R.id.profAXML);



//implementando o Listener do botão.
        btnAdd.setOnClickListener(view -> {
            //verifica se houve tentativa de cadastro sem preenchimento de todos os campos
            if (mat.getText().length() == 0 ||cod.getText().length() == 0||prof.getText().length() == 0) {
            Toast.makeText(Add.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();

            } else {
                //Convertendo os  conteúdos dos EditText para variáveis do JAVA
                String materia = mat.getText().toString();
                String codigo = cod.getText().toString();
                String docente = prof.getText().toString();
                //cria um novo objeto da classe cadastro com as informações para serem inseridas na tabela do banco dados
                Disciplina discip = new Disciplina(0, materia, codigo, docente);
                // chama o método salavaContato do ContatosDB para a inserção na tabela
                long id = db.salvaDisciplina(discip);
                if (id != -1)
                    Toast.makeText(Add.this, "Matéria adicionada", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Add.this, "Não foi possível adicionar.", Toast.LENGTH_LONG).show();

                //limpa as caixa de texto para um novo cadastro.
                mat.setText("");
                cod.setText("");
                prof.setText("");
            }
        });
    }
}