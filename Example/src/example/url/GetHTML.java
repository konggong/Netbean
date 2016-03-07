package example.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class GetHTML {

    static Authenticator authenticator = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication("poowanut_wat", "satrojew07".toCharArray()));
        }
    };

    public static void main(String[] args) throws Exception {
        Authenticator.setDefault(authenticator);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("squid05", 8080));
        
        String stock = "CPALL";
        String StringURL = "http://www.settrade.com/C04_01_stock_quote_p1.jsp?txtSymbol="+stock+"&selectPage=";
        
        URLConnection yc = new URL(StringURL).openConnection(proxy);
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        
        int i = 0;
        boolean found = false;
        String strfind = "border-index";
        String price = "";
        
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.indexOf(strfind) > 0) {
                System.out.println("Found...!!!");
                System.out.println(inputLine);
                found = true;
            }
            i = (found) ? i+1 : i;
            if (i == 5) {
                System.out.println(inputLine);
                price = inputLine;
                System.out.println("price = " + getPrice(price));
                found = false;
                i++;
            }
            
            
            
        }
        in.close();
    }
    
    public static String getPrice(String html) {
        int index1 = html.indexOf("\">");
        int index2 = html.indexOf("</d");
        System.out.println("index1 = " + index1);
        System.out.println("index2 = " + index2);
        
        if (index1 > 0 && index2 > 0) {
            html = html.substring(index1+2, index2);
        }        
        return html;
    }
}
