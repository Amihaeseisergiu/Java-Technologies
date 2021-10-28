package repository;

import java.io.Serializable;
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
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import model.Exam;
import view.GrowlView;

@Named
@ApplicationScoped
public class ExamRepository implements Serializable {
    
    @Resource(name="database")
    private DataSource ds;
    
    public void addExam(String name, LocalTime startingTime, Integer duration)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ds.getConnection();
            
            stmt = con.prepareStatement("insert into exams(name, starting_time, duration) values(?, ?, ?)");
            stmt.setString(1, name);
            stmt.setTime(2, Time.valueOf(startingTime));
            stmt.setInt(3, duration);
            stmt.executeUpdate();
            
            GrowlView.examAddedSuccess();
        } catch (SQLException ex) {
            GrowlView.examAddedFailure();
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
    }
    
    public void updateExam(Exam exam)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement("UPDATE exams SET name=?,starting_time=?,duration=? WHERE id=?");
            stmt.setString(1, exam.getName());
            stmt.setTime(2, Time.valueOf(exam.getStartingTime()));
            stmt.setInt(3, exam.getDuration());
            stmt.setLong(4, exam.getId());
            stmt.executeUpdate();
            
            GrowlView.examUpdateSuccess();
        } catch (SQLException ex) {
            GrowlView.examUpdateFailure();
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
    }
    
    public List<Exam> getExams()
    {
        List<Exam> exams = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ds.getConnection();
            
            stmt = con.prepareStatement("SELECT * FROM exams");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                exams.add(new Exam(rs.getLong(1), rs.getString(2), rs.getTime(3).toLocalTime(), rs.getInt(4)));
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
        
        return exams;
    }
    
    public List<Exam> getStudentExams(Long id)
    {   
        List<Exam> exams = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement("SELECT * FROM exams e JOIN students_exams se ON se.exam_id=e.id WHERE se.student_id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                exams.add(new Exam(rs.getLong(1), rs.getString(2), rs.getTime(3).toLocalTime(), rs.getInt(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
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
        
        return exams;
    }
}
