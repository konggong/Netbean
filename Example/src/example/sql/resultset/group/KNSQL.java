package example.sql.resultset.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class KNSQL {

    private StringBuilder strSQL = new StringBuilder();
    private PreparedStatement statement;
    private Connection conn;

    public KNSQL(Connection conn) {
        this.conn = conn;
    }

    public void append(String sql) {
        this.strSQL.append(sql);
    }

    public void clear() {
        this.strSQL.setLength(0);
    }

    public void executeUpdate() throws SQLException {
        statement = conn.prepareStatement(this.strSQL.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate();
    }

    public ResultSet executeQuery() throws SQLException {
        statement = conn.prepareStatement(this.strSQL.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement.executeQuery();
    }
}
