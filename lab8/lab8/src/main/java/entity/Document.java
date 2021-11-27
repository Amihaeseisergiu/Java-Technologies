package entity;

import abstraction.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "documents")
public class Document extends AbstractEntity<Long> {
    
    @NotNull(message = "Document name must be provided!")
    @Column(name = "name")
    String name;
    
    @NotEmpty(message = "A document must have at least one author!")
    @JoinTable(name = "documents_authors",
            joinColumns = {
                @JoinColumn(name = "document_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "author_id", referencedColumnName = "id")
            }
    )
    @ManyToMany
    List<User> authors = new ArrayList<>();
    
    @Lob
    @Column(name = "content")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    byte[] content;
    
    @NotNull(message = "Each document must have a registration number!")
    @Column(name = "registration_number")
    String registrationNumber;
    
    @JoinTable(name="documents_references",
            joinColumns = {
                @JoinColumn(name = "document_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "reference_id", referencedColumnName = "id")
            }
    )
    @ManyToMany
    @JsonIgnoreProperties("references")
    List<Document> references = new ArrayList<>();

    @ManyToMany(mappedBy = "references")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Document> referenced = new ArrayList<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Document> getReferences() {
        return references;
    }

    public void setReferences(List<Document> references) {
        this.references = references;
    }

    public List<Document> getReferenced() {
        return referenced;
    }

    public void setReferenced(List<Document> referenced) {
        this.referenced = referenced;
    }
    
}
