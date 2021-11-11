package decorator;

import abstraction.Signup;
import entity.TimeFrame;
import java.time.LocalTime;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import repository.TimeFrameRepository;
import service.MessageService;

@Decorator
public abstract class VerifyTimeFrameDecorator implements Signup {
    
    @Inject
    @Delegate
    @Any
    Signup signup;
    
    @Inject
    TimeFrameRepository timeFrameRepository;
    
    @Inject
    MessageService messageService;
    
    @Override
    public String signup()
    {
        TimeFrame timeFrame = timeFrameRepository.get();
        LocalTime now = LocalTime.now();
        
        if(!(now.isAfter(timeFrame.getStartingTime()) && now.isBefore(timeFrame.getEndingTime())))
        {
            messageService.showError("The registrations are closed!", null);
            return null;
        }
        
        return signup.signup();
    }
    
}
