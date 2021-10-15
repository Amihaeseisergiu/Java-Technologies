package model;

import java.time.LocalTime;

public class Exam {
    
    private Long id;
    String name;
    LocalTime startingTime;
    Integer duration;
    private Integer day;
    
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
    
    public Integer getStartAsMinutes()
    {
        String[] startSplit = this.startingTime.toString().split(":");
        return Integer.valueOf(startSplit[0]) * 60 + Integer.valueOf(startSplit[1]);
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
    
    
}
