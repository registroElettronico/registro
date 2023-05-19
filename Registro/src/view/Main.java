package view;

import controller.GestoreRegistro;
import models.*;
import models.tools.Data;

import javax.management.InstanceNotFoundException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestoreRegistro gest;
        try {
            gest = new GestoreRegistro();
        } catch (IOException | InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Login o Registrazione?");
            String risposta = scan.nextLine();

            while (!"Login".equals(risposta) && !"Registrazione".equals(risposta)) {
                System.out.println("Risposta non valida. Login o Registrazione?");
                risposta = scan.nextLine();
            }

            switch (risposta) {
                case "Login" : {
                    System.out.println("Email: ");
                    String email = scan.nextLine();
                    System.out.println("Password: ");
                    String password = scan.nextLine();

                    gest.login(email, password);
                    break;
                }
                case "Registrazione" : {
                    System.out.println("Studente o Insegnante?");
                    String tipologia = scan.nextLine();

                    System.out.println("Nome: ");
                    String nome = scan.nextLine();

                    System.out.println("Cognome: ");
                    String cognome = scan.nextLine();

                    System.out.println("Genere: ");
                    char genere = scan.nextLine().charAt(0);

                    System.out.println("Data di nascita: (yyyy/mm/gg):");
                    String date = scan.nextLine();

                    System.out.println("Email: ");
                    String email = scan.nextLine();

                    System.out.println("Password: ");
                    String password = scan.nextLine();

                    if (tipologia.equals("Studente")) {
                        System.out.println("Classe: ");
                        String classe = scan.nextLine();

                        try {
                            gest.register(email, password, tipologia, nome, cognome, date, genere, classe);
                        } catch (IOException | InstanceNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            gest.register(email, password, tipologia, nome, cognome, date, genere, null);
                        } catch (IOException | InstanceNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                }
            }


            //                          MENU:
            int opzione = 0;
            do {
                System.out.println("-------------------------------------------------");
                if (gest.getUser() instanceof Insegnante) {
                    System.out.println("OPZIONI:");
                    System.out.println("0. Esci;");
                    System.out.println("1. Visualizza classi;");
                    System.out.println("2. Aggiungi un voto;");
                    System.out.println("3. Aggiungi attività;");
                    System.out.println("4. Aggiungi lezione (non finito);");
                    System.out.println("5. Aggiungi assenza;");
                    System.out.println("6. Aggiungi rapporto;");
                    System.out.println("7. Aggiungi una classe;");

                    opzione = scan.nextInt();
                    scan.nextLine();

                    switch (opzione) {
                        case 1: {
                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                System.out.println(c.getSezione() + ": ");
                                for (Studente s : c.getStudenti()) {
                                    System.out.println(s.getNome() + " " + s.getCognome());
                                }
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Scegli la classe: ");

                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                System.out.println(c.getSezione());
                            }

                            String sezione = scan.nextLine();
                            Classe classe = gest.getClasse(sezione);

                            while(classe == null) {
                                System.out.println("Classe non valida. Scegli la classe: ");

                                for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                    System.out.println(c.getSezione());
                                }

                                sezione = scan.nextLine();
                                classe = gest.getClasse(sezione);
                            }

                            System.out.println("Scegli lo studente: ");

                            for (Studente s: classe.getStudenti()) {
                                System.out.println(s.getNome() + " " + s.getCognome());
                            }

                            System.out.println("Nome: ");
                            String nome = scan.nextLine();

                            System.out.println("Cognome: ");
                            String cognome = scan.nextLine();

                            Studente studente = classe.getStudente(nome, cognome);

                            while(studente == null) {
                                System.out.println("Studente non valido. Scegli lo studente: ");

                                for (Studente s: classe.getStudenti()) {
                                    System.out.println(s.getNome() + " " + s.getCognome());
                                }

                                System.out.println("Nome: ");
                                nome = scan.nextLine();

                                System.out.println("Cognome: ");
                                cognome = scan.nextLine();

                                studente = classe.getStudente(nome, cognome);
                            }

                            System.out.println("Materia: ");
                            String materia = scan.nextLine();

                            System.out.println("Voto: ");
                            float voto = scan.nextFloat();

                            scan.nextLine();    //serve a togliere il carattere che va a capo

                            System.out.println("Data (yyyy/mm/gg): ");
                            String data = scan.nextLine();


                            try {
                                ((Insegnante) gest.getUser()).addVoto(new Voto(voto, materia, new Data(data), studente));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("Classe: ");
                            String classe = scan.nextLine();

                            System.out.println("Tipo (Verifica/Attività/Compito): ");
                            String tipo = scan.nextLine();

                            System.out.println("Data (yyyy/mm/gg): ");
                            String data = scan.nextLine();

                            System.out.println("Contenuto: ");
                            String content = scan.nextLine();

                            System.out.println("Ora inizio: ");
                            int start = scan.nextInt();

                            System.out.println("Ora fine: ");
                            int end = scan.nextInt();

                            try {
                                ((Insegnante) gest.getUser()).addAttivita(new Attivita(gest.getClasse(classe), (Insegnante) gest.getUser(), new Data(data), content, start, end, tipo));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                        case 4 : {
                            System.out.println("NON FINITO");
                            break;
                        }
                        case 5: {
                            System.out.println("Scegli la classe: ");

                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                System.out.println(c.getSezione());
                            }

                            String sezione = scan.nextLine();
                            Classe classe = gest.getClasse(sezione);

                            while(classe == null) {
                                System.out.println("Classe non valida. Scegli la classe: ");

                                for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                    System.out.println(c.getSezione());
                                }

                                sezione = scan.nextLine();
                                classe = gest.getClasse(sezione);
                            }

                            System.out.println("Scegli lo studente: ");

                            for (Studente s: classe.getStudenti()) {
                                System.out.println(s.getNome() + " " + s.getCognome());
                            }

                            System.out.println("Nome: ");
                            String nome = scan.nextLine();

                            System.out.println("Cognome: ");
                            String cognome = scan.nextLine();

                            Studente studente = classe.getStudente(nome, cognome);

                            while(studente == null) {
                                System.out.println("Studente non valido. Scegli lo studente: ");

                                for (Studente s: classe.getStudenti()) {
                                    System.out.println(s.getNome() + " " + s.getCognome());
                                }

                                System.out.println("Nome: ");
                                nome = scan.nextLine();

                                System.out.println("Cognome: ");
                                cognome = scan.nextLine();

                                studente = classe.getStudente(nome, cognome);
                            }

                            try {
                                ((Insegnante) gest.getUser()).addAssenza(studente);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                        case 6: {
                            System.out.println("Scegli la classe: ");

                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                System.out.println(c.getSezione());
                            }

                            String sezione = scan.nextLine();
                            Classe classe = gest.getClasse(sezione);

                            while(classe == null) {
                                System.out.println("Classe non valida. Scegli la classe: ");

                                for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                    System.out.println(c.getSezione());
                                }

                                sezione = scan.nextLine();
                                classe = gest.getClasse(sezione);
                            }

                            System.out.println("Scegli lo studente: ");

                            for (Studente s: classe.getStudenti()) {
                                System.out.println(s.getNome() + " " + s.getCognome());
                            }

                            System.out.println("Nome: ");
                            String nome = scan.nextLine();

                            System.out.println("Cognome: ");
                            String cognome = scan.nextLine();

                            Studente studente = classe.getStudente(nome, cognome);

                            while(studente == null) {
                                System.out.println("Studente non valido. Scegli lo studente: ");

                                for (Studente s: classe.getStudenti()) {
                                    System.out.println(s.getNome() + " " + s.getCognome());
                                }

                                System.out.println("Nome: ");
                                nome = scan.nextLine();

                                System.out.println("Cognome: ");
                                cognome = scan.nextLine();

                                studente = classe.getStudente(nome, cognome);
                            }

                            System.out.println("Motivo: ");
                            String motivo = scan.nextLine();

                            System.out.println("Data (yyyy/mm/gg): ");
                            String data = scan.nextLine();


                            try {
                                ((Insegnante) gest.getUser()).addRapporto(new Rapporto((Insegnante) gest.getUser(), studente, motivo, new Data(data)));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                        case 7: {
                            System.out.println("Sezione: ");
                            String sezione = scan.nextLine();

                            try {
                                ((Insegnante) gest.getUser()).addClasse(new Classe(sezione));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                        default : {break;}
                    }
                }
            } while(opzione != 0);
        } while (true);
    }
}
