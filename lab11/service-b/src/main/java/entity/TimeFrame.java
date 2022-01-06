package entity;

import abstraction.AbstractEntity;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_frame")
public class TimeFrame extends AbstractEntity<Long> {
    
    @Column(name = "starting_time")
    LocalTime startingTime;
    
    @Column(name = "ending_time")
    LocalTime endingTime;
    
    public TimeFrame()
    {
        
    }
    
    public TimeFrame(LocalTime startingTime, LocalTime endingTime)
    {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }
    
}