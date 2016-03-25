<%-- 
    Document   : execCommand
    Created on : Mar 25, 2016, 2:40:10 PM
    Author     : Poowanut_Wat
--%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.jcraft.jsch.JSchException"%>
<%@page import="com.jcraft.jsch.ChannelExec"%>
<%@page import="com.jcraft.jsch.Channel"%>
<%@page import="com.jcraft.jsch.Session"%>
<%@page import="com.jcraft.jsch.Session"%>
<%@page import="com.jcraft.jsch.JSch"%>

<%!
    private String username = null;
    private String password = null;
    private String host = null;
    private int port = -1;
    private String filepath1 = null;
    private String filepath2 = null;
    private int commandFlag = -1;

    private String execCommand() {
        Channel channel = null;
        Session sshSession = null;
        String command = "";
        String result = "";
        try {
            JSch jsch = new JSch();
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected!");

            channel = sshSession.openChannel("exec");

            if (commandFlag == 1) {
                command += ". .profile;";
                command += "cd /u/4gl/cs_src/;";
                command += "kvm -o pww0000.4gl;";
            } else if (commandFlag == 2) {
                command += ". .profile;";
                command += "cd /u/4gl/cs_src/;";
                command += "echo y | cp pww0000.4gl.pww pww0000.4gl;";
            } else if (commandFlag == 3) {
                command += ". .profile;";
                command += "cd /u/4gl/cs_src/;";
                command += "/u/4gl/cs_src/pww/k_chg.sh pww0000.4gl";
            } else if (commandFlag == 4) {
                command += ". .profile;";
                command += "cd /u/4gl/cs_src/;";
                command += "echo 'control version test2' | kvm -c pww0000.4gl;";
            }

            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            result = showMoniter(channel);
        } catch (JSchException ex) {
            System.out.println("Error JSchException : " + ex.getMessage());
        } finally {
            closeChannel(channel);
            closeSession(sshSession);
            System.out.println("Close Connection!");
            System.out.println("---------------------------------");
        }
        return result;

    }

    private String showMoniter(Channel channel) {
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
                    System.out.println("Error InterruptedException : " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error catch IOException : " + ex.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("Error finally IOException : " + ex.getMessage());
            }
        }
        return result;
    }

    private void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private void closeSession(Session sshSession) {
        if (sshSession != null) {
            if (sshSession.isConnected()) {
                sshSession.disconnect();
            }
        }
    }

%>
<%
    username = request.getParameter("username");
    password = request.getParameter("password");
    host = request.getParameter("host");
    port = Integer.parseInt(request.getParameter("port"));
    filepath1 = request.getParameter("filepath1");
    filepath2 = request.getParameter("filepath2");

    System.out.println("username  = " + username);
    System.out.println("password  = " + password);
    System.out.println("host      = " + host);
    System.out.println("port      = " + port);
    System.out.println("filepath1 = " + filepath1);
    System.out.println("filepath2 = " + filepath2);

    String val = execCommand();
    out.print(val);
%>    
