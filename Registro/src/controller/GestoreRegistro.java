package controller;

import java.io.*;
import java.util.ArrayList;

import controllerFile.GestoreFile;
import models.*;
import models.tools.Data;

import javax.management.InstanceNotFoundException;

public class GestoreRegistro {
    private ArrayList<Insegnante> insegnanti;
    private ArrayList<Studente> studenti;
    private ArrayList<Classe> classi;
    private Persona user;

    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    public Persona getUser() {
        return user;
    }

    public Classe getClasse(String sezione){
        for (Classe c: this.classi) if (c.getSezione().equals(sezione)) return c;
        return null;
    }

    public GestoreRegistro() throws IOException, InstanceNotFoundException {
        GestoreFile.load();
        this.classi = GestoreFile.getClassi();
        this.studenti = GestoreFile.getStudenti();
        this.insegnanti = GestoreFile.getInsegnanti();
    }

    private void addInsegnante(Insegnante i) {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
    }

    private void addStudente(Studente i) {
        if (i == null) throw new NullPointerException("Studente non valido");
        studenti.add(i);
    }

    public boolean login (String email, String password) {
        boolean okLogin = false;
        for (Studente s: this.studenti) {
            if (s.getEmail().equals(email) && s.getPassword().equals(password)) {
                okLogin = true;
                this.user = s;
            }
        }
        if (this.user == null) {
            for (Insegnante i: this.insegnanti) {
                if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                    okLogin = true;
                    this.user = i;
                }
            }
        }

        return okLogin;
    }

    public boolean register(String email, String password, String tipologia, String nome, String cognome, String date, char genere, String classe) throws IOException {
        boolean okSignup = false;

        switch (tipologia) {
            case "Studente" -> {
                Studente s = new Studente(email, password, nome, cognome, new Data(date), genere, this.getClasse(classe));
                this.addStudente(s);    //aggiunge lo studente alla lista
                s.getClasse().addStudente(s);   //aggiunge la classe allo studente
                GestoreFile.addPersona(s);
                okSignup = true;
            }
            case "Insegnante" -> {
                Insegnante i = new Insegnante(email, password, nome, cognome, new Data(date), genere); //aggiunge l'insegnante alla lista
                this.addInsegnante(i);
                GestoreFile.addPersona(i);
                okSignup = true;
            }
        }

        return okSignup;
    }
}
