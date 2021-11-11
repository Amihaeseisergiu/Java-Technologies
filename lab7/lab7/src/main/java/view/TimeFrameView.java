package view;

import entity.TimeFrame;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import repository.TimeFrameRepository;

@Named
@SessionScoped
public class TimeFrameView implements Serializable {

    TimeFrame timeFrame;
    
    @Inject
    TimeFrameRepository timeFrameRepository;
    
    @PostConstruct
    public void init()
    {
        this.timeFrame = timeFrameRepository.get();
    }
    
    public void onTimeFrameEvent(@Observes TimeFrame timeFrame)
    {
        this.timeFrame = timeFrame;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }
    
}
