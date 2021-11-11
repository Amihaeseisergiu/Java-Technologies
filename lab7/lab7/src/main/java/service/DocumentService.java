package service;

import abstraction.DataEdit;
import entity.Document;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;
import repository.DocumentRepository;
import repository.UserRepository;

@Named
@RequestScoped
public class DocumentService extends DataEdit<Document, DocumentRepository> implements Serializable {

    UploadedFile file;
    List<User> authors = new ArrayList<>();
    User selectedAuthor;
    
    @Inject
    DocumentRepository documentRepository;
    
    @Inject
    UserRepository userRepository;
    
    @PostConstruct
    public void construct()
    {
        authors = userRepository.findAll().stream().filter(u -> u.getType().equals("Author")).collect(Collectors.toList());
    }
    
    public DocumentService()
    {
        super(Document.class);
    }
    
    public void onUserAdded(@Observes User user)
    {
        if(user.getType().equals("Author") && !authors.contains(user))
        {
            authors.add(user);
        }
    }
    
    public void addAuthor()
    {
        if(selectedAuthor != null)
        {
            entity.getAuthors().add(selectedAuthor);
            authors.remove(selectedAuthor);
        }
    }
    
    @Override
    public String save()
    {
        System.out.println(entity.getName());
        return null;
    }
    
    @Override
    public DocumentRepository getRepository() {
        return documentRepository;
    }

    public UploadedFile getFile() {
        return file;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public User getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    public void setSelectedAuthor(User selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }
    
}
