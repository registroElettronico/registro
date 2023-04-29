package models;

public class Pagella {
    private final Studente studente;
    private final String filePagella;

    public Pagella(Studente studente, String filename) {
        this.studente = studente;
        this.filePagella = filename;
    }

    public Studente getStudente() {
        return studente;
    }

    public String getFilePagella() {
        return filePagella;
    }
}
