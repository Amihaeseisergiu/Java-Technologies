package entity;

import abstraction.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username")
public class User extends AbstractEntity<Long> {
    
    @NotNull(message = "Username must be provided!")
    String username;
    
    @NotNull(message = "Password must be provided!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    
    @NotEmpty(message = "A user must have at least one role!")
    @JoinTable(name = "users_roles",
            joinColumns = {
                @JoinColumn(name = "username", referencedColumnName = "username")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "role", referencedColumnName = "name")
            }
    )
    @ManyToMany
    List<Role> roles = new ArrayList<>();
    
    @ManyToMany(mappedBy = "authors")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Document> documents = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(id, other.getId());
    }
}
