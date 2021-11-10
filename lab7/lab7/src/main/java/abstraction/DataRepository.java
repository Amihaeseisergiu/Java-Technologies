package abstraction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {
    
    protected Class<T> entityClass;
    
    @PersistenceContext(unitName = "lab7PU")
    protected EntityManager em;
    
    protected DataRepository(Class<T> entityClass) {
        
        this.entityClass = entityClass;
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
        em.persist(entity);
    }
    
    public void update(T entity)
    {
        em.merge(entity);
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
