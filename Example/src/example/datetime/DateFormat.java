package example.datetime;

import java.text.SimpleDateFormat;

public class DateFormat {

    public static void main(String[] args) {
        java.util.Date indate = new java.util.Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("date = " + indate);
        System.out.println("date = " + dateformat.format(indate));
    }
}
