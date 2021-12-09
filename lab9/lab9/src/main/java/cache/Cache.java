package cache;

import entity.Document;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;

@ApplicationScoped
public class Cache {

    Map<Long, List<Document>> cache = new HashMap<>();
    
    public void onDocumentChange(@Observes @Any Document document)
    {
        cache.clear();
    }
    
    public List<Document> getDocuments(Long id)
    {
        if(id == null)
        {
            return cache.get((long) 0);
        }
        
        return cache.get(id);
    }
    
    public void addDocuments(Long id, List<Document> documents)
    {
        cache.put(id == null ? (long) 0 : id, documents);
    }
    
    public boolean exists(Long id)
    {
        return cache.containsKey(id == null ? (long) 0 : id);
    }
}
