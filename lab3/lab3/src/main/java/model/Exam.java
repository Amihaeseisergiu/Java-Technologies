package model;

import java.time.LocalTime;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import repository.ExamRepository;

@Named
@RequestScoped
public class Exam {
    
    String name;
    LocalTime startingTime;
    Integer duration;
    
    public Exam()
    {
        
    }
    
    public Exam(String name, LocalTime startingTime, Integer duration)
    {
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
    }
    
    public void addExam()
    {
        ExamRepository.addExam(name, startingTime, duration);
        
        this.name = null;
        this.startingTime = null;
        this.duration = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
