package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import model.Exam;
import model.Student;
import org.primefaces.event.DragDropEvent;
import repository.ExamRepository;
import repository.StudentRepository;
import abstraction.DatabaseInsert;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;

@Named
@ApplicationScoped
public class StudentView extends DatabaseInsert implements Serializable {
    
    private String name;
    private List<Student> students;
    List<Exam> availableExams;
    List<Exam> droppedExams;
    
    @EJB
    ExamRepository examRepository;
    
    @EJB
    StudentRepository studentRepository;
    
    @Inject
    @Push(channel="push")
    PushContext push;
    
    @PostConstruct
    public void init() {
        students = studentRepository.findAll();
        availableExams = examRepository.findAll();
        droppedExams = new ArrayList<>();
    }
    
    @Override
    public void insertInDatabase()
    {
        studentRepository.create(new Student(name, droppedExams));
        
        this.name = null;
        init();
        
        push.send("updateStudents");
    }
    
    public void onExamDrop(DragDropEvent<Exam> ddEvent) {
        Exam exam = ddEvent.getData();
        
        droppedExams.add(exam);
        availableExams.remove(exam);
    }
    
    public List<Exam> getDroppedExams()
    {
        return droppedExams;
    }
    
    public void fetchNewExams()
    {
        availableExams = examRepository.findAll();
    }
    
    public List<Exam> getAvailableExams()
    {
        return availableExams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }
    
}
