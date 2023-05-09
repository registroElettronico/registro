package controller;

import java.io.*;
import java.util.ArrayList;

import models.*;
import models.tools.Data;

public class GestoreRegistro {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private final ArrayList<Dirigente> dirigenti = new ArrayList<>();
    private final ArrayList<Classe> classi = new ArrayList<>();
    public Persona user;

    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public ArrayList<Dirigente> getDirigenti() {
        return dirigenti;
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    public Persona getUser() {
        return user;
    }

    public Classe getClasse(String sezione){
        for (Classe c: this.classi) if (c.getSezione().equals(sezione)) return c;
        return null;
    }

    public GestoreRegistro() throws IOException {
        BufferedReader bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
        bufferedReaderClasse.readLine();

        BufferedReader bufferedReaderUtente = new BufferedReader(new FileReader("users.csv"));
        bufferedReaderUtente.readLine();

        String line;

        while((line = bufferedReaderClasse.readLine()) != null) {
            String[] info = line.split(",");
            this.classi.add(new Classe(info[0]));
        }
        bufferedReaderClasse.close();

        while ((line = bufferedReaderUtente.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente": {
                    Studente s = new Studente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0), this.getClasse(info[info.length-1]));
                    this.studenti.add(s);
                    s.getClasse().addStudente(s);
                    break;
                }
                case "Insegnante": {
                    bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
                    Insegnante ins = new Insegnante(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0));
                    this.insegnanti.add(ins);  //aggiunge l'insegnante alla lista

                    //legge le classi per capire a quali classi fa parte il prof
                    while((line = bufferedReaderClasse.readLine()) != null) {
                        String[] infoClassi = line.split(",");
                        int i = 1;
                        while (!infoClassi[i].equals("|")) {    //partendo dalla seconda stringa, scorre fino a "|"

                            if (infoClassi[i].equals(ins.getEmail())) {    //se trova una email uguale alla propria ha trovato la classe giusta

                                Classe c = this.getClasse(infoClassi[0]);   //scorre tutte le classi nella lista per accedere all'oggetto classe
                                if (c!=null) {
                                    c.addInsegnante(ins); //aggiune l'insegnante alla classe
                                    ins.addClasse(c); //aggiunge la classe all'insegnante
                                }
                            }
                            i++;
                        }

                    }
                    break;
                }

                case "Dirigente": {
                    this.dirigenti.add(new Dirigente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0)));
                    break;
                }
            }
        }

        bufferedReaderUtente.close();
        bufferedReaderClasse.close();
    }

    private void addInsegnante(Insegnante i) {
        if (i == null) throw new NullPointerException("Insegnante non valido");
        insegnanti.add(i);
    }

    private void addStudente(Studente i) {
        if (i == null) throw new NullPointerException("Studente non valido");
        studenti.add(i);
    }
    
    private void addDirigente(Dirigente i) {
        if (i == null) throw new NullPointerException("Dirigente non valido");
        dirigenti.add(i);
    }

    public boolean login (String email, String password) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("users.csv"));
        String line;
        br.readLine();

        boolean okLogin = false;
        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            if (info[0].equals(email) && info[1].equals(password)) {
                okLogin = true;
                switch (info[2]) {
                    case "Studente": {
                        for (Studente s: this.getStudenti()) {
                            if (s.getEmail().equals(info[0])) {
                                this.user = s;
                                break;
                            }
                        }
                        break;
                    }
                    case "Insegnante": {
                        for (Insegnante i: this.getInsegnanti()) {
                            if (i.getEmail().equals(info[0])) {
                                this.user = i;
                                break;
                            }
                        }
                        break;
                    }
                    case "Dirigente": {
                        for (Dirigente d: this.getDirigenti()) {
                            if (d.getEmail().equals(info[0])) {
                                this.user = d;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }

        br.close();
        return okLogin;
    }

    public void register(String email, String password, String tipologia, String nome, String cognome, String date, char genere, String classe) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));
        bw.append(email + "," + password + "," + tipologia + "," + nome + "," + cognome + "," + date + "," + genere);
        bw.flush();

        switch (tipologia) {
            case "Studente": {
                Studente s = new Studente(email, password, nome, cognome, new Data(date), genere, this.getClasse(classe));
                this.addStudente(s);    //aggiunge lo studente alla lista
                s.getClasse().addStudente(s);   //aggiunge lo studente alla classe alla lista

                break;
            }
            case "Insegnante": {
                this.addInsegnante(new Insegnante(email, password, nome, cognome, new Data(date), genere)); //aggiunge l'insegnante alla lista
                break;
            }
            case "Dirigente": {
                this.addDirigente(new Dirigente(email, password, nome, cognome, new Data(date), genere)); //aggiunge il dirigente alla lista
                break;
            }
        }

        bw.close();
    }
}
