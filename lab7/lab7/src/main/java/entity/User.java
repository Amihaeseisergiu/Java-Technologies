package entity;

import abstraction.AbstractEntity;
import javax.persistence.Entity;
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
    
}
