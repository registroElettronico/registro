/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.tools.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 *
 * @author studente
 */
public class Studente extends Persona{
    Classe classe;
    public final ArrayList<Voto> voti = new ArrayList<>();
    public final ArrayList<Boolean> assenze = new ArrayList<>();
    public final ArrayList<Nota> note = new ArrayList<>();
    public Pagella pagella;

    public Studente(String email, String password, String nome, String cognome, Data dataDiNascita, char genere, Classe classe) throws IOException {
        super(email, password, nome, cognome, dataDiNascita, genere);
        this.classe = classe;
    }

    public Classe getClasse() {
        return classe;
    }
}