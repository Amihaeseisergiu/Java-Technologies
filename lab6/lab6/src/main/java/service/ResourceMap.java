package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import model.Exam;
import model.Resource;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ResourceMap {
    
    Map<Resource, List<Exam>> resourceMap;
    
    @PostConstruct
    public void init()
    {
        resourceMap = new HashMap<>();
    }
    
    @Lock(LockType.WRITE)
    public void add(Resource resource, Exam exam)
    {
        if(resourceMap.containsKey(resource))
        {
            if(!resourceMap.get(resource).contains(exam))
                resourceMap.get(resource).add(exam);
        }
        else
        {
            List<Exam> exams = new ArrayList<>(1);
            exams.add(exam);
            resourceMap.put(resource, exams);
        }
    }

    @Lock(LockType.READ)
    public Map<Resource, List<Exam>> getResourceMap() {
        return resourceMap;
    }
    
}
