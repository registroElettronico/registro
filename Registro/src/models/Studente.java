/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.tools.Data;

import java.util.ArrayList;

/**
 *
 * @author studente
 */
public class Studente extends Persona{
    public final ArrayList<Voto> voti = new ArrayList<>();
    public final ArrayList<Boolean> assenze = new ArrayList<>();
    public final ArrayList<Nota> note = new ArrayList<>();

    public Pagella pagella;

    public Studente(String nome, String cognome, char genere, Data dataDiNascita, String email, String password) {
        super(nome, cognome, genere, dataDiNascita, email, password);
    }
}
