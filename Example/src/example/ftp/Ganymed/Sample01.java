package example.ftp.Ganymed;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sample01 {

    public static void main(String[] args) {
        String hostname = "10.22.13.63";
        String username = "pww";
        String password = "pww";

        try {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            Session sess = conn.openSession();
            sess.execCommand("cd /u/4gl/cs_src/pww; ls -l ");
            InputStream stdout = new StreamGobbler(sess.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Sample01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
