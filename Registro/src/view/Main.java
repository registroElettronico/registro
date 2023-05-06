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
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));
        BufferedReader br = new BufferedReader(new FileReader("users.csv"));
        
        System.out.println("Login o Registrazione?");
        String risposta = scan.nextLine();
        
        while (!"Login".equals(risposta) && !"Registrazione".equals(risposta)) {
            System.out.println(risposta + "\n");
            System.out.println("Risposta non valida. Login o Registrazione?");
            risposta = scan.nextLine();
        }

        switch(risposta) {
            case "Login": {
                System.out.println("Email: ");
                String email = scan.nextLine();
                System.out.println("Password: ");
                String password = scan.nextLine();

                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] info = line.split(",");
                    if (info[0].equals(email) && info[1].equals(password)) {
                        Persona user = null;
                        Data data = new Data(info[5]);
                        switch (info[2]) {
                            case "Studente": {
                                user = new Studente(info[3], info[4], info[6].charAt(0), data, info[1], info[2], info[7]);
                                break;
                            }
                            case "Insegnante": {
                                user = new Insegnante(info[3], info[4], info[6].charAt(0), data, info[1], info[2]);
                                break;
                            }
                            case "Dirigente": {
                                user = new Dirigente(info[3], info[4], info[6].charAt(0), data, info[1], info[2]);
                                break;
                            }
                        }

                        gest.user = user;

                        break;
                    }
                }

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
                char genere = scan.next().charAt(0);

                System.out.println("Data di nascita: (yyyy/mm/gg):");
                String date = scan.nextLine();

                System.out.println("Email: ");
                String email = scan.nextLine();

                System.out.println("Password: ");
                String password = scan.nextLine();

                bw.append(email + "," + password + "," + tipologia + "," + nome + "," + cognome + "," + date + "," + genere);
                bw.flush();
            }
        }

        risposta = "";
        do {
            if (gest.user instanceof Insegnante) {
                System.out.println("OPZIONI:");
                System.out.println("1. Visualizza classi;");
                System.out.println("2. Esci;");

                risposta = scan.nextLine();

                switch (risposta) {
                    case "Visualizza classi": {
                        for (Classe c: ((Insegnante) gest.user).getClassi()) {
                            System.out.println(c.getSezione() + ": ");
                            for (Studente s: c.getStudenti()) {
                                System.out.println(s.getNome() + " " + s.getCognome());
                            }
                        }
                        break;
                    }
                    default: break;
                }
            }


        } while(risposta != "Esci");
    }
}
