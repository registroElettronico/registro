package models;

import models.tools.Data;

public class Rapporto {
    private final Insegnante insegnante;
    private final Studente studente;

    public Rapporto(Insegnante insegnante, Studente studente, String motivo, Data data) {
        this.insegnante = insegnante;
        this.studente = studente;
        this.motivo = motivo;
        this.data = data;
    }

    private final String motivo;
    private final Data data;

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public String getMotivo() {
        return motivo;
    }

    public Data getData() {
        return data;
    }

    public Studente getStudente() {
        return studente;
    }
}
