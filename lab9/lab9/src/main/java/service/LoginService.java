package service;

import auth.AuthenticationManager;
import controller.PageController;
import entity.User;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import repository.UserRepository;
import utility.PasswordEncryption;

@Named
@RequestScoped
public class LoginService {
    
    User user;
    
    @Inject
    AuthenticationManager authenticationManager;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PageController pageController;
    
    @Inject
    MessageService messageService;
    
    @PostConstruct
    public void init()
    {
        user = new User();
    }
    
    public String login()
    {
        if(!userRepository.usernameExists(user.getUsername()))
        {
            messageService.showError("Account doesn't exist! Let's create one now.", null);
            return pageController.signUp();
        }
        
        User loggedUser = userRepository.findByUsername(user.getUsername());
        
        if(!PasswordEncryption.validatePassword(user.getPassword(), loggedUser.getPassword()))
        {
            messageService.showError("Wrong password! Please try again.", null);
            return pageController.login();
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            request.login(user.getUsername(), user.getPassword());
            authenticationManager.login(loggedUser);
        
            return pageController.homePage() + "?faces-redirect=true";
        } catch(ServletException e) {
            messageService.showError(e.getMessage(), null);
            
            return pageController.login();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
