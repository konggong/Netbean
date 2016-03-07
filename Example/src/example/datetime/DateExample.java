package example.datetime;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateExample {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date in_date = cal.getTime();
        
        System.out.println("in_date " + in_date);
        
        System.out.println("Date              = " + cal.getTime());
        // this does work, increment 10 days  
        cal.add(Calendar.DATE, 10);
        System.out.println("Date + 10 days    = " + cal.getTime());
        // this does NOT work, as no increment of 10 seconds  
        cal.add(Calendar.SECOND, 10);
        System.out.println("Date + 10 seconds = " + cal.getTime());

        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 10);
        System.out.println("Date + 10 days    = " + cal.getTime());

        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 10);
        System.out.println("Date + 10 seconds = " + cal.getTime());

        Locale locale_th = new Locale("th", "TH");
        TimeZone zone_th = TimeZone.getTimeZone("Asia/Bangkok");
        Calendar TH_caln = Calendar.getInstance(zone_th, locale_th);

        System.out.println("TimeZone = " + zone_th.getDisplayName());
        System.out.println("Locale   = " + locale_th.getDisplayName());
        System.out.println("TH_caln  = " + TH_caln.getTime());
    }
}
