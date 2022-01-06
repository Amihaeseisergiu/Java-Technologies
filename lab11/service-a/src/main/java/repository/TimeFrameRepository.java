package repository;

import abstraction.DataRepository;
import entity.TimeFrame;
import java.time.LocalTime;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TimeFrameRepository extends DataRepository<TimeFrame, Long> {
    
    public TimeFrameRepository()
    {
        super(TimeFrame.class);
    }
    
    @Override
    public void create(TimeFrame timeFrame)
    {
        TimeFrame existing = super.findById((long) 1);
        
        if(existing == null)
        {
            super.create(timeFrame);
        }
        else
        {
            existing.setStartingTime(timeFrame.getStartingTime());
            existing.setEndingTime(timeFrame.getEndingTime());
            super.update(existing);
        }
    }
    
    public TimeFrame get()
    {
        TimeFrame existing = super.findById((long) 1);
        
        if(existing != null)
        {
            return existing;
        }
        
        existing = new TimeFrame(LocalTime.MIN, LocalTime.MAX);
        super.create(existing);
        
        return existing;
    }
}