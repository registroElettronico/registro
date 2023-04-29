/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.tools.Data;

/**
 *
 * @author studente
 */
public class Dirigente extends Persona{

    public Dirigente(String nome, String cognome, char genere, Data dataDiNascita, String email, String password) {
        super(nome, cognome, genere, dataDiNascita, email, password);
    }

    private void addPagella(Studente s, Pagella p) {
        s.pagella = p;
    }
}
