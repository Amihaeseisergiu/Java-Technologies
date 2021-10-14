package model;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class Student {

    String name;
    
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void submit()
    {
        try {
            Connection con = DatabaseConnection.getConnection();
            System.out.println(this.name);
            PreparedStatement stmt = con.prepareStatement("insert into students(name) values(?)");
            stmt.setString(1, this.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
