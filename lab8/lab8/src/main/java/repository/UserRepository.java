package repository;

import abstraction.DataRepository;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserRepository extends DataRepository<User, Long> implements Serializable {
    
    public UserRepository()
    {
        super(User.class);
    }
    
    public User findByUsername(String username)
    {
        Query query = em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }
    
    public boolean usernameExists(String username)
    {
        User user = this.findByUsername(username);
        
        if(user == null)
        {
            return false;
        }
        else if(user.getUsername().equals(username))
        {
            return true;
        }
        
        return false;
    }
}
