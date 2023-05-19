/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.removed;

import models.Insegnante;
import models.tools.Data;

/**
 *
 * @author dential
 */
public class Lezione {
    private final String materia;
    private final Insegnante insegnante;
    private final Data data;
    private final String argomento;
    private final int ora;

    public Lezione(String materia, Insegnante insegnante, Data data, String argomento, int ora) {
        this.materia = materia;
        this.insegnante = insegnante;
        this.data = data;
        this.argomento = argomento;
        this.ora = ora;
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

    public int getOra() {
        return ora;
    }
    
    
}
