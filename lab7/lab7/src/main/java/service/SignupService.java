package service;

import controller.PageController;
import entity.User;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.UserRepository;
import utility.PasswordEncryption;

@Named
@RequestScoped
public class SignupService {
    
    User user;
    List<String> types;
    
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
        types = Arrays.asList("Admin", "Author", "Reviewer");
    }
    
    public String signUp()
    {
        if(userRepository.usernameExists(user.getUsername()))
        {
            messageService.showError("Username already exists!", null);
            init();
            return pageController.signUp();
        }
        
        user.setPassword(PasswordEncryption.generateHash(user.getPassword()));
        userRepository.create(user);
        
        return pageController.login();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    
}
