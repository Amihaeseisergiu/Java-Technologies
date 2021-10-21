package view;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import model.Exam;
import repository.ExamRepository;
import abstraction.DatabaseInsert;
import java.util.Collections;
import java.util.Comparator;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named
@SessionScoped
public class ExamView extends DatabaseInsert implements Serializable {
    
    Long id;
    String name;
    LocalTime startingTime;
    Integer duration;
    List<Exam> exams;
    Exam selectedForEdit;
    
    @Inject
    ExamRepository examRepository;
    
    @PostConstruct
    public void init() {
        exams = examRepository.getExams();
        
        Collections.sort(exams, new Comparator<Exam>() {
            @Override
            public int compare(Exam o1, Exam o2) {
                
                return -o2.getId().compareTo(o1.getId());
            }
        });
    }
    
    public List<Exam> getExams()
    {
        init();
        return exams;
    }
    
    public void editRow(Exam selected)
    {
        selectedForEdit = selected;
    }
    
    public void save()
    {
        examRepository.updateExam(selectedForEdit);
        selectedForEdit = null;
    }
    
    public void cancel()
    {
        selectedForEdit = null;
    }
    
    @Override
    public void insertInDatabase()
    {
        examRepository.addExam(name, startingTime, duration);
        
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

    public Exam getSelectedForEdit() {
        return selectedForEdit;
    }
}
