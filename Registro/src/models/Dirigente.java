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

    public Dirigente(String email, String password, String nome, String cognome, Data dataDiNascita, char genere) {
        super(email, password, nome, cognome, dataDiNascita, genere);
    }

    private void addPagella(Studente s, Pagella p) {
        s.pagella = p;
    }
}
