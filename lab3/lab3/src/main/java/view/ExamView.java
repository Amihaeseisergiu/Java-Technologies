package view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.Exam;
import repository.ExamRepository;

@Named
@RequestScoped
public class ExamView implements Serializable {
    
    List<Exam> exams;
    
    @PostConstruct
    public void init() {
        exams = ExamRepository.getExams();
    }
    
    public List<Exam> getExams()
    {
        return ExamRepository.getExams();
    }
}
