package example.database;

import example.property.propertiesFileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDB {

    public java.sql.Connection con = null;
    private String url;
    private String userName;
    private String password;
    private String className;
//    Informix KCC ba
//    private String url = "jdbc:informix-sqli://10.22.13.63:2546/ba:INFORMIXSERVER=online1;DB_LOCALE=th_th.thai620";
//    private String userName = "informix";
//    private String password = "informix";
//    private String className = "com.informix.jdbc.IfxDriver";
//
//    Informix 10.22.21.28 ba_imp
//    **INFORMIXSERVER = INFORMIXSERVER environment variable.
//    private String url = "jdbc:informix-sqli://10.22.21.28:61000/ba_imp:INFORMIXSERVER=sbaserver";
//    private String userName = "infoconn";
//    private String password = "infoconn@freewill";
//    
    /* INFORMIXSERVER (INFORMIXSERVER environment variable.)
     * echo $INFORMIXSERVER
     * 
     * POST
     * netstat -ln | grep -i 'tcp '
     * netstat -ln | grep -i {IP}
     * 
     */
//    SQL Server
//    private String url = "jdbc:sqlserver://localhost:1433;databaseName=PWWINC";
//    private String userName = "sa";
//    private String password = "1234566";
//    private String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//    MySQL
//    private String url = "jdbc:mysql://localhost:3306/PWWINC";
//    private String userName = "root";
//    private String password = "123456";
//    private String className = "org.gjt.mm.mysql.Driver";
//
//     Informs the driver to use server a side-cursor, 
//     which permits more than one active statement 
//     on a connection.
    private final String selectMethod = "cursor";

    // Constructor
    public ConnectionDB() {
        propertiesFileReader prop = new propertiesFileReader("database.xml");
        String utf8 = prop.getProperties("utf8");        
        this.url = prop.getProperties("url") + utf8;
        this.userName = prop.getProperties("username");
        this.password = prop.getProperties("password");
        this.className = prop.getProperties("classname");    
        
        System.out.println("url = " + this.url);
        System.out.println("userName = " + this.userName);
        System.out.println("password = " + this.password);
        System.out.println("className = " + this.className);
    }

    public String getConnectionUrl() {
        return url;
    }

    public java.sql.Connection getConnection() {
        try {
            Class.forName(this.className);
            con = java.sql.DriverManager.getConnection(getConnectionUrl(), this.userName, this.password);
            if (con != null) {
                System.out.println("Connection Successful!");
            } else {
                System.out.println("Connection Fail!");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error [getConnection()] ClassNotFoundException : " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error [getConnection()] SQLException : " + ex.getMessage());
        }
        return con;
    }

    public void displayDbProperties(String dbName) {
        java.sql.DatabaseMetaData dm;
        java.sql.ResultSet rs;
        try {
            con = this.getConnection();
            if (con != null) {
                dm = con.getMetaData();
                System.out.println("Driver Information");
                System.out.println("\tDriver Name: " + dm.getDriverName());
                System.out.println("\tDriver Version: " + dm.getDriverVersion());
                System.out.println("\nDatabase Information ");
                System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
                System.out.println("Avalilable Catalogs ");
                rs = dm.getCatalogs();
                while (rs.next()) {
                    System.out.println("\tcatalog: " + rs.getString(1));
                }
                rs.close();
                this.closeConnection();
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            System.out.println("Error Trace in displayDbProperties() : " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            System.out.println("Error Trace in closeConnection() : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        PreparedStatement statement;

        ConnectionDB connectionDB = new ConnectionDB();
        java.sql.Connection conn = connectionDB.getConnection();

        connectionDB.testVal(conn);
        
        // delete the H2 database named 'test' in the user home directory
        //DeleteDbFiles.execute("~", "test", true);

        StringBuilder str = new StringBuilder();
        str.append("select * ");
        //str.append("  from tcc ");
        str.append("  from k_proglist ");
        statement = conn.prepareStatement(str.toString());

        try (ResultSet rs2 = statement.executeQuery()) {
            if (rs2 != null && rs2.next()) {
                System.out.println("HAVE DATA");
                System.out.println(rs2.getString(1));
            } else {
                System.out.println("NO DATA");
            }
        } catch (Exception e) {
            System.out.println("Error Trace in executeQuery() : " + e.getMessage());
        } finally {
            connectionDB.closeConnection();
        }
    }

    public void testVal(Connection conn) throws SQLException {
        
    }
}
