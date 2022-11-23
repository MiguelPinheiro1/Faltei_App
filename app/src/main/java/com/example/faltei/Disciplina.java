package com.example.faltei;

public class Disciplina {
    private long _id;
    private String mat;
    private String cod;
    private String prof;
    private int cre;
    private int assi;
    private float fmax;

    public Disciplina(long _id, String mat, String cod, String prof, int cre, int assi, float fmax) {
        this._id = _id;
        this.mat = mat;
        this.cod = cod;
        this.prof = prof;
        this.cre = cre;
        this.assi = assi;
        this.fmax = fmax;
    }

    public long get_id() {return _id;}
    public void set_id(long _id) {this._id = _id;}

    public String getMat() {return mat;}
    public void setMat(String materia) {this.mat = materia;}

    public String getCod() {return cod;}
    public void setCod(String codigo) {this.cod = codigo;}

    public String getProf() {return prof;}
    public void setProf(String docente) {this.prof = docente;}

    public int getCre() {return cre;}
    public void setCre(int credito) {this.cre = credito;}

    public int getAssi() {return assi;}
    public void setAssi(int assiduidade) {this.assi = assiduidade;}

    public Float getFmax() {
        return fmax;
    }
    public void setFmax(float faltasmax) {this.fmax = faltasmax;}

    @Override
    public String toString() {
        return "\n"+ "Matéria: " + mat + "\n" +
                "Código: " + cod + "\n" +
                "Docente: " + prof + "\n" +
                "Créditos: " + cre + "\n" +
                "Minhas Faltas: " + assi + "\n" +
                "Faltas Máximas" + fmax + "\n";
    }
}