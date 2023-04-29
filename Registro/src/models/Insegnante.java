
package models;

import controller.GestoreRegistro;

import java.util.ArrayList;

public class Insegnante extends Persona{
    private final ArrayList<Classe> classi = new ArrayList<>();

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    void addVoto(Classe c, Studente s, Voto val) {
        if (val == null) throw new NullPointerException("VOTO non valido");
        s.addVoto(val);
    }

    void addComunicazione(GestoreRegistro c, Comunicazione com) {
        if (c == null) throw new NullPointerException("CLASSE non valida");
        c.addComunicazione(com);
    }
}
