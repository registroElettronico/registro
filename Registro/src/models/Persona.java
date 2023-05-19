package models;

import models.tools.Data;


public abstract class Persona implements Comparable{
    private final String nome;
    private final String cognome;
    private final char genere;
    private final Data dataDiNascita;
    private final String email;
    private final String password;

    public Persona(String email, String password, String nome, String cognome, Data dataDiNascita, char genere) {
        this.nome = nome;
        this.cognome = cognome;
        this.genere = genere;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public char getGenere() {
        return genere;
    }

    public Data getDataDiNascita() {
        return dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int compareTo(Object other) {
        Persona persona = (Persona) other;
        return (this.cognome + this.nome).compareTo((persona.getCognome() + persona.getNome()));
    }
}
