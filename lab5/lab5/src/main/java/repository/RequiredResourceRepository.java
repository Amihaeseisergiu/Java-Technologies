package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import model.RequiredResource;

@Named
@ApplicationScoped
@Transactional
public class RequiredResourceRepository extends DataRepository<RequiredResource, Long> implements Serializable {
    
    public RequiredResourceRepository()
    {
        super(RequiredResource.class, false);
    }
}
