package view;

import abstraction.DatabaseInsert;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Resource;
import repository.ResourceRepository;

@Named
@ViewScoped
public class ResourceAddView extends DatabaseInsert implements Serializable {
    
    String name;
    Integer number;
    
    @EJB
    ResourceRepository resourceRepository;

    @Inject
    @Push(channel="push")
    PushContext push;
    
    @Override
    public void insertInDatabase() {
        resourceRepository.create(new Resource(name, number));
        
        this.name = null;
        this.number = null;
        
        push.send("updateResources");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
}
