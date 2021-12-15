package auth;

import controller.PageController;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AuthenticationManager implements Serializable {
    
    User user;
    
    @Inject
    PageController pageController;

    public void login(User user)
    {
        this.user = user;
    }
    
    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return pageController.homePage() + "?faces-redirect=true";
    }
    
    public boolean isLoggedIn()
    {
        return user != null;
    }

    public User getUser() {
        return user;
    }
    
    public Boolean hasRole(String name)
    {
        if(user == null)
            return false;
        
        return user.getRoles().stream().anyMatch(r -> r.getName().equals(name));
    }
}
