package florizz.storage;

import florizz.core.FlorizzException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class StorageManager {
    private final String URL = "jdbc:sqlite:flowers.db";
    private Connection connection;
    private Statement statement;

    public void loadDatabase() throws FlorizzException {
        try {
            this.connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new FlorizzException("ERROR: unable to make connection with database");
        }
    }

    public void printAll() throws FlorizzException {
        try {
            ResultSet rs = statement.executeQuery("select * from flower");
            while(rs.next()) {
                System.out.println("name = " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new FlorizzException("ERROR: unable to query all");
        }
    }

}
