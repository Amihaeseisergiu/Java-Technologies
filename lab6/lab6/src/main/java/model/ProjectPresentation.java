package model;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("PP")
public class ProjectPresentation extends Exam {
    
    @OneToMany(mappedBy = "projectPresentation")
    List<RequiredResource> requiredResources;
    
    public ProjectPresentation()
    {
        
    }
    
    public ProjectPresentation(String name, LocalTime startingTime, Integer duration)
    {
        super(name, startingTime, duration);
    }
}
