package repository;

import abstraction.DataRepository;
import entity.Document;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class DocumentRepository extends DataRepository<Document, Long> {
    
    public DocumentRepository()
    {
        super(Document.class);
    }
}
