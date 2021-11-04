package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Exam;
import model.Exam_;
import model.Student;
import model.Student_;

@Stateless
public class ExamRepository extends DataRepository<Exam, Long> implements Serializable {
    
    public ExamRepository()
    {
        super(Exam.class, true);
    }
    
    public List<Exam> findByCriteria(String examName, String studentName, LocalTime from, LocalTime to)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = builder.createQuery(Exam.class);

        Root<Exam> exam = query.from(Exam.class);

        Predicate filter = builder.conjunction();
        
        if(examName != null && !examName.isEmpty())
        {
            filter = builder.and(filter, builder.equal(exam.get(Exam_.name), examName));
        }
        if(studentName != null && !studentName.isEmpty())
        {
            Join<Exam, Student> student = exam.join(Exam_.students);
            filter = builder.and(filter, builder.equal(student.get(Student_.name), studentName));
        }
        if(from != null && to != null)
        {
            filter = builder.and(filter, builder.between(exam.get(Exam_.startingTime), from, to));
        }
        
        query.where(filter);
        TypedQuery<Exam> typedQuery = em.createQuery(query);
        
        List<Exam> result = typedQuery.getResultList();
        return result;

    }
}
