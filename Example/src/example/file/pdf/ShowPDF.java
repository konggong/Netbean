
public class ShowPDF {

    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler F:/MX-3114N_20140910_174718.pdf");
        p.waitFor();
        System.out.println("Done.");
    }
}