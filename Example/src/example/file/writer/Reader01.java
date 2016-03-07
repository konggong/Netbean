package example.file.writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader01 {
    
    public static void main(String[] args) {
        String filePath = "./output/Writer01.txt";
        try {
            FileReader reader = new FileReader(filePath);
            try (BufferedReader br = new BufferedReader(reader)) {
                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Reader01.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
