package phonebook;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private final String URL = "jdbc:derby:sempleDB;create=true";
    private final String USERNAME = "";
    private final String PASSWORD = "";

    private Connection conn;
    private Statement createStatement = null;
    private DatabaseMetaData dbmd = null;

    public DB() {
        conn = null;
        try {

            conn = DriverManager.getConnection(URL);
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        if (conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            ResultSet rs = dbmd.getTables(null, "APP", "CONTACTS", null);

            if (!rs.next()) {
                createStatement.execute("create table contacts("
                        + "lastname varchar 20,"
                        + "firstname varchar 20,"
                        + "email varchar 30)");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
