package models;

import models.tools.*;

public class Persona {
    private final String nome;
    private final String cognome;
    private final char genere;
    private Data dataDiNascita;
    private String email;
    private String password;
    private String indirizzo;
    private String foto;

    public Persona(String nome, String cognome, char genere, Data dataDiNascita, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.genere = genere;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.password = password;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getFoto() {
        return foto;
    }
    
    
}
