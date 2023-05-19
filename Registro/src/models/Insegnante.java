
package models;

import controllerFile.GestoreFile;
import models.removed.Comunicazione;
import models.removed.Lezione;
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

    public void addVoto(Voto v) throws IOException {
        v.getStudente().addVoto(v);
        GestoreFile.addVoto(v);
    }

    /*                                                                  NON FINITO
    public void addComunicazione(Comunicazione com) {
        com.getTarget().addComunicazione(com);
    }
    */

    public void addAttivita (Attivita a) throws IOException {
        if (a == null) throw new NullPointerException("Attività non valida");
        a.getClasse().addAttivita(a);
        GestoreFile.addAttivita(a);
    }

    /*                                                                  NON FINITO
    public void addLezione(Classe c, Lezione l) {
        if (c == null) throw new NullPointerException("Classe non valida");
        c.addLezione(l);
    }
    */

    public void addAssenza(Studente s) throws IOException {
        s.addAssenza();
        GestoreFile.addAssenza(s);
    }

    public void addRapporto(Rapporto r) throws IOException {
        r.getStudente().addRapporto(r);
        GestoreFile.addRapporto(r);
    }

    public void addClasse(Classe c) throws IOException {
        if (c == null) throw new NullPointerException("Classe non valida");

        c = GestoreFile.addClasse(c);

        this.classi.add(c);
        c.addInsegnante(this);
    }
}
