package view;

import database.AbstractDatabaseInsert;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Exam;
import model.Student;
import org.primefaces.event.DragDropEvent;
import repository.ExamRepository;
import repository.StudentRepository;

@Named
@ViewScoped
public class StudentView extends AbstractDatabaseInsert implements Serializable {
    
    private String name;
    private List<Student> students;
    List<Exam> availableExams;
    List<Exam> droppedExams;
    
    @PostConstruct
    public void init() {
        students = StudentRepository.getStudents();
        availableExams = ExamRepository.getExams();
        droppedExams = new ArrayList<>();
    }
    
    @Override
    public void insertInDatabase()
    {
        StudentRepository.addStudent(name, droppedExams);
        
        this.name = null;
        init();
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
