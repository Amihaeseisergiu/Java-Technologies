package view;

import entity.Document;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class DocumentView implements Serializable {
    
    Document selectedDocument;
    
    public void selectDocument(Document document)
    {
        this.selectedDocument = document;
    }
    
    public StreamedContent getFileContent()
    {
        return DefaultStreamedContent.builder()
                .contentType("application/pdf")
                .name(selectedDocument.getName())
                .stream(() -> new ByteArrayInputStream(selectedDocument.getContent()))
                .build();
    }
}
