package com.example.faltei;

public class Disciplina {
    private long _id;
    private String mat;
    private String cod;
    private String prof;

    public Disciplina(long _id, String mat, String cod, String prof) {
        this._id = _id;
        this.mat = mat;
        this.cod = cod;
        this.prof = prof;
    }

    public long get_id() {
        return _id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getMat() {
        return mat;
    }
    public void setMat(String nome) {
        this.mat = nome;
    }
    public String getCod() {
        return cod;
    }
    public void setCod(String nome) {
        this.cod = nome;
    }
    public String getProf() {
        return prof;
    }
    public void setProf(String email) {
        this.prof = email;
    }

    @Override
    public String toString() {
        return  "\n"+ "Matéria :"+mat+ "\n"+
                "Código :"+cod+"\n"+
                "Docente: "+prof+"\n";
    }
}
