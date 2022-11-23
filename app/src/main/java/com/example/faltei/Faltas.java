package com.example.faltei;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Faltas extends AppCompatActivity {
    /*//variáveis correspondes aos botões
    private Button btnEditF;
    private Button btnCompletF;

    //variáveis correspondente aos campoos que serão preenchido para atualização
    private EditText mat;
    private EditText assi;


    private DataBase db;
    private Disciplina disciplina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Variável criada para apontar para o Banco de dados
        db = new DataBase(this);

        //"linkando" as váriaveis com as Views no XML
        btnEditF = (Button) findViewById(R.id.editFXML);
        btnCompletF = (Button) findViewById(R.id.completFXML);
        mat = (EditText) findViewById(R.id.materiaFXML);
        assi = (EditText) findViewById(R.id.assiFXML);

        //listerner do botão carrega para capturar o nome do contato fornecido pelo usuário
        //Se o contato não existe limpa os campos e devolve um toast.
        //Se o contato existe caputara as informações do contato e exibe para o usuário
        btnCompletF.setOnClickListener(view -> {
            String matFaltas = mat.getText().toString();
            // chama o método pesquisaContato  para verificar se "nome" (registro) digitado pelo usuário existe
            // se existir o banco devolve um objeto da classe Contato com as informações do registro.
            disciplina = db.pesquisaDisciplina(matFaltas);
            // se não existe
            if (disciplina.get_id() == 0) {
                Toast.makeText(Faltas.this, "Matéria inexistente", Toast.LENGTH_LONG).show();
                mat.setText("");
                assi.setText("");
            }
            //recebe o contato exibe nos campos para o usuário
            else {
                mat.setText(disciplina.getMat());
                assi.setText(disciplina.getAssi());
                *//*percent.setText(String.valueOf(disciplina.getPercent()));
                aulas.setText(String.valueOf(disciplina.getAulas()));
                dia1.setText(String.valueOf(disciplina.getDia1()));
                dia2.setText(String.valueOf(disciplina.getDia2()));
                dia3.setText(String.valueOf(disciplina.getDia3()));*//*
            }
        });


        //listener do botão atualiza para processar a atualização do registro na tabela
        btnEditF.setOnClickListener(view -> {
            // Verfica se não foi solicitado acidentalmente para atualizar sem ter carregado previamente um contato.
            // ou após um retorno de contato inexistente.
            if (mat.getText().length() == 0) {
                Toast.makeText(Faltas.this, "Insira a matéria", Toast.LENGTH_SHORT).show();

            }//captura dos campos da tela os valores e modifica os valores dos campos do objeto contato retornado
            else {
                String materia = mat.getText().toString();
                int assiduidade =Integer.parseInt(assi.getText().toString());
                disciplina.setMat(materia);
                disciplina.setAssi(assiduidade);
                //chama o método salva contato para a atualiação
                long id = db.salvaDisciplina(disciplina);
                //mensagens de retorno da operação de atualização
                if (id != -1)
                    Toast.makeText(Faltas.this,"Atualizado",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Faltas.this,"Não foi possível editar.",Toast.LENGTH_LONG).show();

                mat.setText("");
                assi.setText("");
            }
        });
    }*/
}
