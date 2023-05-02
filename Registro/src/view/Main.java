package view;

import controller.GestoreRegistro;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestoreRegistro gest = new GestoreRegistro();
        Scanner scan = new Scanner(System.in);
        
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
            }
            case "Registrazione": {
                System.out.println("Studente, Insegnante o Dirigente?");
            }
        }
    }
}
