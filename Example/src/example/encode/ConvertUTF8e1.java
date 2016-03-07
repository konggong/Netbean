package example.encode;

public class ConvertUTF8e1 {

    //http://fabioangelini.wordpress.com/2011/08/04/converting-java-string-fromto-utf-8/
    
    // convert from UTF-8 -> internal Java String format
    public String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    // convert from internal Java String format -> UTF-8
    public String convertToUTF8(String s) {
        String out = null;
        try {
            //out = new String(s.getBytes("UTF-8"), "ISO-8859-1");

            byte[] ptext = s.getBytes("UTF-8");
            out = new String(ptext);

        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }


    public static void main(String[] args) {
        ConvertUTF8e1 conUTF8 = new ConvertUTF8e1();

        //String xmlstring = "Здравей' хора";
        String xmlstring = "ภาษาไทย";
        System.out.println("xmlstring = " + xmlstring);

        String utf8string = conUTF8.convertToUTF8(xmlstring);
        System.out.println("xmlstring utf8 = " + utf8string);
    }
}
