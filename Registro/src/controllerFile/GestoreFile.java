package controllerFile;

import models.*;
import models.tools.Data;

import java.io.*;
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

    public static void addPersona(Persona p) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("users.csv"));

        //si salvano gli utenti già presenti nel file
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += (lineContent + "\n");
        }

        br.close();

        if (p instanceof Studente) {
            String tipologia = "Studente";
            content += p.getEmail() + "," + p.getPassword() + "," + tipologia + "," + p.getNome() + "," + p.getCognome() + "," + p.getDataDiNascita() + "," + p.getGenere() + "," + ((Studente) p).getClasse();

            //aggiungo l'utente al file delle classi
            BufferedReader readerClassi = new BufferedReader(new FileReader("classi.csv"));
            String lineContentClassi;
            String contentClassi = "";

            while ((lineContentClassi = readerClassi.readLine()) != null) {
                contentClassi += lineContentClassi;

                if (lineContentClassi.split(",")[0].equals(((Studente) p).getClasse().getSezione())) contentClassi += ("," + p.getEmail());

                contentClassi += "\n";
            }
            readerClassi.close();

            BufferedWriter writerClassi = new BufferedWriter(new FileWriter("classi.csv"));
            writerClassi.write(contentClassi);
            writerClassi.close();
        }
        else if (p instanceof Insegnante) {
            String tipologia = "Insegnante";
            content += p.getEmail() + "," + p.getPassword() + "," + tipologia + "," + p.getNome() + "," + p.getCognome() + "," + p.getDataDiNascita() + "," + p.getGenere();
        }

        //aggiunge l'utente al file degli utenti
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));    //si riscrive il file con anche il nuovo utente
        bw.write(content);

        bw.close();
    }

    public static void addVoto(Voto val) throws IOException {
        //scrive il voto sul file dei voti
        BufferedReader br = new BufferedReader(new FileReader("student/votes.csv"));

        boolean giaPresente = false;
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += lineContent;

            if (lineContent.split(",")[0].equals(val.getStudente().getEmail())) {
                giaPresente = true;
                content += ("," + val.getVoto()+"|"+val.getMateria()+"|"+val.getData());
            }

            content += "\n";
        }
        br.close();
        if (!giaPresente) content += (val.getStudente().getEmail()+","+val.getVoto()+"|"+val.getMateria()+"|"+val.getData());

        BufferedWriter bw = new BufferedWriter(new FileWriter("student/votes.csv"));
        bw.write(content);
        bw.close();
    }

    public static void addClasse(Classe c) {
        classi.add(c);

        //                                                          SCRIVI SU FILE QUA
    }

    public static void addTeacherToClass(Classe classe, Insegnante i) {
    }

    public static void addStudentToClass(Classe classe, Studente s) {
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

    public static Classe getClasse(String sezione) {
        for (Classe c: classi){
            if (c.getSezione().equals(sezione)) return c;
        }
        throw new NullPointerException("LA CLASSE NON ESISTE");
    }
}
