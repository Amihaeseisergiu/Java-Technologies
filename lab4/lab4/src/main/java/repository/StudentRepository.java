package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import model.Exam;
import model.Student;
import view.GrowlView;

@Named
@ApplicationScoped
public class StudentRepository {
    
    @Resource(lookup="java:comp/env/database")
    private DataSource ds;
    
    @Inject
    ExamRepository examRepository;
    
    public void addStudent(String name, List<Exam> exams)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement("insert into students(name) values(?)", 
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
                    stmt2 = con.prepareStatement("insert into students_exams(student_id, exam_id) values(?, ?)");
                    
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
        } finally {
            if(stmt2 != null)
            {
                try {
                    stmt2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException ex) {}
            }
            if(con != null)
            {
                try {
                    con.close();
                } catch (SQLException ex) {}
            }
        }
    }
    
    public List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();
        Connection con = null;
        
        try {
            con = ds.getConnection();
            String sql = "SELECT * FROM students";
            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    Long studentId = rs.getLong(1);
                    String studentName = rs.getString(2);
                    List<Exam> studentExams = examRepository.getStudentExams(studentId);
                    
                    students.add(new Student(studentId, studentName, studentExams));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(con != null)
            {
                try {
                    con.close();
                } catch (SQLException ex) {}
            }
        }
        
        return students;
    }
    
    public List<Student> getExamStudents(Long id)
    {
        List<Student> students = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ds.getConnection();
            stmt = 
                con.prepareStatement("SELECT * FROM students s JOIN students_exams se ON se.student_id=s.id WHERE se.exam_id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                students.add(new Student(rs.getLong(1), rs.getString(2)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException ex) {}
            }
            if(con != null)
            {
                try {
                    con.close();
                } catch (SQLException ex) {}
            }
        }
        
        return students;
    }
}
