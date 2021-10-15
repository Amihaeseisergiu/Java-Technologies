package model;

import java.util.List;

public class Student {

    private Long id;
    String name;
    private List<Exam> exams;
    
    public Student() {
    }
    
    public Student(Long id, String name, List<Exam> exams)
    {
        this.id = id;
        this.name = name;
        this.exams = exams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public List<Exam> getExams() {
        return exams;
    }
    
}
