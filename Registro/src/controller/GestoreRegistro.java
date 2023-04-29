/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import models.*;

public class GestoreRegistro {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Dirigente> dirigenti = new ArrayList<>();

    public Persona user;
    
    public void addInsegnante(Insegnante i) {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
    }
    
    public void addStudente(Studente i) {
        if (i == null) throw new NullPointerException("Studente non valido");
        studenti.add(i);
    }
    
    public void addDirigente(Dirigente i) {
        if (i == null) throw new NullPointerException("Dirigente non valido");
        dirigenti.add(i);
    }

    public void login (Persona p) {
        if (p == null) throw new NullPointerException("LOGIN non valido");
        user = p;
    }
}
