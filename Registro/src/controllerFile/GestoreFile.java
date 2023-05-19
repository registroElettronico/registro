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
    public static void load() throws IOException, InstanceNotFoundException {          //carica tutte le informazioni dei file nei vettori
        loadUtenti();
        loadClassi();
        loadAssenze();
        loadVoti();
        loadRapporti();
        loadAttivita();
    }

    private static void loadUtenti() throws IOException {
        BufferedReader bufferedReaderUsers = new BufferedReader(new FileReader("users.csv"));
        bufferedReaderUsers.readLine();
        String line;

        //inserisce gli utenti nelle rispettive liste
        while ((line = bufferedReaderUsers.readLine()) != null) {
            String[] info = line.split(",");

            switch (info[2]) {
                case "Studente": {
                    Classe c = getClasse(info[7]);

                    if (c == null){
                        c = new Classe(info[7]);
                    }

                    Studente s = new Studente(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0), c);
                    studenti.add(s);    //aggiunge lo studente alla lista
                    break;
                }
                case "Insegnante": {
                    Insegnante i = new Insegnante(info[0], info[1], info[3], info[4], new Data(info[5]), info[6].charAt(0));
                    insegnanti.add(i);  //aggiunge l'insegnante alla lista
                    break;
                }
            }
        }

        bufferedReaderUsers.close();
    }

    private static void loadClassi() throws IOException, InstanceNotFoundException {
        //inserisce le informazioni delle classi negli utenti e nella lista di classi
        BufferedReader bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
        bufferedReaderClasse.readLine();
        String line;

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

    private static void loadVoti() throws InstanceNotFoundException, IOException {
        //inserisce i voti negli studenti
        BufferedReader bufferedReaderVoti = new BufferedReader(new FileReader("student/votes.csv"));
        bufferedReaderVoti.readLine();
        String line;

        while ((line = bufferedReaderVoti.readLine()) != null) {
            String[] info = line.split(",");

            Studente studente = getStudente(info[0]);

            if (studente == null) throw new InstanceNotFoundException("STUDENTE NON PRESENTE IN 'users.csv'");

            for (int i = 1; i < info.length; i++) {
                String[] infoVoto = info[i].split("\\|");   //splitta sulla barra verticale

                studente.addVoto(new Voto(Float.parseFloat(infoVoto[0]), infoVoto[1], new Data(infoVoto[2]), studente));
            }
        }

        bufferedReaderVoti.close();
    }

    private static void loadAssenze() throws IOException, InstanceNotFoundException {
        //inserisce le assenze negli studenti
        BufferedReader bufferedReaderAssenze = new BufferedReader(new FileReader("student/absence.csv"));
        String line;
        bufferedReaderAssenze.readLine();

        while ((line = bufferedReaderAssenze.readLine()) != null) {
            String[] info = line.split(",");

            Studente studente = getStudente(info[0]);

            if (studente == null) throw new InstanceNotFoundException("STUDENTE NON PRESENTE IN 'users.csv'");

            studente.setAssenze(Integer.parseInt(info[1]));
        }

        bufferedReaderAssenze.close();
    }

    private static void loadRapporti() throws IOException, InstanceNotFoundException {
        //inserisce i rapporti negli studenti
        BufferedReader bufferedReaderRapporti = new BufferedReader(new FileReader("student/notes.csv"));
        String line;
        bufferedReaderRapporti.readLine();



        while ((line = bufferedReaderRapporti.readLine()) != null) {
            String[] info = line.split(",");

            Studente studente = getStudente(info[0]);

            if (studente == null) throw new InstanceNotFoundException("STUDENTE NON PRESENTE IN 'users.csv'");

            for (int i = 1; i < info.length; i++) {
                String[] infoRapporto = info[i].split("\\|");   //splitta sulla barra verticale

                studente.addRapporto(new Rapporto(getInsegnante(infoRapporto[0]), studente, infoRapporto[1], new Data(infoRapporto[2])));
            }
        }

        bufferedReaderRapporti.close();
    }

    private static void loadAttivita() {

    }
    public static void addPersona(Persona p) throws IOException {
        String content;

        if (p instanceof Studente) {
            String tipologia = "Studente";
            content = p.getEmail() + "," + p.getPassword() + "," + tipologia + "," + p.getNome() + "," + p.getCognome() + "," + p.getDataDiNascita() + "," + p.getGenere() + "," + ((Studente) p).getClasse().getSezione();
            addStudentToClass(((Studente) p).getClasse(), (Studente) p);
        }
        else {
            String tipologia = "Insegnante";
            content = p.getEmail() + "," + p.getPassword() + "," + tipologia + "," + p.getNome() + "," + p.getCognome() + "," + p.getDataDiNascita() + "," + p.getGenere();
        }

        //aggiunge l'utente al file degli utenti
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv", true));    //si riscrive il file con anche il nuovo utente
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

            //scrive sul file delle classi
            BufferedWriter bw = new BufferedWriter(new FileWriter("classi.csv", true));
            bw.write(c.getSezione() + ",|\n");
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

    public static void addStudentToClass(Classe classe, Studente s) throws IOException {

        //legge il file delle classi
        BufferedReader readerClassi = new BufferedReader(new FileReader("classi.csv"));
        String lineContent;
        String content = "";

        while ((lineContent = readerClassi.readLine()) != null) {
            content += lineContent;

            if (lineContent.split(",")[0].equals(s.getClasse().getSezione())) {
                content += ("," + s.getEmail());
            }

            content += "\n";
        }
        readerClassi.close();

        //scrive sul file delle classi
        BufferedWriter writerClassi = new BufferedWriter(new FileWriter("classi.csv"));
        writerClassi.write(content);
        writerClassi.close();
    }

    public static void addRapporto(Rapporto rapporto) throws IOException {//scrive il voto sul file dei voti
        //legge il file
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

        //scrive sul file
        BufferedWriter bw = new BufferedWriter(new FileWriter("student/notes.csv"));
        bw.write(content);
        bw.close();
    }

    public static void addAttivita(Attivita attivita) throws IOException {//scrive l'attività sul file delle attività
        //legge il file
        BufferedReader br = new BufferedReader(new FileReader("class/activities.csv"));

        boolean giaPresente = false;
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {
            content += lineContent;

            if (lineContent.split(",")[0].equals(attivita.getClasse().getSezione())) {
                giaPresente = true;
                content += ("," + attivita.getInsegnante().getEmail() + "|" + attivita.getData() + "|" + attivita.getContenuto() + "|" + attivita.getOraInizio() + "|" + attivita.getOraFine() + "|" + attivita.getTipo());
            }

            content += "\n";
        }
        br.close();
        if (!giaPresente) content += (attivita.getClasse().getSezione() + "," + attivita.getInsegnante().getEmail() + "|" + attivita.getData() + "|" + attivita.getContenuto() + "|" + attivita.getOraInizio() + "|" + attivita.getOraFine() + "|" + attivita.getTipo());

        //scrive sul file
        BufferedWriter bw = new BufferedWriter(new FileWriter("class/activities.csv"));
        bw.write(content);
        bw.close();
    }

    public static void addAssenza(Studente studente) throws IOException {
        //legge il file
        BufferedReader br = new BufferedReader(new FileReader("student/absence.csv"));

        boolean giaPresente = false;
        String lineContent;
        String content = "";

        while ((lineContent = br.readLine()) != null) {


            if (lineContent.split(",")[0].equals(studente.getEmail())) {
                giaPresente = true;
                lineContent = (lineContent.split(",")[0] + "," + (Integer.parseInt(lineContent.split(",")[1])+1));
            }

            content += lineContent + "\n";
        }
        br.close();
        if (!giaPresente) content += (studente.getEmail() + "," + "1");

        //scrive sul file
        BufferedWriter bw = new BufferedWriter(new FileWriter("student/absence.csv"));
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
