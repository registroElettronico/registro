package models;

import controllerFile.GestoreFile;
import models.tools.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author studente
 */
public class Studente extends Persona{
    Classe classe;
    private final ArrayList<Voto> voti = new ArrayList<>();
    private final ArrayList<Boolean> assenze = new ArrayList<>();
    private final ArrayList<Nota> note = new ArrayList<>();
    public Pagella pagella;

    public Studente(String email, String password, String nome, String cognome, Data dataDiNascita, char genere, Classe classe) throws IOException {
        super(email, password, nome, cognome, dataDiNascita, genere);
        this.classe = classe;
    }

    public Classe getClasse() {
        return classe;
    }

    public void addVoto(Voto val) throws IOException {
        if (val == null) throw new NullPointerException("VOTO non valido");
        this.voti.add(val);
    }

    public void addAssenza(){
        this.assenze.add(false);
    }

    public void addRapporto(Nota n){
        if (n == null) throw new NullPointerException("NOTA non valida");
        this.note.add(n);
    }
}