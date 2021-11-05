package test;

import test.Child;
import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;

@Stateless
public class ChildRepository extends DataRepository<Child, Long> implements Serializable {
    
    public ChildRepository()
    {
        super(Child.class, false);
    }
}