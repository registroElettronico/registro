/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author studente
 */
public class Comunicazione {
    private final Classe target;
    private final Persona mittente;
    private final String contenuto;

    public Comunicazione(Classe target, Persona mittente, String contenuto) {
        this.target = target;
        this.mittente = mittente;
        this.contenuto = contenuto;
    }

    
    
    public Classe getTarget() {
        return target;
    }

    public Persona getMittente() {
        return mittente;
    }

    public String getContenuto() {
        return contenuto;
    }
    
    
}
