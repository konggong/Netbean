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
        TestSSH ftpkcc = new TestSSH();
//        String pathfile = "/u/sba/src/post/kttr/blp028.4gl";
//        String server = "sba_dora";
//        String pathout = "";
        
//        String server = "sba_dora";
//        String pathfile = "/u/sba/src/post/kttr/blp028_1.4gl";
//        String pathout = "4gl\\blp028_1.4gl";
//        
        String server = "kcc";
        String pathfile = "4gl\\blp028_1.4gl";
        String pathout = "/u/4gl/cs_src/";

        //ftpkcc.testSendCommand();
        //ftpkcc.fileExists(server, pathfile);
        //ftpkcc.downloadFile(server,pathfile,pathout);
        //ftpkcc.fileOpenVersion("kcc","/u/4gl/cs_src", "btc185.4gl");
        ftpkcc.uploadFile(server,pathfile,pathout);
    }

    public SSHManager connect(String server) {
        String connectionIP = "", userName = "", password = "", known_hosts = "";
        switch (server) {
            case "kcc":
                connectionIP = "10.22.13.63";
                userName = "pww";
                password = "pww";
                break;
            case "aroma":
                connectionIP = "10.22.11.22";
                userName = "webmaster";
                password = "webmaster@freewill";
                break;
            case "sba_dora":
                connectionIP = "10.22.13.58";
                userName = "upc";
                password = "upc";
                break;
        }

        SSHManager instance = new SSHManager(userName, password, connectionIP, "./file/authorized_keys");
        String errorMessage = instance.connectServer();
        if (errorMessage != null) {
            System.out.println("Error : " + errorMessage);
        }
        return instance;
    }

    public void testSendCommand(String server, String command) {
        System.out.println("---sendCommand---");
        server = "kcc";
        command = ". .profile;cd /u/4gl/cs_src;ls -l kn*.4gl";
        SSHManager instance = connect(server);
        String result = instance.sendCommand(command);
        System.out.println("result = " + result);
        instance.close();
        System.out.println("-------------------------------------");

    }

    public void fileExists(String server, String fullPathFile) {
        System.out.println("---fileExists---");
        SSHManager instance = connect(server);
        String command = "ls -l " + fullPathFile;
        String result = instance.sendCommand(command);
        if ("".equals(result)) {
            result = "File no found.";
        } else {
            result = "found " + fullPathFile;
        }
        System.out.println("result = " + result);
        instance.close();
        System.out.println("-------------------------------------");
    }

    public void fileOpenVersion(String server, String nfullpath, String nfile) {
        System.out.println("---open version---");
        server = "kcc";
        SSHManager instance = connect(server);
        String command = ". .profile;cd " + nfullpath + ";kvm -o " + nfile;
        String result = instance.sendCommand(command);
        if ("".equals(result)) result = "File no found.";
        else result = "found "+nfile;
        System.out.println("result = " + result);
        instance.close();
        System.out.println("-------------------------------------");
    }

    public void uploadFile(String server, String pathfile, String pathout) {
        System.out.println("---uplaod file---");
        SSHManager instance = connect(server);
        instance.sendCommandUpload(pathfile, pathout);
        instance.close();
        System.out.println("-------------------------------------");
    }

    public void downloadFile(String server, String pathfile, String pathout) {
        System.out.println("---uplaod file---");
        SSHManager instance = connect(server);
        instance.sendCommandDownload(pathfile, pathout);
        instance.close();
        System.out.println("-------------------------------------");
    }
}
