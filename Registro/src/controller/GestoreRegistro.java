/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import models.*;

public class GestoreRegistro {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Dirigente> dirigenti = new ArrayList<>();
    private final ArrayList<Classe> classi = new ArrayList<>();

    public GestoreRegistro() throws IOException {

        //scarica le classi
        BufferedReader br = new BufferedReader(new FileReader("classi.csv"));
        br.readLine();
        String line;

        while((line = br.readLine()) != null) {
            String[] info = line.split(",");
            this.classi.add(new Classe(info[0]));

            int j = 1;
            while (!info[j].equals("|")) {
                for (Insegnante i: this.insegnanti) {
                    if (info[j].equals(i.getEmail())) this.classi.get(this.classi.size()-1).addInsegnante(i);
                    break;
                }

                j++;
            }

            j++;
            while (info[j] != null) {
                for (Studente s: this.studenti) {
                    if (info[j].equals(s.getEmail())) this.classi.get(this.classi.size()-1).addStudente(s);
                    break;
                }
                j++;
            }
        }
    }

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
