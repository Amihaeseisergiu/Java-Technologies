package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import model.Exam;

@Named
@ApplicationScoped
@Transactional
public class ExamRepository extends DataRepository<Exam, Long> implements Serializable {
    
    public ExamRepository()
    {
        super(Exam.class, true);
    }
}
