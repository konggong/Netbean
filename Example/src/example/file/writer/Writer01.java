package example.file.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Writer01 {
    
    public static void main(String[] args) {
        String filePath = "./output/Writer01.txt";
        boolean append_to_file = false;
        
        try {
            FileWriter fw = new FileWriter(filePath,append_to_file);
            try (PrintWriter out = new PrintWriter(fw)) {
                for (int i = 0; i < 5; i++) {
                    out.printf("%s" + "%n", "1+1="+i);
                }
            }  
        } catch (IOException ex) {
            Logger.getLogger(Writer01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
