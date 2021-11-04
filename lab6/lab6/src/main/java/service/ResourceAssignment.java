package service;

import exception.ResourceException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import model.Exam;
import model.Resource;
import repository.ResourceRepository;

@Stateful
@LocalBean
public class ResourceAssignment {
    
    List<Resource> resources;
    
    @EJB
    ResourceRepository resourceRepository;
    
    @EJB
    ResourceMap resourceMap;
    
    @PostConstruct
    public void init()
    {
        resources = new ArrayList<>();
    }
    
    public void add(Resource resource)
    {
        this.resources.add(resource);
    }
    
    public void reset()
    {
        init();
    }
    
    public void save(Exam exam) throws ResourceException
    {
        for(Resource r : resources)
        {
            r.addExam(exam);
            resourceRepository.claim(r);
            resourceRepository.update(r);
            resourceMap.add(r, exam);
        }
        
        reset();
    }
}
