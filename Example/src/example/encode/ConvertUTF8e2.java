package example.encode;

public class ConvertUTF8e2 {
    
    //Freewill
    public String decodeUTF8(String s) {
        if ((s == null) || (s.trim().equals(""))) {
            return s;
        }
        int l = s.length();
        int sumb = 0;
        int more = -1;
        StringBuilder sbuf = new StringBuilder(l);
        for (int i = 0; i < l; i++) {
            int b = s.charAt(i);
            if ((b & 0xC0) == 128) {
                sumb = sumb << 6 | b & 0x3F;
                more--;
                if (more == 0) {
                    sbuf.append((char) sumb);
                }
            } else if ((b & 0x80) == 0) {
                sbuf.append((char) b);
            } else if ((b & 0xE0) == 192) {
                sumb = b & 0x1F;
                more = 1;
            } else if ((b & 0xF0) == 224) {
                sumb = b & 0xF;
                more = 2;
            } else if ((b & 0xF8) == 240) {
                sumb = b & 0x7;
                more = 3;
            } else if ((b & 0xFC) == 248) {
                sumb = b & 0x3;
                more = 4;
            } else {
                sumb = b & 0x1;
                more = 5;
            }
        }
        return sbuf.toString();
    }
    
    //Freewill
    public static String encodeUTF8(String in) {
        if ((in == null) || (in.trim().equals(""))) {
            return in;
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char x = in.charAt(i);
            if (('A' <= x) && (x <= 'Z')) {
                out.append(x);
            } else if (('a' <= x) && (x <= 'z')) {
                out.append(x);
            } else if (('0' <= x) && (x <= '9')) {
                out.append(x);
            } else if ((x == '-')
                    || (x == '_')
                    || (x == '#')
                    || (x == '$')
                    || (x == '.')
                    || (x == '!')
                    || (x == '/')
                    || (x == '%')
                    || (x == '~')
                    || (x == '*')
                    || (x == ',')
                    || (x == '^')
                    || (x == '\'')
                    || (x == '(')
                    || (x == '@')
                    || (x == '&')
                    || (x == ')')
                    || (x == ' ')
                    || (x == '|')
                    || (x == '?')
                    || (x == '=')
                    || (x == '>')
                    || (x == '<')
                    || (x == ';')
                    || (x == ':')
                    || (x == '}')
                    || (x == '{')
                    || (x == '[')
                    || (x == ']')
                    || (x == '\\')
                    || (x == '"')
                    || (x == '+')) {
                out.append(x);
            } else if (x <= '\n') {
                out.append(x);
            } else if (x <= '') {
                out.append((byte) x);
            } else if (x <= '߿') {
                out.append((char) (0xC0 | x >> '\006'));
                out.append((char) (0x80 | x & 0x3F));
            } else if (x <= 65535) {
                out.append((char) (0xE0 | x >> '\f'));
                out.append((char) (0x80 | x >> '\006' & 0x3F));
                out.append((char) (0x80 | x & 0x3F));
            } else {
                out.append((char) (0xF0 | x >> '\022'));
                out.append((char) (0x80 | x >> '\f' & 0x3F));
                out.append((char) (0x80 | x >> '\006' & 0x3F));
                out.append((char) (0x80 | x & 0x3F));
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        ConvertUTF8e2 conUTF8 = new ConvertUTF8e2();

        String xmlstring = "ภาษาไทย";
        System.out.println("xmlstring = " + xmlstring);

        String utf8string = conUTF8.encodeUTF8(xmlstring);
        System.out.println("xmlstring utf8 = " + utf8string);
    }
}
