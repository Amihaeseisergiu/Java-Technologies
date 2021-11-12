package service;

import abstraction.DataEdit;
import abstraction.Registration;
import entity.Document;
import entity.User;
import interceptor.Submission;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;
import qualifier.Random;
import repository.DocumentRepository;
import view.UserView;

@Named
@ViewScoped
public class DocumentService extends DataEdit<Document, DocumentRepository> implements Registration, Serializable {

    UploadedFile file;
    List<User> authors = new ArrayList<>();
    User selectedAuthor;
    
    @Inject
    DocumentRepository documentRepository;
    
    @Inject
    UserView userView;
    
    @Inject
    @Random
    Instance<String> registrationNumber;
    
    @PostConstruct
    public void construct()
    {
        authors = userView.getEntities().stream().filter(u -> u.getType().equals("Author")).collect(Collectors.toList());
    }
    
    public DocumentService()
    {
        super(Document.class);
    }
    
    public void onUserAdded(@Observes User user)
    {
        if(user.getType().equals("Author"))
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
    public void beforeSave()
    {
        entity.setContent(file.getContent());
        entity.setRegistrationNumber(registrationNumber.get());
    }
    
    @Override
    @Submission
    public String register()
    {
        return save();
    }
    
    @Override
    public void afterSave()
    {
        super.afterSave();
        construct();
    }
    
    public void fetchNewAuthors()
    {
        if(userView.getLastEntity().getType().equals("Author"))
        {
            authors.add(userView.getLastEntity());
        }
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
