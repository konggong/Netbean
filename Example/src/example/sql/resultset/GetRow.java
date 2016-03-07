package example.sql.resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GetRow {

    public static ResultSet beforeResultset(ResultSet rs2, int row) throws SQLException {
        rs2.beforeFirst();
        for (int i = 1; i < row; i++) {
            rs2.next();
        }
        return rs2;
    }

    public static void main(String[] args) throws Exception {

        Class.forName("com.informix.jdbc.IfxDriver");
        Connection con = java.sql.DriverManager.getConnection("jdbc:informix-sqli://160.3.107.11:2546/ba:INFORMIXSERVER=online1;DB_LOCALE=th_th.thai620", "pww", "pww");

        StringBuilder str = new StringBuilder();
        str.append("select * ");
        str.append("  from testpww ");
        PreparedStatement sel_test = con.prepareStatement(str.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = sel_test.executeQuery();
        ResultSetMetaData rsmd;

        rsmd = rs.getMetaData();
        System.out.println("rsmd.getColumnCount() = " + rsmd.getColumnCount());

        rs.next();
        System.out.println(rsmd.getColumnName(1) + " = " + rs.getString(1));
        System.out.println(rsmd.getColumnName(2) + " = " + rs.getString(2));
        System.out.println(rsmd.getColumnName(3) + " = " + rs.getString(3));
        System.out.println(rsmd.getColumnName(4) + " = " + rs.getString(4));
        System.out.println("-----------------------------------------");
        rs.next();
        System.out.println(rsmd.getColumnName(1) + " = " + rs.getString(1));
        System.out.println(rsmd.getColumnName(2) + " = " + rs.getString(2));
        System.out.println(rsmd.getColumnName(3) + " = " + rs.getString(3));
        System.out.println(rsmd.getColumnName(4) + " = " + rs.getString(4));
        System.out.println("-----------------------------------------");
        rs = beforeResultset(rs, rs.getRow());
        System.out.println(rsmd.getColumnName(1) + " = " + rs.getString(1));
        System.out.println(rsmd.getColumnName(2) + " = " + rs.getString(2));
        System.out.println(rsmd.getColumnName(3) + " = " + rs.getString(3));
        System.out.println(rsmd.getColumnName(4) + " = " + rs.getString(4));
        System.out.println("-----------------------------------------");
        
        System.out.println("");
        System.out.println("");
        System.out.println("");

//        rs.beforeFirst();
//        while (rs.next()) {
//            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " = " + rs.getString(i));
//            }
//            System.out.println("-----------------------------------------");
//        }

        rs.close();

    }
}