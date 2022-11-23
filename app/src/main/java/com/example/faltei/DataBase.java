package com.example.faltei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String tag = "SQL";
    public static final String banc = "BancodeDados.db";
    public static final int version = 1;
    public static final String TABLE_MAT = "disciplinas";
    public static final String COLUMN0 = "_id";
    public static final String COLUMN1 = "materia";
    public static final String COLUMN2 = "codigo";
    public static final String COLUMN3 = "docente";
    public static final String COLUMN4 = "credito";
    public static final String COLUMN5 = "assiduidade";
    public static final String COLUMN6 = "faltasmax";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_MAT + " ("
                    + COLUMN0 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN1 + "TEXT,"
                    + COLUMN2 + "TEXT,"
                    + COLUMN3 + "TEXT,"
                    + COLUMN4 + "INT,"
                    + COLUMN5 + "INT,"
                    + COLUMN6 + "INT)";

    public DataBase(Context context) {
        super(context, banc, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.d(tag, "Tabela"+TABLE_MAT+" criada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //5 - Criando o metodo utilizado para salvar os contatos ou atualizar  contato existente
    //recebe um contato( objeto Contato) já existente para atualização ou um novo contato para cadastro

    // o método recebe como entrada um objeto da classe contato
    public long salvaDisciplina(Disciplina disciplina){
        //lê o valor do id do objeto ( Se id = 0 cadastro/ Se id!=0 atualização)
        long id = disciplina.get_id();
        //abre a conexão com o banco de dados
        SQLiteDatabase db = getWritableDatabase();//abre a conexão com o banco de dados
        try{

            //cria a variável que aponta para o objeto ContentValues()  e que representa o conteudo do registro a ser alterado ou criado
            //em um formato que o banco de dados entende.
            ContentValues valores = new ContentValues();
            //transfere os valores das variáveis de instancia do objeto para a variável valor na forma (chave, valor)
            valores.put(COLUMN1,disciplina.getMat());
            valores.put(COLUMN2,disciplina.getCod());
            valores.put(COLUMN3,disciplina.getProf());
            valores.put(COLUMN4,disciplina.getCre());
            valores.put(COLUMN5,disciplina.getAssi());
            valores.put(COLUMN6,disciplina.getFmax());
            if(id!=0){//se já existe este contato e queremos simplesmente atualizá-lo
                // String _id = String.valueOf(id);
                //String[] whereArgs = new String[]{_id};
                int count = db.update(TABLE_MAT, valores, "_id=?",new String[]{String.valueOf(id)});
                return count; // retorna o numero de linhas alteradas.

            }
            else{//se não existe o contato e vamos incluí-lo na tabela.
                id = db.insert(TABLE_MAT,null,valores);
                return id; //retorna o ID da nova linha inserida ou -1 se ocorrer erro

            }
        }finally{
            db.close();//fecha a conexão
        }
    }


    //6 - metodo utilizado para apagar um contato
    //Este método recebe uma String com o nome do contato

    public int deleteDisciplina(String materiaDisciplina) {
        SQLiteDatabase db = getWritableDatabase();//abre a conexão com o banco de dados
        try {
            //apago o registro onde na coluna nome o valor do registro corresponde a String passada.
            int count = db.delete(TABLE_MAT,"materia=?", new String[]{materiaDisciplina});
            Log.i(tag,"deletou registro =>" + count);
            return count;//retorna zero se não houver contato registro que coincida com o valor passado para o método.
        } finally {
            db.close();//fecha a conexão
        }
    }

// MIGUEL: Dae bro! Aposto que o erro na visualização está aqui embaixo!!!!!!!!!!

    //7 - metodo que devolve um ArrayList com todos os contatos da agenda.

    public ArrayList<Disciplina> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Disciplina> lista = new ArrayList<>();
        try {
            Cursor d = db.query(TABLE_MAT, null, null, null, null, null, null);
            if (d.moveToFirst())
            {do {
                long id = d.getLong(d.getColumnIndexOrThrow("_id"));
                String mat = d.getString(d.getColumnIndexOrThrow("materia"));
                String cod = d.getString(d.getColumnIndexOrThrow("codigo"));
                String prof = d.getString(d.getColumnIndexOrThrow("docente"));
                int cre = d.getInt(d.getColumnIndexOrThrow("credito"));
                int assi = d.getInt(d.getColumnIndexOrThrow("assiduidade"));
                float fmax = d.getFloat(d.getColumnIndexOrThrow("faltasmax"));
                Disciplina currentDisci = new Disciplina(id, mat, cod, prof, cre, assi, fmax);
                lista.add(currentDisci);

                } while (d.moveToNext());
            }
            return lista;
        } finally {db.close();}
    }


    // 8 - pesquisa um contato específico pelo nome do contato passado pela String nomeContato do método.
    //será utilizado para carregar o contato no processo de atualização

    public Disciplina pesquisaDisciplina(String materiaDisciplina) {
        SQLiteDatabase db = getWritableDatabase();
        long id;
        String mat;
        String cod;
        String prof;
        int cre = 0;
        int assi = 0;
        float fmax = 0;

        try {
            Cursor d = db.query(TABLE_MAT, null,"materia=?", new String[]{materiaDisciplina}, null, null, null, null);
            if(d.moveToFirst()) {//verifica se o contato existe, se sim extrai os valores para criação  um objeto Contato com os valores
                id = d.getLong(d.getColumnIndexOrThrow("_id"));
                mat = d.getString(d.getColumnIndexOrThrow("materia"));
                cod = d.getString(d.getColumnIndexOrThrow("codigo"));
                prof = d.getString(d.getColumnIndexOrThrow("docente"));
                cre = d.getInt(d.getColumnIndexOrThrow("credito"));
                assi = d.getInt(d.getColumnIndexOrThrow("faltas"));
                fmax = d.getFloat(d.getColumnIndexOrThrow("faltasmax"));

                //Disciplina disciplina = new disciplina(id, materia, codigo, docente);
            }
            //Se não for encontrado, define  valores dummy para criação de um objeto
            //pois o método retorna um objeto do tipo contato
            else{
                id = 0;
                mat = "math";
                cod = "EB999" ;
                prof = "Jorginho";
                cre = 1;
                assi = 2;
                fmax = 3;
            }
            Disciplina disciplina = new Disciplina(id, mat, cod, prof, cre, assi, fmax);
            return disciplina;
        } finally {db.close();}
    }
}