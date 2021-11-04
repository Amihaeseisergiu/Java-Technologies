package abstraction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import view.GrowlView;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {
    
    protected Class<T> entityClass;
    private final boolean showMessages;
    
    @PersistenceContext(unitName = "lab6PU")
    protected EntityManager em;
    
    protected DataRepository(Class<T> entityClass, boolean showMessages) {
        
        this.entityClass = entityClass;
        this.showMessages = showMessages;
    }
    
    public T findById(ID id)
    {
        return id == null ? null : em.find(entityClass, id);
    }
    
    public List<T> findAll()
    {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return em.createQuery(query).getResultList();
    }
    
    public void create(T entity)
    {
        try
        {
            em.persist(entity);
            
            if(showMessages) GrowlView.showEntityMessage(entityClass.getSimpleName(), "Add", true);
        }
        catch(Exception e)
        {
            if(showMessages) GrowlView.showEntityMessage(entityClass.getSimpleName(), "Add", false);
        }
    }
    
    public void update(T entity)
    {
        try
        {
            em.merge(entity);
            if(showMessages) GrowlView.showEntityMessage(entityClass.getSimpleName(), "Update", true);
        }
        catch(Exception e)
        {
            if(showMessages) GrowlView.showEntityMessage(entityClass.getSimpleName(), "Update", false);
        }
    }

    public void remove(T entity)
    {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }
    
    public T refresh(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        
        em.refresh(entity);
        return entity;
    }
    
    public void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }
}
