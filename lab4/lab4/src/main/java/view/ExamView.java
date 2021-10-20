package view;

import database.AbstractDatabaseInsert;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Exam;
import repository.ExamRepository;

@Named
@ViewScoped
public class ExamView extends AbstractDatabaseInsert implements Serializable {
    
    private Long id;
    private String name;
    private LocalTime startingTime;
    private Integer duration;
    List<Exam> exams;
    
    @PostConstruct
    public void init() {
        exams = ExamRepository.getExams();
    }
    
    public List<Exam> getExams()
    {
        return ExamRepository.getExams();
    }
    
    @Override
    public void insertInDatabase()
    {
        ExamRepository.addExam(name, startingTime, duration);
        
        this.name = null;
        this.startingTime = null;
        this.duration = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
