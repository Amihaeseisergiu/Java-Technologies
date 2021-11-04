package view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.Resource;
import repository.ResourceRepository;

@Named
@ApplicationScoped
public class ResourceView {
    
    List<Resource> resources;
    
    @EJB
    ResourceRepository resourceRepository;
    
    @PostConstruct
    public void init()
    {
        resources = resourceRepository.findAll();
    }
    
    public void fetchNewResources()
    {
        resources = resourceRepository.findAll();
    }

    public List<Resource> getResources() {
        return resources;
    }
}
