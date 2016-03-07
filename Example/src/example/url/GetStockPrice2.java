package example.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class GetStockPrice2 {

    private String proxy_user = "poowanut_wat";
    private String proxy_pass = "satrojew07";
    Authenticator authenticator = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(proxy_user, proxy_pass.toCharArray()));
        }
    };

    public String getPrice(String stock) throws IOException {
        if ("".equals(stock)) {
            return null;
        }
        Authenticator.setDefault(authenticator);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("squid05", 8080));
        String StringURL = "http://marketdata.set.or.th/mkt/stockquotation.do?language=en&country=US&symbol=" + stock;
        URLConnection yc = new URL(StringURL).openConnection(proxy);
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;

        int i = 0;
        boolean found = false;
        String strfind = "Offer Price";
        String price = "";

        while ((inputLine = in.readLine()) != null) {
            if (inputLine.indexOf(strfind) > 0) {
                found = true;
            }
            i = (found) ? i + 1 : i;
            if (i == 3) {
                inputLine = inputLine.trim();
                int index1 = inputLine.indexOf(" ");
                
                if (index1 > 0) {
                    inputLine = inputLine.substring(0,index1);
                }
                in.close();
                return inputLine;
            }
        }
        in.close();
        return "";
    }
}
