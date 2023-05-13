package models;

import models.tools.Data;
public class Attivita {
    private Classe classe;
    private Insegnante insegnante;
    private Data data;
    private String contenuto;
    private int oraInizio;
    private int oraFine;
    private String tipo;

    public Attivita(Classe classe, Insegnante insegnante, Data data, String contenuto, int oraInizio, int oraFine, String tipo) {
        this.classe = classe;
        this.insegnante = insegnante;
        this.data = data;
        this.contenuto = contenuto;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.tipo = tipo;
    }

    public Classe getClasse() {
        return classe;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public Data getData() {
        return data;
    }

    public String getContenuto() {
        return contenuto;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public String getTipo() {
        return tipo;
    }
}
