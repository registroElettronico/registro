package controller;

import java.io.*;
import java.util.ArrayList;

import controllerFile.GestoreFile;
import models.*;
import models.tools.Data;

public class GestoreRegistro {
    private final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private final ArrayList<Studente> studenti = new ArrayList<>();
    private ArrayList<Classe> classi = new ArrayList<>();
    private Persona user;

    public ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
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
        BufferedReader bufferedReaderUtente = new BufferedReader(new FileReader("users.csv"));
        bufferedReaderUtente.readLine();

        this.classi = GestoreFile.loadClassi();

        String line;
        while ((line = bufferedReaderUtente.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente" -> {
                    Studente s = new Studente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0), this.getClasse(info[info.length - 1]));
                    this.studenti.add(s);
                    s.getClasse().addStudente(s);
                }
                case "Insegnante" -> {
                    bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
                    Insegnante ins = new Insegnante(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0));
                    this.insegnanti.add(ins);  //aggiunge l'insegnante alla lista

                    //legge le classi per capire a quali classi fa parte il prof
                    while ((line = bufferedReaderClasse.readLine()) != null) {
                        String[] infoClassi = line.split(",");
                        int i = 1;
                        while (!infoClassi[i].equals("|")) {    //partendo dalla seconda stringa, scorre fino a "|"

                            if (infoClassi[i].equals(ins.getEmail())) {    //se trova una email uguale alla propria ha trovato la classe giusta

                                Classe c = this.getClasse(infoClassi[0]);   //scorre tutte le classi nella lista per accedere all'oggetto classe
                                if (c != null) {
                                    c.addInsegnante(ins); //aggiune l'insegnante alla classe
                                    ins.addClasse(c); //aggiunge la classe all'insegnante
                                }
                            }
                            i++;
                        }

                    }
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
                    case "Studente" -> {
                        for (Studente s : this.getStudenti()) {
                            if (s.getEmail().equals(info[0])) {
                                this.user = s;
                                break;
                            }
                        }
                    }
                    case "Insegnante" -> {
                        for (Insegnante i : this.getInsegnanti()) {
                            if (i.getEmail().equals(info[0])) {
                                this.user = i;
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }

        br.close();
        return okLogin;
    }

    public boolean register(String email, String password, String tipologia, String nome, String cognome, String date, char genere, String classe) throws IOException {
        boolean okSignup = false;

        BufferedReader br = new BufferedReader(new FileReader("users.csv"));

        //si salvano gli utenti già presenti nel file
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += (lineContent + "\n");
        }

        br.close();

        switch (tipologia) {
            case "Studente" -> {
                content += email + "," + password + "," + tipologia + "," + nome + "," + cognome + "," + date + "," + genere + "," + classe;

                Studente s = new Studente(email, password, nome, cognome, new Data(date), genere, this.getClasse(classe));
                this.addStudente(s);    //aggiunge lo studente alla lista
                s.getClasse().addStudente(s);   //aggiunge la classe allo studente


                //aggiungo l'utente al file delle classi
                BufferedReader readerClassi = new BufferedReader(new FileReader("classi.csv"));
                String lineContentClassi;
                String contentClassi = "";

                while ((lineContentClassi = readerClassi.readLine()) != null) {
                    contentClassi += lineContentClassi;

                    if (lineContentClassi.split(",")[0].equals(s.getClasse().getSezione())) contentClassi += ("," + s.getEmail());

                   contentClassi += "\n";
                }
                readerClassi.close();

                BufferedWriter writerClassi = new BufferedWriter(new FileWriter("classi.csv"));
                writerClassi.write(contentClassi);
                writerClassi.close();

                okSignup = true;
            }
            case "Insegnante" -> {
                content += email + "," + password + "," + tipologia + "," + nome + "," + cognome + "," + date + "," + genere;

                this.addInsegnante(new Insegnante(email, password, nome, cognome, new Data(date), genere)); //aggiunge l'insegnante alla lista

                okSignup = true;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));    //si riscrive il file con anche il nuovo utente
        bw.write(content);

        bw.close();

        return okSignup;
    }
}
