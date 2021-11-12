package decorator;

import abstraction.Registration;
import entity.TimeFrame;
import java.io.Serializable;
import java.time.LocalTime;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import repository.TimeFrameRepository;
import service.MessageService;

@Decorator
class VerifyTimeFrameDecorator implements Registration, Serializable {
    
    @Inject
    @Delegate
    @Any
    Registration registration;
    
    @Inject
    TimeFrameRepository timeFrameRepository;
    
    @Inject
    MessageService messageService;
    
    @Override
    public String register()
    {
        TimeFrame timeFrame = timeFrameRepository.get();
        LocalTime now = LocalTime.now();
        
        if(!(now.isAfter(timeFrame.getStartingTime()) && now.isBefore(timeFrame.getEndingTime())))
        {
            messageService.showError("The registrations are closed!", null);
            return null;
        }
        
        return registration.register();
    }
}
