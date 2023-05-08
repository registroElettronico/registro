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
import models.tools.Data;

public class GestoreRegistro {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Dirigente> dirigenti = new ArrayList<>();
    private final ArrayList<Classe> classi = new ArrayList<>();

    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public ArrayList<Dirigente> getDirigenti() {
        return dirigenti;
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    public Persona getUser() {
        return user;
    }

    public GestoreRegistro() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("classi.csv"));
        br.readLine();
        String line;

        while((line = br.readLine()) != null) {
            String[] info = line.split(",");
            this.classi.add(new Classe(info[0]));
        }

        br = new BufferedReader(new FileReader("users.csv"));
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente": {
                    Studente s = new Studente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0), info[info.length-1]);
                    this.studenti.add(s);
                    s.getClasse().addStudente(s);

                    break;
                }
                case "Insegnante": {
                    Insegnante ins = new Insegnante(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0));
                    this.insegnanti.add(ins);  //aggiunge l'insegnante alla lista

                    //legge le classi per capire a quali classi fa parte il prof
                    br = new BufferedReader(new FileReader("classi.csv"));
                    br.readLine();

                    while((line = br.readLine()) != null) {
                        String[] infoClassi = line.split(",");

                        int i = 1;
                        while (!infoClassi[i].equals("|")) {    //partendo dalla seconda stringa, scorre fino a "|"
                            if (infoClassi[i].equals(info[3])) {    //se trova una email uguale alla propria ha trovato la classe giusta
                                for (Classe c: this.classi){           //scorre tutte le classi nella lista per accedere all'oggetto classe
                                    if (c.getSezione().equals(infoClassi[0])) { //se trova la sezione corrispondente
                                        c.addInsegnante(ins); //aggiune l'insegnante alla classe
                                        ins.addClasse(c); //aggiunge la classe all'insegnante
                                    }
                                }
                            }
                            i++;
                        }
                    }

                    break;
                }

                case "Dirigente": {
                    this.dirigenti.add(new Dirigente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0)));
                    break;
                }
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
