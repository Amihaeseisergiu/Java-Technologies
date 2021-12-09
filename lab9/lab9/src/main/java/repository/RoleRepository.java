package repository;

import abstraction.DataRepository;
import entity.Role;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RoleRepository extends DataRepository<Role, Long> {
    
    public RoleRepository()
    {
        super(Role.class);
    }
}
