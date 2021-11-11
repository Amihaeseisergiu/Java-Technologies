package abstraction;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import service.MessageService;

public abstract class DataEdit<T extends AbstractEntity, R extends DataRepository> {
    
    Class<T> entityClass;
    protected T entity;
    
    @Inject
    protected Event<T> dataEvent;
    
    @Inject
    protected MessageService messageService;
    
    @Inject
    @Push(channel="push")
    protected PushContext push;
    
    public DataEdit(Class<T> entityClass)
    {
        try {
            this.entityClass = entityClass;
            entity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void init()
    {
        try {
            entity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract R getRepository();
    
    public void beforeSave()
    {
        
    }
    
    public void saveEntity()
    {
        getRepository().create(entity);
    }
    
    public void afterSave()
    {
        init();
        push.send("update" + entityClass.getSimpleName());
    }
    
    public String successOutcome()
    {
        return null;
    }
    
    public String errorOutcome()
    {
        return null;
    }
    
    public String save()
    {
        try
        {
            beforeSave();
            saveEntity();
            dataEvent.fire(entity);
            afterSave();
            messageService.showInfo("Saved successfully!", null);
            
            return successOutcome();
        } catch(Exception ex) {
            
            messageService.showError(ex.getMessage(), null);
            
            return errorOutcome();
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
    
}
