package models;

import models.tools.Data;
public class Attivita {
    private final Classe classe;
    private final Insegnante insegnante;
    private final Data data;
    private final String contenuto;
    private final int oraInizio;
    private final int oraFine;
    private final String tipo;

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
