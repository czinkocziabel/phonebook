package phonebook;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        + "id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "lastname varchar (20),"
                        + "firstname varchar (20),"
                        + "email varchar (30))");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ArrayList<Person> getAllContacts() {
        String sql = "select * from contacts";
        ArrayList<Person> contact = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            contact = new ArrayList<>();
            while (rs.next()) {
                contact.add(new Person(rs.getInt("id"),rs.getString("firstname"), rs.getString("lastName"), rs.getString("email")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contact;
    }
    
    public void addContact(Person person) {
        try {

            
            String sql = "insert into contacts (lastname,firstname,email) values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void updateContact(Person person) {
        try {

            
            String sql = "update contacts set lastname = ?, firstname = ?, email = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, Integer.parseInt(person.getId()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeContact(Person person) {
        try {

            
            String sql = "delete from contacts where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(person.getId()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
