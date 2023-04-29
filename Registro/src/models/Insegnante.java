
package models;

import controller.GestoreRegistro;
import models.tools.Data;

import java.util.ArrayList;

public class Insegnante extends Persona{
    private final ArrayList<Classe> classi = new ArrayList<>();

    public Insegnante(String nome, String cognome, char genere, Data dataDiNascita, String email, String password) {
        super(nome, cognome, genere, dataDiNascita, email, password);
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    void addVoto(Studente s, Voto val) {
        if (val == null) throw new NullPointerException("VOTO non valido");
        s.voti.add(val);
    }

    void addComunicazione(Comunicazione com) {
        com.getTarget().addComunicazione(com);
    }

    void addAttivita (Classe c, Attivita a) {
        if (c == null) throw new NullPointerException("Classe non valida");
        c.addAttivita(a);
    }

    void addLezione(Classe c, Lezione l) {
        if (c == null) throw new NullPointerException("Classe non valida");
        c.addLezione(l);
    }

    void addAssenza(Studente s) {
        if (s == null) throw new NullPointerException("Studente non valida");
        s.assenze.add(false);
    }

    void addRapporto(Studente s, Nota n) {
        if (s == null) throw new NullPointerException("Studente non valida");
        s.note.add(n);
    }
}
