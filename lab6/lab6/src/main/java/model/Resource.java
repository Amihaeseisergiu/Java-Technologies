package model;

import abstraction.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
@NamedQuery(name = "Resource.isAvailable",
        query = "SELECT CASE WHEN r.number > 0 THEN true ELSE false END FROM Resource r WHERE r.id = :id")
@NamedQuery(name = "Resource.claim", query = "UPDATE Resource r SET r.number = r.number-1 WHERE r.id = :id")
public class Resource extends AbstractEntity<Long> {
    
    String name;
    Integer number;
    
    @OneToMany(
        mappedBy = "resource",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    List<ExamResource> exams = new ArrayList<>();
    
    public Resource()
    {
        
    }
    
    public Resource(String name, Integer number)
    {
        this.name = name;
        this.number = number;
    }

    public void addExam(Exam exam)
    {
        ExamResource examResource = new ExamResource(exam, this);
        exams.add(examResource);
        exam.getResources().add(examResource);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<ExamResource> getExams() {
        return exams;
    }

    public void setExams(List<ExamResource> exams) {
        this.exams = exams;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Resource other = (Resource) obj;
        return Objects.equals(id, other.id);
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
