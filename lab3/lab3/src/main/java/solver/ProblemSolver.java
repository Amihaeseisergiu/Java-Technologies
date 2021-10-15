package solver;

import java.util.List;
import model.Exam;
import model.Student;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class ProblemSolver {
    
    public static List<Exam> solve()
    {
        ProblemData pd = new ProblemData();
        int[][] conflictMatrix = pd.getConflictMatrix();
        int nrExams = pd.getNrExams();
        int[] examsStart = pd.getExamsStart();
        int[] examsEnd = pd.getExamsEnd();
        
        Model model = new Model("Exam Scheduler");

        IntVar[] day = model.intVarArray("day", nrExams, 0, nrExams - 1);
        IntVar totDay = model.intVar("Total Day Score", 0, (nrExams * (nrExams - 1)) / 2);

        for (int i = 0; i < nrExams; i++)
        {
            for (int j = 0; j < nrExams; j++)
            {
                if(i != j)
                {
                    //no overlap ((examsStart[i] <= examsEnd[j])  &&  (examsEnd[i] >= examsStart[j]))
                    if(conflictMatrix[i][j] == 1)
                    {
                        model.arithm(day[i], "!=", day[j]).post();
                    }
                }
            }
        }
        
        model.sum(day, "=", totDay).post();
        model.setObjective(Model.MINIMIZE, totDay);

        Solver solver = model.getSolver();
        Solution solution = new Solution(model);

        while(solver.solve()){
            solution.record();
        }
        
        List<Exam> exams = null;
        
        if(solution.exists())
        {
            exams = pd.getExams();
            
            for(int i = 0; i < nrExams; i++)
            {
                exams.get(i).setDay(solution.getIntVal(day[i]));
            }
        }
        
        return exams;
    }
}
