package view;

import abstraction.DataView;
import entity.Role;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.RoleRepository;

@Named
@ApplicationScoped
public class RoleView extends DataView<Role, RoleRepository> {
    
    @Inject
    RoleRepository roleRepository;
    
    @PostConstruct
    public void init()
    {
        loadData();
    }

    @Override
    public RoleRepository getRepository() {
        return this.roleRepository;
    }
}
