package com.example.faltei;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.faltei.databinding.ActivityEditBinding;

public class Edit extends AppCompatActivity {
    //variáveis correspondes aos botões
    private Button btnAtualiza;
    private Button btnComplet;

    //variáveis correspondente aos campoos que serão preenchido para atualização
    private EditText mat;
    private EditText cod;
    private EditText prof;

    private DataBase db;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Variável criada para apontar para o Banco de dados
        db = new DataBase(this);

        //"linkando" as váriaveis com as Views no XML
        btnAtualiza = (Button) findViewById(R.id.editEXML);
        btnComplet = (Button) findViewById(R.id.completEXML);
        mat = (EditText) findViewById(R.id.materiaEXML);
        cod = (EditText) findViewById(R.id.codEXML);
        prof = (EditText) findViewById(R.id.profEXML);

        //listerner do botão carrega para capturar o nome do contato fornecido pelo usuário
        //Se o contato não existe limpa os campos e devolve um toast.
        //Se o contato existe caputara as informações do contato e exibe para o usuário
        btnComplet.setOnClickListener(view -> {
            String matEdit = mat.getText().toString();
            // chama o método pesquisaContato  para verificar se "nome" (registro) digitado pelo usuário existe
            // se existir o banco devolve um objeto da classe Contato com as informações do registro.
            disciplina = db.pesquisaDisciplina(matEdit);
            // se não existe
            if (disciplina.get_id() == 0) {
                Toast.makeText(Edit.this, "Contato inexistente!", Toast.LENGTH_LONG).show();
                mat.setText("");
                cod.setText("");
                prof.setText("");
            }
            //recebe o contato exibe nos campos para o usuário
            else {
                mat.setText(disciplina.getMat());
                cod.setText(disciplina.getCod());
                prof.setText(disciplina.getProf());
                    /*percent.setText(String.valueOf(disciplina.getPercent()));
                    aulas.setText(String.valueOf(disciplina.getAulas()));
                    dia1.setText(String.valueOf(disciplina.getDia1()));
                    dia2.setText(String.valueOf(disciplina.getDia2()));
                    dia3.setText(String.valueOf(disciplina.getDia3()));*/
            }

        });


        //listener do botão atualiza para processar a atualização do registro na tabela
        btnAtualiza.setOnClickListener(view -> {
            // Verfica se não foi solicitado acidentalmente para atualizar sem ter carregado previamente um contato.
            //ou após um retorno de contato inexistente.
            if (mat.getText().length() == 0) {
                Toast.makeText(Edit.this, "Por favor insira o nome do contato!", Toast.LENGTH_SHORT).show();

            }
            //captura dos campos da tela os valores e modifica os valores dos campos do objeto contato retornado
            else {
                String materia = mat.getText().toString();
                String codigo = cod.getText().toString();
                String docente = prof.getText().toString();
                    /*int porcentagem =Integer.parseInt(percent.getText().toString());
                    int aulasemana =Integer.parseInt(aulas.getText().toString());
                    int d1 =Integer.parseInt(dia1.getText().toString());
                    int d2 =Integer.parseInt(dia2.getText().toString());
                    int d3 =Integer.parseInt(dia3.getText().toString());*/
                disciplina.setMat(materia);
                disciplina.setCod(codigo);
                disciplina.setProf(docente);
                //chama o método salva contato para a atualiação
                long id = db.salvaDisciplina(disciplina);
                //mensagens de retorno da operação de atualização
                if (id != -1)
                    Toast.makeText(Edit.this, "Atualizado", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Edit.this, "Não foi possível editar.", Toast.LENGTH_LONG).show();

                mat.setText("");
                cod.setText("");
                prof.setText("");
            }
        });
    }
}