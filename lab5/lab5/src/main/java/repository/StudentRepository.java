package repository;

import abstraction.DataRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import model.Student;

@Named
@ApplicationScoped
@Transactional
public class StudentRepository extends DataRepository<Student, Long> {
    
    public StudentRepository()
    {
        super(Student.class, true);
    }
}
