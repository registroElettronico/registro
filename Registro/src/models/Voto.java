package models;

import models.tools.Data;

public class Voto {
    private float voto;
    private String materia;
    private Data data;

    public Voto(float voto, String materia, Data data) {
        this.voto = voto;
        this.materia = materia;
        this.data = data;
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
}
