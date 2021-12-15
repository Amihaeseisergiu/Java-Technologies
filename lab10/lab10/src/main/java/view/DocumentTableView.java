package view;

import abstraction.DataView;
import entity.Document;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.DocumentRepository;

@Named
@ApplicationScoped
public class DocumentTableView extends DataView<Document, DocumentRepository> {
    
    @Inject
    DocumentRepository documentRepository;
    
    @PostConstruct
    public void init()
    {
        loadData();
    }
    
    @Override
    public DocumentRepository getRepository()
    {
        return documentRepository;
    }
}
