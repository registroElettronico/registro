
package models;

import controller.GestoreRegistro;
import models.tools.Data;

import java.io.IOException;
import java.util.ArrayList;

public class Insegnante extends Persona{
    private final ArrayList<Classe> classi = new ArrayList<>();

    public Insegnante(String email, String password, String nome, String cognome, Data dataDiNascita, char genere) {
        super(email, password, nome, cognome, dataDiNascita, genere);
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    public void addVoto(Studente s, Voto val) throws IOException {
        s.addVoto(val);
    }

    public void addComunicazione(Comunicazione com) {
        com.getTarget().addComunicazione(com);
    }

    public void addAttivita (Classe c, Attivita a) {
        if (c == null) throw new NullPointerException("Classe non valida");
        c.addAttivita(a);
    }

    public void addLezione(Classe c, Lezione l) {
        if (c == null) throw new NullPointerException("Classe non valida");
        c.addLezione(l);
    }

    public void addAssenza(Studente s) {
        s.addAssenza();
    }

    public void addRapporto(Studente s, Nota n) {
        s.addRapporto(n);
    }

    public void addClasse(Classe c) {
        if (c == null) throw new NullPointerException("Classe non valida");
        this.classi.add(c);
    }
}
