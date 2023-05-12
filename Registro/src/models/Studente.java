package models;

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

        //scrive il voto sul file dei voti
        BufferedReader br = new BufferedReader(new FileReader("student/votes.csv"));

        boolean giaPresente = false;
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += lineContent;

            if (lineContent.split(",")[0].equals(this.getEmail())) {
                giaPresente = true;
                content += ("," + val.getVoto()+"|"+val.getMateria()+"|"+val.getData());
            }

            content += "\n";
        }
        br.close();
        if (!giaPresente) content += (this.getEmail()+","+val.getVoto()+"|"+val.getMateria()+"|"+val.getData());

        BufferedWriter bw = new BufferedWriter(new FileWriter("student/votes.csv"));
        bw.write(content);
        bw.close();
    }

    public void addAssenza(){
        this.assenze.add(false);
    }

    public void addRapporto(Nota n){
        if (n == null) throw new NullPointerException("NOTA non valida");
        this.note.add(n);
    }
}