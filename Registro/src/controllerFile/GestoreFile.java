package controllerFile;

import models.Classe;
import models.Insegnante;
import models.Studente;
import models.tools.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreFile {
    private static final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private static final ArrayList<Studente> studenti = new ArrayList<>();
    private static final ArrayList<Classe> classi = new ArrayList<>();
    public static void load() throws IOException {
        BufferedReader bufferedReaderUsers = new BufferedReader(new FileReader("users.csv"));
        bufferedReaderUsers.readLine();

        String line;

        //inserisce gli utenti nelle rispettive liste
        while ((line = bufferedReaderUsers.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente" -> {
                    Classe c;
                    try {
                        c = getClasse(info[7]);
                    }catch (NullPointerException ex){
                        c = new Classe(info[7]);
                    }

                    Studente s = new Studente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0), c);
                    studenti.add(s);    //aggiunge lo studente alla lista
                }
                case "Insegnante" -> {
                    Insegnante i = new Insegnante(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0));
                    insegnanti.add(i);  //aggiunge l'insegnante alla lista
                }
            }
        }

        bufferedReaderUsers.close();

        //inserisce le informazioni delle classi negli utenti e nella lista di classi
        BufferedReader bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
        bufferedReaderClasse.readLine();

        while((line = bufferedReaderClasse.readLine()) != null) {
            String[] info = line.split(",");
            Classe classe = new Classe(info[0]);

            int i = 1;
            while (!info[i].equals("|")) {
                Insegnante insegnante;
                try {
                    insegnante = getInsegnante(info[i]);
                    insegnante.addClasse(classe);
                    classe.addInsegnante(insegnante);
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
                i++;
            }

            i++;
            while (i < info.length) {
                try {
                    Studente studente = getStudente(info[i]);
                    classe.addStudente(studente);
                }catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
                i++;
            }
        }

        bufferedReaderClasse.close();
    }

    public static ArrayList<Insegnante> getInsegnanti() {
        return insegnanti;
    }

    public static ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public static ArrayList<Classe> getClassi() {
        return classi;
    }

    private static Insegnante getInsegnante(String email) {
        for (Insegnante i: insegnanti){
            if (i.getEmail().equals(email)) return i;
        }
        throw new NullPointerException("L'INSEGNANTE NON ESISTE");
    }

    private static Studente getStudente(String email) {
        for (Studente s: studenti){
            if (s.getEmail().equals(email)) return s;
        }
        throw new NullPointerException("LO STUDENTE NON ESISTE");
    }

    private static Classe getClasse(String sezione) {
        for (Classe c: classi){
            if (c.getSezione().equals(sezione)) return c;
        }
        throw new NullPointerException("LA CLASSE NON ESISTE");
    }
}
