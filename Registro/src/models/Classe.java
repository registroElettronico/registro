
package models;

import controllerFile.GestoreFile;
import models.removed.Comunicazione;
import models.removed.Lezione;

import java.io.*;
import java.util.ArrayList;
public class Classe {
    private final String sezione;
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Attivita> attivita = new ArrayList<>();
    //private final ArrayList<Lezione> lezioni = new ArrayList<>();
    //private final ArrayList<Comunicazione> comunicazioni = new ArrayList<>();

    public Classe(String sezione) {
        this.sezione = sezione;
    }

    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }
    
    public void addInsegnante(Insegnante i) throws IOException {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
        GestoreFile.addTeacherToClass(this, i);
    }
    
    public void addStudente(Studente s)  {
        if (s == null) throw new NullPointerException("Studente non valido");
        studenti.add(s);
    }
    
    public void addAttivita (Attivita a) {
        if (a == null) throw new NullPointerException("Attività non valida");
        attivita.add(a);
    }

    /*
    public void addLezione (Lezione l) {
        if (l == null) throw new NullPointerException("Lezione non valida");
        lezioni.add(l);
    }

    void addComunicazione(Comunicazione com) {
        if (com == null) throw new NullPointerException("COMUNICAZIONE non valida");
        comunicazioni.add(com);
    }

     */

    public String getSezione() {
        return sezione;
    }

    public Studente getStudente(String nome, String cognome){
        for (Studente s: this.studenti) if (s.getNome().equals(nome) && s.getCognome().equals(cognome)) return s;
        return null;
    }

    public ArrayList<Attivita> getAttivita() {
        return attivita;
    }
}
