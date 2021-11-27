package service;

import abstraction.DataEdit;
import entity.TimeFrame;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import repository.TimeFrameRepository;

@Named
@RequestScoped
public class TimeFrameService extends DataEdit<TimeFrame, TimeFrameRepository> implements Serializable {

    @Inject
    TimeFrameRepository timeFrameRepository;
    
    public TimeFrameService()
    {
        super(TimeFrame.class);
    }
    
    @Override
    public TimeFrameRepository getRepository()
    {
        return timeFrameRepository;
    }
    
    @Override
    public void beforeSave()
    {
        if(entity.getStartingTime().isAfter(entity.getEndingTime()))
        {
            throw new RuntimeException("Starting Time should be before Ending Time!");
        }
    }
}
