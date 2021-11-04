package repository;

import abstraction.DataRepository;
import javax.ejb.Stateless;
import model.Student;

@Stateless
public class StudentRepository extends DataRepository<Student, Long> {
    
    public StudentRepository()
    {
        super(Student.class, true);
    }
}
