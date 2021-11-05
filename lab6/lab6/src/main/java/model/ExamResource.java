package model;

import abstraction.AbstractEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "ExamResource")
@Table(name = "exams_resources")
@NamedQuery(name = "ExamResource.increaseAssigned",
        query = "UPDATE ExamResource er SET er.assigned = er.assigned+1 WHERE er.id = :id")
public class ExamResource extends AbstractEntity<ExamResourceId> implements Serializable {
    
    @EmbeddedId
    ExamResourceId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("examId")
    Exam exam;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resourceId")
    Resource resource;
    
    @Column(name = "assigned")
    Integer assigned = 0;
    
    public ExamResource() {}
 
    public ExamResource(Exam exam, Resource resource) {
        this.exam = exam;
        this.resource = resource;
        this.id = new ExamResourceId(exam.getId(), resource.getId());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ExamResource that = (ExamResource) o;
        return Objects.equals(exam, that.exam) &&
               Objects.equals(resource, that.resource);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(exam, resource);
    }

    public ExamResourceId getId() {
        return id;
    }

    public void setId(ExamResourceId id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Integer getAssigned() {
        return assigned;
    }

    public void setAssigned(Integer assigned) {
        this.assigned = assigned;
    }
    
    
}
