package service;

import abstraction.DataEdit;
import controller.PageController;
import entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.UserRepository;
import utility.PasswordEncryption;
import abstraction.Registration;
import entity.Role;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import view.RoleView;

@Named
@RequestScoped
public class SignupService extends DataEdit<User, UserRepository> implements Registration {
    
    List<Role> roles = new ArrayList<>();
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PageController pageController;
    
    @Inject
    RoleView roleView;
    
    @PostConstruct
    public void initialize()
    {
        this.roles = roleView.getEntities();
    }
    
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
    public String register()
    {
        return save();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
