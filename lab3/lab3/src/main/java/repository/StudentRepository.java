package repository;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exam;
import model.Student;
import view.GrowlView;

public class StudentRepository {
    
    public static void addStudent(String name, List<Exam> exams)
    {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into students(name) values(?)", 
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                GrowlView.studentAddedFailure();
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next())
                {
                    Long studentId = generatedKeys.getLong(1);
                    PreparedStatement stmt2 = con.prepareStatement("insert into students_exams(student_id, exam_id) values(?, ?)");
                    
                    for(Exam ex : exams)
                    {
                        stmt2.setLong(1, studentId);
                        stmt2.setLong(2, ex.getId());
                        stmt2.executeUpdate();
                    }
                }
                else
                {
                    GrowlView.studentAddedFailure();
                }
            }
            
            GrowlView.studentAddedSuccess();
        } catch (SQLException ex) {
            GrowlView.studentAddedFailure();
        }
    }
    
    public static List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    Long studentId = rs.getLong(1);
                    String studentName = rs.getString(2);
                    List<Exam> studentExams = ExamRepository.getStudentExams(studentId);
                    
                    students.add(new Student(studentId, studentName, studentExams));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }
}
