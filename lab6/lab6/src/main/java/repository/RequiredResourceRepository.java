package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;
import model.RequiredResource;

@Stateless
public class RequiredResourceRepository extends DataRepository<RequiredResource, Long> implements Serializable {
    
    public RequiredResourceRepository()
    {
        super(RequiredResource.class, false);
    }
}
