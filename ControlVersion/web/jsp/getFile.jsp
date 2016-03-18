<%-- 
    Document   : getFile
    Created on : Mar 17, 2016, 4:48:54 PM
    Author     : Poowanut_Wat
--%>
<%--
http://localhost:8084/ControlVersion/jsp/getFile.jsp?host=10.22.13.63&port=22&username=pww&password=pww&filepath=/u/4gl/client/src/blk999.4gl

http://localhost:8084/ControlVersion/jsp/getFile.jsp?
--%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="com.jcraft.jsch.SftpException"%>
<%@page import="com.jcraft.jsch.JSchException"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.jcraft.jsch.JSch"%>
<%@page import="com.jcraft.jsch.Session"%>
<%@page import="com.jcraft.jsch.Channel"%>
<%@page import="com.jcraft.jsch.ChannelSftp"%>
<%@ page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    private String readFile(String host, int port, String username, final String password, String filepath) {
        ChannelSftp sftp = null;
        Channel channel = null;
        Session sshSession = null;

        StringBuilder result = new StringBuilder();
        
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected!");
            channel = sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("Channel connected!");
            sftp = (ChannelSftp) channel;

            InputStream obj_InputStream = sftp.get(filepath);
            char[] ch_Buffer = new char[0x10000];
            Reader reader = new InputStreamReader(obj_InputStream, "UTF-8");
            int int_Line;
            do {
                int_Line = reader.read(ch_Buffer, 0, ch_Buffer.length);
                if (int_Line > 0) {
                    result.append(ch_Buffer, 0, int_Line);
                }
            } while (int_Line >= 0);
            reader.close();

        } catch (JSchException e) {
            System.out.println("Error JSchException : " + e.getMessage());
        } catch (SftpException e) {
            System.out.println("Error SftpException : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error IOException : " + e.getMessage());
        } finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
            System.out.println("Close Connection!");
            System.out.println("---------------------------------");
        }

        return result.toString();

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
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String host = request.getParameter("host");
    int port = Integer.parseInt(request.getParameter("port"));
    String filepath = request.getParameter("filepath");
    
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    System.out.println("host     = " + host);
    System.out.println("port     = " + port);
    System.out.println("filepath = " + filepath);
    
    String val = readFile(host,port,username,password,filepath);
    out.print(val);
%>
