package view;

import abstraction.DatabaseInsert;
import exception.ResourceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.Exam;
import model.Resource;
import org.primefaces.event.DragDropEvent;
import repository.ExamRepository;
import repository.ResourceRepository;
import service.ResourceAssignment;

@Named
@ApplicationScoped
public class ReservationView extends DatabaseInsert implements Serializable {
    
    List<Exam> exams;
    Exam selectedExam;
    
    List<Resource> availableResources;
    List<Resource> droppedResources;
    
    @EJB
    ExamRepository examRepository;
    
    @EJB
    ResourceRepository resourceRepository;
    
    @EJB
    ResourceAssignment resourceAssignment;
    
    @PostConstruct
    public void init()
    {
        exams = examRepository.findAll();
        availableResources = resourceRepository.findAll().stream().filter(r -> r.getNumber() > 0).collect(Collectors.toList());
        
        selectedExam = exams.get(0) != null ? exams.get(0) : null;
        droppedResources = new ArrayList<>();
    }
    
    @Override
    public void insertInDatabase()
    {
        try {
            resourceAssignment.save(selectedExam);
        } catch (ResourceException ex) {
            GrowlView.showEntityMessage("Resource", "Number", false);
            resourceAssignment.reset();
        }
        
        init();
    }
    
    public void onResourceDrop(DragDropEvent<Resource> ddEvent) {
        Resource resource = ddEvent.getData();
        
        if(resourceRepository.isAvailable(resource))
        {
            resourceAssignment.add(resource);
            droppedResources.add(resource);
            availableResources.remove(resource);
        }
        else
        {
            GrowlView.showEntityMessage("Resource", "Number", false);
        }
    }
    
    public void fetchNewExams()
    {
        exams = examRepository.findAll();
        selectedExam = exams.get(0) != null ? exams.get(0) : null;
    }
    
    public void fetchNewResources()
    {
        availableResources = resourceRepository.findAll().stream().filter(r -> r.getNumber() > 0).collect(Collectors.toList());
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public Exam getSelectedExam() {
        return selectedExam;
    }

    public void setSelectedExam(Exam selectedExam) {
        this.selectedExam = selectedExam;
    }

    public List<Resource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<Resource> availableResources) {
        this.availableResources = availableResources;
    }

    public List<Resource> getDroppedResources() {
        return droppedResources;
    }

    public void setDroppedResources(List<Resource> droppedResources) {
        this.droppedResources = droppedResources;
    }
    
}
