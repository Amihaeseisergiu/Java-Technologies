package entity;

import abstraction.AbstractEntity;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username")
public class User extends AbstractEntity<Long> {
    
    @NotNull(message = "Username must be provided!")
    String username;
    
    @NotNull(message = "Password must be provided!")
    String password;
    
    @NotNull(message = "Type must be provided!")
    String type;
    
    @ManyToMany(mappedBy = "authors")
    List<Document> documents;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
