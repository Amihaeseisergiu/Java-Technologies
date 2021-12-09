package abstraction;

import enumeration.DatabaseOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.event.Observes;
import qualifier.EntityChange;

public abstract class DataView<T extends AbstractEntity, R extends DataRepository> {
    
    protected List<T> entities = new ArrayList<>();
    
    public abstract R getRepository();
    
    public void loadData()
    {
        entities = getRepository().findAll();
    }
    
    public void onEntityCreateEvent(@Observes @EntityChange(DatabaseOperation.CREATE) T entity)
    {
        entities.add(entity);
    }
    
    public void onEntityUpdateEvent(@Observes @EntityChange(DatabaseOperation.UPDATE) T entity)
    {
        Optional<T> foundEntity = entities.stream()
                .filter(e -> e.getId().equals(entity.getId())).findFirst();
        
        if(foundEntity.isPresent())
        {
            T getEntity = foundEntity.get();
            int index = entities.indexOf(getEntity);
            
            entities.set(index, entity);
        }
        else
        {
            entities.add(entity);
        }
    }
    
    public void onEntityDeleteEvent(@Observes @EntityChange(DatabaseOperation.DELETE) T entity)
    {
        Optional<T> foundEntity = entities.stream()
                .filter(e -> e.getId().equals(entity.getId())).findFirst();
        
        if(foundEntity.isPresent())
        {
            T getEntity = foundEntity.get();
            entities.remove(getEntity);
        }
    }

    public List<T> getEntities() {
        return entities;
    }
    
    public T getLastEntity()
    {
        if(!entities.isEmpty())
        {
            return entities.get(entities.size() - 1);
        }
        
        return null;
    }
}
