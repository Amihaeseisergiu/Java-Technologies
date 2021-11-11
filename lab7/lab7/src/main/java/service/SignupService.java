package service;

import abstraction.DataEdit;
import abstraction.Signup;
import controller.PageController;
import entity.User;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.UserRepository;
import utility.PasswordEncryption;

@Named
@RequestScoped
public class SignupService extends DataEdit<User, UserRepository> implements Signup {
    
    List<String> types = Arrays.asList("Admin", "Author", "Reviewer");
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PageController pageController;
    
    public SignupService()
    {
        super(User.class);
    }
    
    @Override
    public UserRepository getRepository()
    {
        return userRepository;
    }
    
    @Override
    public void beforeSave()
    {
        if(userRepository.usernameExists(entity.getUsername()))
        {
            throw new RuntimeException("Username already exists!");
        }
        
        entity.setPassword(PasswordEncryption.generateHash(entity.getPassword()));
    }
    
    @Override
    public String successOutcome()
    {
        return pageController.login() + "?faces-redirect=true";
    }
    
    @Override
    public String signup()
    {
        return save();
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    
}
