package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Properties;
import java.io.IOException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.JSchException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;

public final class getFile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


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

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');

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

      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
