package view;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Exam;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
import solver.ProblemSolver;

@Named
@ViewScoped
public class ResultsView implements Serializable {
    
    private TimelineModel<String, ?> model;
    private LocalDateTime start;
    private LocalDateTime end;
    
    @PostConstruct
    public void init() {
        List<Exam> exams = ProblemSolver.solve();
        
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        start = LocalDateTime.of(today, midnight);
        end = start.plusDays(1);
        
        model = new TimelineModel<>();
        
        for(Exam e : exams)
        {
            LocalDateTime examStart = start.plusDays(e.getDay()).plusMinutes(e.getStartAsMinutes());
            LocalDateTime examEnd = start.plusDays(e.getDay()).plusMinutes(e.getStartAsMinutes() + e.getDuration());
            
            TimelineEvent ex = TimelineEvent.builder()
                    .data(e.getName())
                    .startDate(examStart)
                    .endDate(examEnd)
                    .styleClass("blue")
                    .build();

            model.add(ex);
        }
    }

    public TimelineModel<String, ?> getModel() {
        return model;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
    
}
