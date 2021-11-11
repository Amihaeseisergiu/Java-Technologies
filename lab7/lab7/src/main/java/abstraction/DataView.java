package abstraction;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;

public abstract class DataView<T extends AbstractEntity, R extends DataRepository> {
    
    List<T> entities = new ArrayList<>();
    
    public abstract R getRepository();
    
    public void loadData()
    {
        entities = getRepository().findAll();
    }
    
    public void onEntityEvent(@Observes T entity)
    {
        entities.add(entity);
    }

    public List<T> getEntities() {
        return entities;
    }
    
}
