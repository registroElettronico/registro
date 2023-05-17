package models;

import models.tools.Data;

public class Nota {
    private final Insegnante insegnante;

    public Nota(Insegnante insegnante, String motivo, Data data) {
        this.insegnante = insegnante;
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
}
