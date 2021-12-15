package view;

import abstraction.DataView;
import entity.User;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.UserRepository;

@Named
@ApplicationScoped
public class UserView extends DataView<User, UserRepository> {
    
    @Inject
    UserRepository userRepository;
    
    @PostConstruct
    public void init()
    {
        loadData();
    }
    
    @Override
    public UserRepository getRepository()
    {
        return userRepository;
    }
}
