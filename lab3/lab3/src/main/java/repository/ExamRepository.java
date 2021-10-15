package repository;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exam;
import view.GrowlView;

public class ExamRepository {
    
    public static void addExam(String name, LocalTime startingTime, Integer duration)
    {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into exams(name, starting_time, duration) values(?, ?, ?)");
            stmt.setString(1, name);
            stmt.setTime(2, Time.valueOf(startingTime));
            stmt.setInt(3, duration);
            stmt.executeUpdate();
            
            GrowlView.examAddedSuccess();
        } catch (SQLException ex) {
            GrowlView.examAddedFailure();
        }
    }
    
    public static List<Exam> getExams()
    {
        List<Exam> exams = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM exams";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    exams.add(new Exam(rs.getLong(1), rs.getString(2), rs.getTime(3).toLocalTime(), rs.getInt(4)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exams;
    }
    
    public static List<Exam> getStudentExams(Long id)
    {   
        List<Exam> exams = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = 
                    conn.prepareStatement("SELECT * FROM exams e JOIN students_exams se ON se.exam_id=e.id WHERE se.student_id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                exams.add(new Exam(rs.getLong(1), rs.getString(2), rs.getTime(3).toLocalTime(), rs.getInt(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exams;
    }
}
