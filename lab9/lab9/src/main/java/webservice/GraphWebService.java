package webservice;

import entity.Document;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GraphWebService {
    
    public List<String> sortTopologically(List<Document> documents)
    {
        List<Document> sorted = new ArrayList<>();
        Set<Document> nodesNoIncomingEdge = new HashSet();
        
        for(Document d : documents)
        {
            if(d.getReferences().isEmpty())
            {
                nodesNoIncomingEdge.add(d);
            }
        }
        
        while(!nodesNoIncomingEdge.isEmpty())
        {
            Document n = nodesNoIncomingEdge.iterator().next();
            nodesNoIncomingEdge.remove(n);
            
            sorted.add(n);
            
            for(Document m : documents)
            {
                if(n.getReferenced().stream().filter(r -> r.getId().equals(m.getId())).findAny().isPresent())
                {
                    Document toRemove = m.getReferences().stream()
                            .filter(r -> r.getId().equals(n.getId())).findFirst().get();
                    
                    m.getReferences().remove(toRemove);
                
                    if(m.getReferences().isEmpty())
                    {
                        nodesNoIncomingEdge.add(m);
                    }
                }
            }
        }
        
        for(Document n : documents)
        {
            if(!n.getReferences().isEmpty())
            {
                return null;
            }
        }
        
        return sorted.stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }
}
