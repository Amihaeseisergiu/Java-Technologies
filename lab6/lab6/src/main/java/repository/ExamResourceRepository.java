package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;
import model.ExamResource;
import model.ExamResourceId;

@Stateless
public class ExamResourceRepository extends DataRepository<ExamResource, ExamResourceId> implements Serializable {
    
    public ExamResourceRepository()
    {
        super(ExamResource.class, false);
    }
    
    public void increaseAssigned(ExamResourceId examResourceId)
    {
        em.createNamedQuery("ExamResource.increaseAssigned").setParameter("id", examResourceId).executeUpdate();
    }
}
