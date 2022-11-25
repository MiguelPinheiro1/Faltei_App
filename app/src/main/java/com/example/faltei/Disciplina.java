package com.example.faltei;

public class Disciplina {
    private long _id;
    private String mat;
    private String cod;
    private String prof;
    private int cre;
    private int falta;

    public Disciplina(long _id, String mat, String cod, String prof, int cre, int falta) {
        this._id = _id;
        this.mat = mat;
        this.cod = cod;
        this.prof = prof;
        this.cre = cre;
        this.falta = falta;
    }

    public long get_id() {return _id;}
    public void set_id(long _id) {this._id = _id;}

    public String getMat() {return mat;}
    public void setMat(String materia) {this.mat = materia;}

    public String getCod() {return cod;}
    public void setCod(String codigo) {this.cod = codigo;}

    public String getProf() {return prof;}
    public void setProf(String docente) {this.prof = docente;}

    public int getCre() {
        return cre;
    }
    public void setCre(int creditos) {
        this.cre = creditos;
    }

    public int getFalta() {
        return falta;
    }
    public void setFalta(int faltas) {
        this.falta = faltas;
    }

    @Override
    public String toString() {
        return  "\n" + "Matéria: " + mat +
                "\n" + "Código: " + cod +
                "\n" + "Docente: " + prof +
                "\n" + "Créditos: " + cre +
                "\n" + "Faltas Máx: " + ((100*(cre*15) -75*(15*cre))/(100*cre)) +
                "\n" + "Faltas: " + falta + "\n";
    }
}
