package controllerFile;

import models.*;
import models.tools.Data;

import javax.management.InstanceNotFoundException;
import java.io.*;
import java.util.ArrayList;

public class GestoreFile {
    private static final ArrayList<Insegnante> insegnanti = new ArrayList<>();
    private static final ArrayList<Studente> studenti = new ArrayList<>();
    private static final ArrayList<Classe> classi = new ArrayList<>();
    public static void load() throws IOException, InstanceNotFoundException {

        BufferedReader bufferedReaderUsers = new BufferedReader(new FileReader("users.csv"));
        bufferedReaderUsers.readLine();

        String line;

        //inserisce gli utenti nelle rispettive liste
        while ((line = bufferedReaderUsers.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente" -> {
                    Classe c = getClasse(info[7]);

                    if (c == null){
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
            Classe classe = getClasse(info[0]);
            if (classe == null) {
                classe = new Classe(info[0]);
                classi.add(classe);
            }

            int i = 1;

            while (!info[i].equals("|")) {

                Insegnante insegnante = getInsegnante(info[i]);
                if (insegnante == null) throw new InstanceNotFoundException("INSEGNANTE NON PRESENTE IN 'users.csv'");
                insegnante.addClasse(classe);
                classe.addInsegnante(insegnante);

                i++;
            }

            i++;
            while (i < info.length) {

                Studente studente = getStudente(info[i]);
                if (studente == null) throw new InstanceNotFoundException("STUDENTE NON PRESENTE IN 'users.csv'");
                classe.addStudente(studente);
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
            content += p.getEmail() + "," + p.getPassword() + "," + tipologia + "," + p.getNome() + "," + p.getCognome() + "," + p.getDataDiNascita() + "," + p.getGenere() + "," + ((Studente) p).getClasse().getSezione();

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

    public static Classe addClasse(Classe c) throws IOException{
        Classe classe = getClasse(c.getSezione());
        if (classe == null) {   //controlla se la classe non è già presente
            //se non è presente la aggiunge al file e alla lista
            classe = c;
            classi.add(classe);

            //legge il file delle classi
            BufferedReader br = new BufferedReader(new FileReader("classi.csv"));
            String lineContent;
            String content = "";

            while((lineContent = br.readLine()) != null) {
                content += lineContent + "\n";
            }
            br.close();

            content += (c.getSezione() + ",|\n");

            //scrive sul file delle classi
            BufferedWriter bw = new BufferedWriter(new FileWriter("classi.csv"));
            bw.write(content);
            bw.close();
        }

        return classe;  //restituisce la classe aggiunta
    }

    public static void addTeacherToClass(Classe classe, Insegnante i) throws IOException {
        //legge il file delle classi

        BufferedReader br = new BufferedReader(new FileReader("classi.csv"));
        String lineContent;
        String content = "";

        while((lineContent = br.readLine()) != null) {

            if (lineContent.split(",")[0].equals(classe.getSezione())){
                boolean exists = lineContent.contains(i.getEmail());
                if (!exists) lineContent = lineContent.substring(0, 3) + i.getEmail() + "," + lineContent.substring(3);
            }

            content += lineContent + "\n";
        }
        br.close();

        //scrive sul file delle classi
        BufferedWriter bw = new BufferedWriter(new FileWriter("classi.csv"));
        bw.write(content);
        bw.close();
    }

    public static void addRapporto(Rapporto rapporto) throws IOException {
        //scrive il voto sul file dei voti
        BufferedReader br = new BufferedReader(new FileReader("student/notes.csv"));

        boolean giaPresente = false;
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += lineContent;

            if (lineContent.split(",")[0].equals(rapporto.getStudente().getEmail())) {
                giaPresente = true;
                content += ("," + rapporto.getInsegnante().getEmail() + "|" + rapporto.getMotivo() + "|" + rapporto.getData());
            }

            content += "\n";
        }
        br.close();
        if (!giaPresente) content += (rapporto.getStudente().getEmail()+","+rapporto.getInsegnante().getEmail()+"|"+rapporto.getMotivo()+"|"+rapporto.getData());

        BufferedWriter bw = new BufferedWriter(new FileWriter("student/notes.csv"));
        bw.write(content);
        bw.close();
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
        return null;
    }

    private static Studente getStudente(String email) {
        for (Studente s: studenti){
            if (s.getEmail().equals(email)) return s;
        }
        return null;
    }

    public static Classe getClasse(String sezione) {
        for (Classe c: classi){
            if (c.getSezione().equals(sezione)) return c;
        }
        return null;
    }


}
