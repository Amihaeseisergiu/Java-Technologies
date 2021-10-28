package model;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("WT")
public class WrittenTest extends Exam {
    
    @OneToMany(mappedBy = "writtenTest")
    List<Bibliography> bibliographies;
    
    public WrittenTest()
    {
        
    }
    
    public WrittenTest(String name, LocalTime startingTime, Integer duration)
    {
        super(name, startingTime, duration);
    }
}
