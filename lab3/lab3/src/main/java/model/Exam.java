package model;

import java.time.LocalTime;

public class Exam {
    
    private Long id;
    String name;
    LocalTime startingTime;
    Integer duration;
    
    public Exam()
    {
        
    }
    
    public Exam(Long id, String name, LocalTime startingTime, Integer duration)
    {
        this.id = id;
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
