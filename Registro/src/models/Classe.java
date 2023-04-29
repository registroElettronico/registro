
package models;

import java.util.ArrayList;
public class Classe {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Attivita> attivita = new ArrayList<>();
    private final ArrayList<Lezione> lezioni = new ArrayList<>();
    private final ArrayList<Comunicazione> comunicazioni = new ArrayList<>();
    
    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }
    
    public void addInsegnante(Insegnante i) {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
    }
    
    public void addStudente(Studente i) {
        if (i == null) throw new NullPointerException("Studente non valido");
        studenti.add(i);
    }
    
    public void addAttivita (Attivita i) {
        if (i == null) throw new NullPointerException("Attivita non valido");
        attivita.add(i);
    }
    
    public void addLezione (Lezione i) {
        if (i == null) throw new NullPointerException("Lezione non valida");
        lezioni.add(i);
    }

    void addComunicazione(Comunicazione com) {
        if (com == null) throw new NullPointerException("COMUNICAZIONE non valida");
        comunicazioni.add(com);
    }
}
