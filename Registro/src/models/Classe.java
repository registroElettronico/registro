/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author dential
 */
public class Classe {
    
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Attivita> attivita = new ArrayList<>();
    private final ArrayList<Lezione> lezioni = new ArrayList<>();
    
    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }
    
    public void addInsegnante(Insegnante i) {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
    }
    
    public void addStudente(Studente i) {
        if (i == null) throw new NullPointerException("Studente non valido");
        studenti.add(i);
    }
    
    public void addAttivita (Attivita i) {
        if (i == null) throw new NullPointerException("Attivita non valido");
        attivita.add(i);
    }
    
    public void addLezione (Lezione i) {
        if (i == null) throw new NullPointerException("Lezione non valida");
        lezioni.add(i);
    }
    
}
