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

        System.out.println("Login o Registrazione?");
        String risposta = scan.nextLine();
        
        while (!"Login".equals(risposta) && !"Registrazione".equals(risposta)) {
            System.out.println("Risposta non valida. Login o Registrazione?");
            risposta = scan.nextLine();
        }

        switch(risposta) {
            case "Login": {
                BufferedReader br = new BufferedReader(new FileReader("users.csv"));
                System.out.println("Email: ");
                String email = scan.nextLine();
                System.out.println("Password: ");
                String password = scan.nextLine();

                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] info = line.split(",");
                    if (info[0].equals(email) && info[1].equals(password)) {

                        switch (info[2]) {
                            case "Studente": {
                                for (Studente s: gest.getStudenti()) {
                                    if (s.getEmail().equals(info[0])) {
                                        gest.user = s;
                                        break;
                                    }
                                }
                                break;
                            }
                            case "Insegnante": {
                                for (Insegnante i: gest.getInsegnanti()) {
                                    if (i.getEmail().equals(info[0])) {

                                        gest.user = i;
                                        break;
                                    }
                                }
                                break;
                            }
                            case "Dirigente": {
                                for (Dirigente d: gest.getDirigenti()) {
                                    if (d.getEmail().equals(info[0])) {
                                        gest.user = d;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }

            case "Registrazione": {
                BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

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

                bw.append(email + "," + password + "," + tipologia + "," + nome + "," + cognome + "," + date + "," + genere);
                bw.flush();
            }
        }


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
                System.out.println(((Insegnante) gest.user).getClassi());


                switch (opzione) {
                    case 1: {
                        System.out.println("AAAAAAAA");
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
