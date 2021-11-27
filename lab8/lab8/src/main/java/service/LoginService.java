package service;

import auth.AuthenticationManager;
import controller.PageController;
import entity.User;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
        
        authenticationManager.login(loggedUser);
        
        return pageController.homePage() + "?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
