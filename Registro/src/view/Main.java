package view;

import controller.GestoreRegistro;
import models.*;
import models.tools.Data;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        GestoreRegistro gest = new GestoreRegistro();
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Login o Registrazione?");
            String risposta = scan.nextLine();

            while (!"Login".equals(risposta) && !"Registrazione".equals(risposta)) {
                System.out.println("Risposta non valida. Login o Registrazione?");
                risposta = scan.nextLine();
            }

            switch (risposta) {
                case "Login" -> {
                    System.out.println("Email: ");
                    String email = scan.nextLine();
                    System.out.println("Password: ");
                    String password = scan.nextLine();

                    gest.login(email, password);
                }
                case "Registrazione" -> {
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

                        gest.register(email, password, tipologia, nome, cognome, date, genere, classe);
                    } else gest.register(email, password, tipologia, nome, cognome, date, genere, null);
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
                    System.out.println("4. Aggiungi lezione;");
                    System.out.println("5. Aggiungi assenza;");
                    System.out.println("6. Aggiungi rapporto;");

                    opzione = scan.nextInt();
                    scan.nextLine();

                    switch (opzione) {
                        case 1 -> {
                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                System.out.println(c.getSezione() + ": ");
                                for (Studente s : c.getStudenti()) {
                                    System.out.println(s.getNome() + " " + s.getCognome());
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("Scegli lo studente: ");

                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                for (Studente s : c.getStudenti()) {
                                    System.out.println(s.getClasse().getSezione() + "| " + s.getNome() + " " + s.getCognome());
                                }
                            }

                            System.out.println("Nome: ");
                            String nome = scan.nextLine();

                            System.out.println("Cognome: ");
                            String cognome = scan.nextLine();

                            Studente student = null;

                            for (Classe c : ((Insegnante) gest.getUser()).getClassi()) {
                                for (Studente s : c.getStudenti()) {
                                    if (s.getNome().equals(nome) && s.getCognome().equals(cognome)) student = s;
                                }
                            }

                            if (student == null) {
                                System.out.println("SCELTA NON VALIDA");
                                break;
                            }

                            System.out.println("Materia: ");
                            String materia = scan.nextLine();
                            System.out.println("Voto: ");
                            float voto = scan.nextFloat();
                            scan.nextLine();
                            System.out.println("Data (yyyy/mm/gg): ");
                            String data = scan.nextLine();


                            ((Insegnante) gest.getUser()).addVoto(student, new Voto(voto, materia, new Data(data)));
                        }
                        case 3 -> {
                        }
                        case 4 -> {
                        }
                        case 5 -> {
                        }
                        case 6 -> {
                        }
                        default -> {
                        }
                    }
                }
            } while(opzione != 0);
        } while (true);
    }
}
