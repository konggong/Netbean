package example.file.writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWrite {

    public static void main(String[] args) {
        String fileIn = "./output/beta131003.txt";
        String fileOut = "./output/beta131003.csv";
        boolean append_to_file = false;
        try {
            FileReader reader = new FileReader(fileIn);
            BufferedReader br = new BufferedReader(reader);

            FileWriter fw = new FileWriter(fileOut, append_to_file);
            PrintWriter out = new PrintWriter(fw);

            String str, outstr = "";
            int i = 0;

            while ((str = br.readLine()) != null) {
                if (++i % 2 == 1) {
                    outstr = str + ",";
                } else {
                    out.printf("%s" + "%n", (outstr += str));
                }
            }
            
            br.close();
            out.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Reader01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
