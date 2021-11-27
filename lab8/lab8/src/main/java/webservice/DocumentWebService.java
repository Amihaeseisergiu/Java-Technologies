package webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Document;
import entity.User;
import enumeration.DatabaseOperation;
import exception.DocumentException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import qualifier.EntityChange;
import qualifier.Random;
import repository.DocumentRepository;
import repository.UserRepository;

@RequestScoped
public class DocumentWebService {
    
    @Inject
    DocumentRepository documentRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    @Random
    Instance<String> registrationNumber;
    
    @Inject
    @EntityChange(DatabaseOperation.CREATE)
    Event<Document> documentCreate;
    
    @Inject
    @EntityChange(DatabaseOperation.UPDATE)
    Event<Document> documentUpdate;
    
    @Inject
    @EntityChange(DatabaseOperation.DELETE)
    Event<Document> documentDelete;
    
    @Inject
    @Push(channel="push")
    protected PushContext push;
    
    public void addDocument(String json, InputStream content) throws DocumentException
    {
        Document document = fillDocumentData(json, content);
        
        documentRepository.create(document);
        documentRepository.clearCache();
        
        documentCreate.fire(document);
        push.send("updateDocument");
    }
    
    public void updateDocument(Long id, String json, InputStream content) throws DocumentException
    {
        if(!documentRepository.exists(id))
        {
            throw new DocumentException("Document with id " + id + " not found");
        }
        
        Document document = fillDocumentData(json, content);
        document.setId(id);
        
        documentRepository.update(document);
        documentRepository.clearCache();
        
        documentUpdate.fire(document);
        push.send("updateDocument");
    }
    
    public void deleteDocument(Long id) throws DocumentException
    {
        Document foundDocument = documentRepository.findById(id);
        
        if(foundDocument == null)
        {
            throw new DocumentException("Document with id " + id + " not found");
        }
        
        documentRepository.remove(foundDocument);
        documentRepository.clearCache();
        
        documentDelete.fire(foundDocument);
        push.send("updateDocument");
    }
    
    public List<Document> viewDocuments(Long id) throws DocumentException
    {
        if(id != null)
        {
            User user = userRepository.findById(id);
            
            return user.getDocuments();
        }
        
        return documentRepository.findAll();
    }
    
    public Document fillDocumentData(String json, InputStream content) throws DocumentException
    {
        Document document = getDocumentFromJson(json);
        document.setRegistrationNumber(registrationNumber.get());
        
        try {
            document.setContent(IOUtils.toByteArray(content));
        } catch (IOException ex) {
            throw new DocumentException("Content is not a valid file");
        }
        
        List<User> authors = new ArrayList<>();
        
        for(User u : document.getAuthors())
        {
            User user = userRepository.findById(u.getId());
            
            if(user == null)
            {
                throw new DocumentException("User with id " + u.getId() + " doesn't exist");
            }
            
            if(user.getType().equals("Author"))
            {
                authors.add(user);
            }
            else
            {
                throw new DocumentException("User with id " + u.getId() + " is not an author");
            }
        }
        
        document.setAuthors(authors);
        
        return document;
    }
    
    public Document getDocumentFromJson(String json) throws DocumentException
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Document document = objectMapper.readValue(json, Document.class);
            
            return document;
        } catch (JsonProcessingException ex) {
            throw new DocumentException("Json is not a valid document");
        }
    }
}
