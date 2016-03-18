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
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

out.println("------------1<br>");
            String filename = request.getParameter("filename");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String host = request.getParameter("host");
            int port = Integer.parseInt(request.getParameter("port"));
out.println("------------2<br>");
            ChannelSftp sftp = null;
            Channel channel = null;
            Session sshSession = null;
out.println("------------3<br>");
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
out.println("------------4<br>");
                InputStream obj_InputStream = sftp.get(filename);
out.println("------------5<br>");                
                char[] ch_Buffer = new char[0x10000];
out.println("------------6<br>");                
                Reader reader = new InputStreamReader(obj_InputStream, "UTF-8");
out.println("------------7<br>");                
                int int_Line;
                do {
                    int_Line = reader.read(ch_Buffer, 0, ch_Buffer.length);
                    if (int_Line > 0) {
                        result.append(ch_Buffer, 0, int_Line);
                    }
                } while (int_Line >= 0);
                reader.close();
out.println("------------5<br>");
            } catch (JSchException e) {
                System.out.println("Error JSchException : " + e.getMessage());
            } catch (SftpException e) {
                System.out.println("Error SftpException : " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error IOException : " + e.getMessage());
            } finally {
                if (sftp != null) {
                    if (sftp.isConnected()) {
                        sftp.disconnect();
                    }
                }
                if (channel != null) {
                    if (channel.isConnected()) {
                        channel.disconnect();
                    }
                }
                if (sshSession != null) {
                    if (sshSession.isConnected()) {
                        sshSession.disconnect();
                    }
                }

            }
        
      out.write("        \n");
      out.write("        <div id=\"values\">");
      out.print(result.toString());
      out.write("</div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
