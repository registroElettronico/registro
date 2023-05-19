package models;

import models.tools.Data;

import java.io.*;
import java.util.ArrayList;

public class Studente extends Persona{
    Classe classe;
    private final ArrayList<Voto> voti = new ArrayList<>();
    private final ArrayList<Rapporto> rapporti = new ArrayList<>();

    private int numeroAssenze = 0;
    //public Pagella pagella;                                                       NON FATTO

    public Studente(String email, String password, String nome, String cognome, Data dataDiNascita, char genere, Classe classe) throws IOException {
        super(email, password, nome, cognome, dataDiNascita, genere);
        this.classe = classe;
    }

    public Classe getClasse() {
        return classe;
    }

    public void addVoto(Voto val) {
        if (val == null) throw new NullPointerException("VOTO non valido");
        this.voti.add(val);
    }

    public void addAssenza(){
        this.numeroAssenze++;
    }

    public Float percAssenze() {
        return ((float)(this.numeroAssenze/240)*100);
    }

    public void addRapporto(Rapporto n){
        if (n == null) throw new NullPointerException("NOTA non valida");
        this.rapporti.add(n);
    }

    public void setAssenze(int assenze) {
        this.numeroAssenze = assenze;
    }

    public ArrayList<Voto> getVoti() {
        return voti;
    }

    public ArrayList<Rapporto> getRapporti() {
        return rapporti;
    }
}