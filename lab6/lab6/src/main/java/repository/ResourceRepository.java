package repository;

import abstraction.DataRepository;
import exception.ResourceException;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.Resource;

@Stateless
public class ResourceRepository extends DataRepository<Resource, Long> implements Serializable {
    
    public ResourceRepository()
    {
        super(Resource.class, true);
    }
    
    public boolean isAvailable(Resource resource)
    {
        Query query = em.createNamedQuery("Resource.isAvailable", Boolean.class).setParameter("id", resource.getId());
        return (boolean) query.getSingleResult();
    }
    
    public void claim(Resource resource) throws ResourceException
    {
        try
        {
            em.createNamedQuery("Resource.claim").setParameter("id", resource.getId()).executeUpdate();
        } catch(Exception e)
        {
            throw new ResourceException("Resource number depleted", e);
        }
    }
}
