package com.example.faltei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String TAG = "SQL";
    public static final String NOME_BANCO = "BancodeDados.db";
    public static final int VERSAO_BANCO = 10;
    public static final String TABLE_MAT = "disciplinas";
    public static final String COLUNA0 = "_id";
    public static final String COLUNA1 = "materia";
    public static final String COLUNA2 = "codigo";
    public static final String COLUNA3 = "docente";
    public static final String COLUNA4 = "creditos";
    public static final String COLUNA5 = "faltas";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " +TABLE_MAT + " ("
                    + COLUNA0 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA1 + " TEXT,"
                    + COLUNA2 + " TEXT,"
                    + COLUNA3 + " TEXT,"
                    + COLUNA4 + " INT,"
                    + COLUNA5 + " INT)";

    public DataBase(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE);

        Log.d(TAG, "Tabela"+TABLE_MAT+" criada com sucesso");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long salvaDisciplina(Disciplina disciplina){
        long id = disciplina.get_id();

        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues valores = new ContentValues();
            valores.put(COLUNA1,disciplina.getMat());
            valores.put(COLUNA2,disciplina.getCod());
            valores.put(COLUNA3,disciplina.getProf());
            valores.put(COLUNA4,disciplina.getCre());
            valores.put(COLUNA5,disciplina.getFalta());
            if(id!=0){
                int count = db.update(TABLE_MAT, valores, "_id=?",new String[]{String.valueOf(id)});
                return count;
            }
            else{
                id = db.insert(TABLE_MAT,null, valores);
                return id;
            }
        }finally{db.close();}
    }

    public int deleteDisciplina(String materiaDisciplina) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            int count = db.delete(TABLE_MAT,"materia=?", new String[]{materiaDisciplina});
            Log.i(TAG,"deleta registro =>" + count);
            return count;
        } finally {db.close();}
    }

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
                int cre = d.getInt(d.getColumnIndexOrThrow("creditos"));
                int falta = d.getInt(d.getColumnIndexOrThrow("faltas"));

                Disciplina currentDisci = new Disciplina(id, mat, cod, prof, cre, falta);
                lista.add(currentDisci);

            } while (d.moveToNext());
            }
            return lista;
        } finally {db.close();}
    }


    public Disciplina pesquisaDisciplina(String materiaDisciplina) {
        SQLiteDatabase db = getWritableDatabase();
        long id;
        String mat;
        String cod;
        String prof;
        int cre;
        int falta;

        try {
            Cursor d = db.query(TABLE_MAT, null,"materia=?", new String[]{materiaDisciplina}, null, null, null, null);
            if(d.moveToFirst()) {
                id = d.getLong(d.getColumnIndexOrThrow("_id"));
                mat = d.getString(d.getColumnIndexOrThrow("materia"));
                cod = d.getString(d.getColumnIndexOrThrow("codigo"));
                prof = d.getString(d.getColumnIndexOrThrow("docente"));
                cre = d.getInt(d.getColumnIndexOrThrow("creditos"));
                falta = d.getInt(d.getColumnIndexOrThrow("faltas"));
            }

            else{
                id = 0;
                mat = "math";
                cod = "EB999" ;
                prof = "john";
                cre = 6;
                falta = 3;
            }
            Disciplina disciplina = new Disciplina(id, mat, cod, prof, cre, falta);
            return disciplina;
        } finally {db.close();}
    }
}