package example.ftp.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPKCC {

    public static void main(String[] args) {
        String host = "10.22.13.63";
        String user = "pww";
        String password = "pww";
        String command1 = "";
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel = session.openChannel("exec");
            
//            command1 += ". .profile;";
//            command1 += "cd /u/4gl/cs_src/;";
//            command1 += "kvm -o pww0000.4gl;";
            
//            command1 += ". .profile;";
//            command1 += "cd /u/4gl/cs_src/;";
//            command1 += "echo y | cp pww0000.4gl.pww pww0000.4gl;";
            
//            command1 += ". .profile;";
//            command1 += "cd /u/4gl/cs_src/;";
//            command1 += "/u/4gl/cs_src/pww/k_chg.sh pww0000.4gl";            

            command1 += ". .profile;";
            command1 += "cd /u/4gl/cs_src/;";
            command1 += "echo 'control version test2' | kvm -c pww0000.4gl;";
            ((ChannelExec) channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            System.out.println(showMoniter(channel));
            
            channel.disconnect();  
            session.disconnect();
            System.out.println("DONE");
            
        } catch (JSchException ex) {
            Logger.getLogger(FTPKCC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String showMoniter(Channel channel) {
        InputStream in = null;
        String result = null;
        try {
            in = channel.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    result = new String(tmp, 0, i);
                    //System.out.print(result);
                }
                if (channel.isClosed()) {
                    result = result.concat("\nexit-status: " + channel.getExitStatus());
                    //System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FTPKCC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPKCC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FTPKCC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
