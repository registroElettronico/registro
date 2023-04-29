/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.tools.Data;

/**
 *
 * @author dential
 */
public class Attivita {
    
    private String materia;
    private Insegnante insegnante;
    private Data data;
    private String argomento;
    private int oraInizio;
    private int oraFine;
    private String tipo;

    public Attivita(String materia, Insegnante insegnante, Data data, String argomento, int oraInizio, int oraFine, String tipo) {
        this.materia = materia;
        this.insegnante = insegnante;
        this.data = data;
        this.argomento = argomento;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.tipo = tipo;
    }

        
    
    public String getMateria() {
        return materia;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public Data getData() {
        return data;
    }

    public String getArgomento() {
        return argomento;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public String getTipo() {
        return tipo;
    }
    
}
