/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ftp.jsch;

/**
 *
 * @author Poowanut_Wat
 */
public class TestSSH {
    public static void main(String[] args) {
        testSendCommand();
    }

    public static void testSendCommand() {
        System.out.println("sendCommand");
        
        String connectionIP = "10.22.13.63";
        String userName = "pww";
        String password = "pww";        
        SSHManager instance = new SSHManager(userName, password, connectionIP, "./file/.profile");
        String errorMessage = instance.connect();

        if (errorMessage != null) {
            System.out.println(errorMessage);
        }
        
        String command = "cd /u/4gl/cs_src/pww; ls -l ";
        String result = instance.sendCommand(command);
        System.out.println("result = " + result);
        instance.close();
        System.out.println("-------------------------------------");



    }
}
