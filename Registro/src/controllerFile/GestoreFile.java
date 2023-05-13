package controllerFile;

import models.Classe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreFile {
    public static ArrayList<Classe> loadClassi() throws IOException {
        ArrayList<Classe> classi = new ArrayList<>();

        BufferedReader bufferedReaderClasse = new BufferedReader(new FileReader("classi.csv"));
        bufferedReaderClasse.readLine();
        String line;

        while((line = bufferedReaderClasse.readLine()) != null) {
            String[] info = line.split(",");
            Classe c = new Classe(info[0]);

            int i = 1;
            while (!info[i].equals("|")) {
                c.addStudente();
            }

            i++;
            while (info[i] != null) {

            }

            classi.add();

        }

        bufferedReaderClasse.close();
    }
}
