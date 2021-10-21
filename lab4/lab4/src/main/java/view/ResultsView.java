package view;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Exam;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
import repository.ExamRepository;
import repository.StudentRepository;
import solver.ProblemData;
import solver.ProblemSolver;

@Named
@ViewScoped
public class ResultsView implements Serializable {
    
    TimelineModel<String, ?> model;
    LocalDateTime start;
    LocalDateTime end;
    Integer nrExams = 1;
    Integer nrStudents = 1;
    
    @Inject
    ProblemSolver problemSolver;
    
    @Inject
    ExamRepository examRepository;
    
    @Inject
    StudentRepository studentRepository;
    
    @PostConstruct
    public void init() {
        List<Exam> exams = problemSolver.solve(new ProblemData(examRepository, studentRepository));
        
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
    
    public void test()
    {
        long solverStart = System.nanoTime();  
        
        List<Exam> exams = problemSolver.solve(new ProblemData(this.nrExams + 1, this.nrStudents + 1));
        
        long estimatedTime = System.nanoTime() - solverStart;
        double elapsedTimeInSecond = (double) estimatedTime / 1_000_000_000;
        GrowlView.testingFinished(elapsedTimeInSecond);
        
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
        
        this.nrExams = 1;
        this.nrStudents = 1;
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

    public Integer getNrExams() {
        return nrExams;
    }

    public void setNrExams(Integer nrExams) {
        this.nrExams = nrExams;
    }

    public Integer getNrStudents() {
        return nrStudents;
    }

    public void setNrStudents(Integer nrStudents) {
        this.nrStudents = nrStudents;
    }
    
}
