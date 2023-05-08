package view;

import controller.GestoreRegistro;
import models.*;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GestoreRegistro gest = new GestoreRegistro();
        Scanner scan = new Scanner(System.in);

        System.out.println("Login o Registrazione?");
        String risposta = scan.nextLine();
        
        while (!"Login".equals(risposta) && !"Registrazione".equals(risposta)) {
            System.out.println("Risposta non valida. Login o Registrazione?");
            risposta = scan.nextLine();
        }

        switch(risposta) {
            case "Login": {
                System.out.println("Email: ");
                String email = scan.nextLine();
                System.out.println("Password: ");
                String password = scan.nextLine();

                gest.login(email, password);
                break;
            }

            case "Registrazione": {
                System.out.println("Studente, Insegnante o Dirigente?");
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
                }
                else gest.register(email, password, tipologia, nome, cognome, date, genere, null);
                break;
            }
        }


        //                          MENU:
        int opzione = 0;
        do {
            System.out.println("-------------------------------------------------");
            if (gest.user instanceof Insegnante) {
                System.out.println("OPZIONI:");
                System.out.println("0. Esci;");
                System.out.println("1. Visualizza classi;");
                System.out.println("2. Aggiungi un voto;");
                System.out.println("3. Aggiungi attività;");
                System.out.println("4. Aggiungi lezione;");
                System.out.println("5. Aggiungi assenza;");
                System.out.println("6. Aggiungi rapporto;");

                opzione = scan.nextInt();

                switch (opzione) {
                    case 1: {
                        for (Classe c: ((Insegnante) gest.user).getClassi()) {
                            System.out.println(c.getSezione() + ": ");
                            for (Studente s: c.getStudenti()) {
                                System.out.println(s.getNome() + " " + s.getCognome());
                            }
                        }
                        break;
                    }

                    case 2: {
                        break;
                    }

                    case 3: {
                        break;
                    }

                    case 4: {
                        break;
                    }

                    case 5: {
                        break;
                    }

                    case 6: {
                        break;
                    }

                    default: break;
                }
            }
        } while(opzione != 0);
    }
}
