package model;

import abstraction.AbstractEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends AbstractEntity<Long> {

    String name;
    
    @JoinTable(name = "students_exams",
            joinColumns = {
                @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "exam_id", referencedColumnName = "id")
            }
    )
    @ManyToMany
    List<Exam> exams;
    
    public Student() {
    }
    
    public Student(String name)
    {
        this.name = name;
    }
    
    public Student(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    public Student(String name, List<Exam> exams)
    {
        this.name = name;
        this.exams = exams;
    }
    
    public Student(Long id, String name, List<Exam> exams)
    {
        this.id = id;
        this.name = name;
        this.exams = exams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Exam> getExams() {
        return exams;
    }
    
}
