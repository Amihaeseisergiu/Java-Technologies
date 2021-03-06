package model;

import abstraction.AbstractEntity;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "exams")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="exam_type", discriminatorType = DiscriminatorType.CHAR)
public class Exam extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @Column(name = "starting_time")
    LocalTime startingTime;
    
    @Column(name = "duration")
    Integer duration;
    
    @Transient
    Integer assignedDay;
    
    @ManyToMany(mappedBy = "exams")
    private List<Student> students;
    
    public Exam()
    {
        
    }
    
    public Exam(String name, LocalTime startingTime, Integer duration)
    {
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
    }
    
    public Exam(Long id, String name, LocalTime startingTime, Integer duration)
    {
        this.id = id;
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
    }
    
    public Exam(Long id, String name, LocalTime startingTime, Integer duration, List<Student> students)
    {
        this.id = id;
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
        this.students = students;
    }
    
    public Integer getStartAsMinutes()
    {
        String[] startSplit = this.startingTime.toString().split(":");
        return Integer.valueOf(startSplit[0]) * 60 + Integer.valueOf(startSplit[1]);
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAssignedDay() {
        return assignedDay;
    }

    public void setAssignedDay(Integer assignedDay) {
        this.assignedDay = assignedDay;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
