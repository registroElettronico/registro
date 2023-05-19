package models;

import models.tools.Data;

public class Voto {
    private final float voto;
    private final String materia;
    private final Data data;
    private final Studente studente;

    public Voto(float voto, String materia, Data data, Studente studente) {
        this.voto = voto;
        this.materia = materia;
        this.data = data;
        this.studente = studente;
    }

    public float getVoto() {
        return voto;
    }
    public String getMateria() {
        return materia;
    }

    public Data getData() {
        return data;
    }

    public Studente getStudente() {
        return studente;
    }
}
