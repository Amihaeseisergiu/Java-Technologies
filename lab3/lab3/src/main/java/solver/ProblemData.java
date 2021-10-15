package solver;

import java.util.Collections;
import java.util.List;
import model.Exam;
import model.Student;
import repository.ExamRepository;
import repository.StudentRepository;

public class ProblemData {
    
    private final int[][] conflictMatrix;
    private final List<Exam> exams;
    private final List<Student> students;
    private final int nrStudents;
    private final int nrExams;
    private final int[] examsStart;
    private final int[] examsEnd;
    private final int[] examsDur;
    
    public ProblemData()
    {
        exams = ExamRepository.getExams();
        students = StudentRepository.getStudents();
        nrExams = exams.size();
        nrStudents = students.size();
        conflictMatrix = new int[nrExams][nrExams];
        examsStart = new int[nrExams];
        examsEnd = new int[nrExams];
        examsDur = new int[nrExams];
        
        for(int i = 0; i < conflictMatrix.length; i++)
        {
            Exam exami = exams.get(i);
            List<Student> examStudentsi = StudentRepository.getExamStudents(exami.getId());
            
            for(int j = 0; j < conflictMatrix.length; j++)
            {
                if(i != j)
                {
                    List<Student> examStudentsj = StudentRepository.getExamStudents(exams.get(j).getId());
                    
                    if(!examsDisjoint(examStudentsi, examStudentsj))
                    {
                        conflictMatrix[i][j] = 1;
                    }
                    else
                    {
                        conflictMatrix[i][j] = 0;
                    }
                }
                else
                {
                    conflictMatrix[i][j] = 0;
                }
            }
            
            examsStart[i] = exami.getStartAsMinutes();
            examsEnd[i] = examsStart[i] + exami.getDuration();
            examsDur[i] = exami.getDuration();
        }
    }
    
    public boolean examsDisjoint(List<Student> l1, List<Student> l2)
    {
        for(Student s1 : l1)
        {
            for(Student s2 : l2)
            {
                if(s1.getId().equals(s2.getId()))
                {
                    return false;
                }
            }
        }
        
        return true;
    }

    public int[][] getConflictMatrix() {
        return conflictMatrix;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getNrStudents() {
        return nrStudents;
    }

    public int getNrExams() {
        return nrExams;
    }

    public int[] getExamsStart() {
        return examsStart;
    }

    public int[] getExamsEnd() {
        return examsEnd;
    }

    public int[] getExamsDur() {
        return examsDur;
    }
    
}
