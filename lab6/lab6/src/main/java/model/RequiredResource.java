package model;

import abstraction.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "required_resources")
public class RequiredResource extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @JoinColumn(name = "project_presentation_id", nullable = false)
    @ManyToOne
    ProjectPresentation projectPresentation;
    
    public RequiredResource()
    {
        
    }
    
    public RequiredResource(String name)
    {
        this.name = name;
    }
    
    public RequiredResource(String name, ProjectPresentation projectPresentation)
    {
        this.name = name;
        this.projectPresentation = projectPresentation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectPresentation getProjectPresentation() {
        return projectPresentation;
    }

    public void setProjectPresentation(ProjectPresentation projectPresentation) {
        this.projectPresentation = projectPresentation;
    }
    
    
}
