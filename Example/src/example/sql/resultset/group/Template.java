package example.sql.resultset.group;

import example.database.ConnectionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Template extends KReport {

    String line1;
    String header1;
    String dtail1;
    String linestr;

    public static void main(String[] args) throws SQLException, IOException {
        Template temp = new Template();
        
        if (args.length < 0) {
            ConnectionDB connectionDB = new ConnectionDB();
            Connection conn = connectionDB.getConnection();

            KNSQL knsql = new KNSQL(conn);
            knsql.append("select * ");
            knsql.append("  from pwwtest ");
            knsql.append(" order by school,room,idcode");

            temp.run(knsql.executeQuery());
            connectionDB.closeConnection();

        } else {
            temp.usage();
        }

    }

    public void run(ResultSet rs) throws SQLException, IOException {
        setWriteFile("./output/KReport.txt");
        setPageLength(42);
        String[] columnGroup = {"school", "room", "sex"};
        setColumnGroup(columnGroup);
        reportRun(rs);

    }

    public void usage() {
        System.out.println("    Ex. -args   no parameter ");
    }

    @Override
    public void setLayout() {
        line1 = "------------------------------------------------------------";
        header1 = " Room          IDCode          Sex          Name            ";
//      line1   = "------------------------------------------------------------";
        dtail1 = " ^1            ^2              ^3           ^4              ";
//      line1   = "------------------------------------------------------------";
    }

    @Override
    public void header() throws SQLException {
        printRow("FS Securities Public Company Limited");
        printRow("Report Convert 4gl to Java");
        printRow(line1);
        printRow(header1);
        printRow(line1);
    }

    @Override
    public void beforeGroup(String fieldGroup) throws SQLException {
        if ("school".equalsIgnoreCase(fieldGroup)) {
            printRow("--- " + rs.getString("school") + " ---");
        } else if ("room".equals(fieldGroup)) {
        } else if ("sex".equals(fieldGroup)) {
        }
    }

    @Override
    public void afterGroup(String fieldGroup) throws SQLException  {
        if ("school".equalsIgnoreCase(fieldGroup)) {
            newPage();
        }
    }

    @Override
    public void everyRow() throws SQLException {
        linestr = dtail1;
        linestr = ks_prformat(linestr, 1, rs.getString("room"));
        linestr = ks_prformat(linestr, 2, rs.getString("idcode"));
        linestr = ks_prformat(linestr, 3, rs.getString("sex"));
        linestr = ks_prformat(linestr, 4, rs.getString("name"));
        printRow(linestr);
    }

    @Override
    public void tailer() throws SQLException  {
        printRow(line1);
        printRow("Report By : pww");
        printRow(line1);
    }
}
