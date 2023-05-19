package models.removed;

import models.Classe;
import models.Persona;

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
