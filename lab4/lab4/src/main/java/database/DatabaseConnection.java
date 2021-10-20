package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    
    private static Connection conn = null;
    
    private DatabaseConnection() {}
    
    public static Connection getConnection()
    {
        if(conn == null)
        {
            String url = "jdbc:postgresql://localhost:5432/lab3";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","labs");

            try {
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
}
