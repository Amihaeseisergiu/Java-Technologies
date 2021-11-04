package solver;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.Exam;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

@Named
@ApplicationScoped
public class ProblemSolver {
    
    public List<Exam> solve(ProblemData pd)
    {
        int[][] conflictMatrix = pd.getConflictMatrix();
        int nrExams = pd.getNrExams();
        int[] examsStart = pd.getExamsStart();
        int[] examsEnd = pd.getExamsEnd();
        
        Model model = new Model("Exam Scheduler");

        IntVar[] day = model.intVarArray("day", nrExams, 0, nrExams - 1);
        IntVar nrDistinct = model.intVar("distinct", 0, nrExams - 1);

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
        
        model.nValues(day, nrDistinct).post();
        model.setObjective(Model.MINIMIZE, nrDistinct);

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
                //System.out.println("day[" + exams.get(i).getName() + "] = " + solution.getIntVal(day[i]));
                exams.get(i).setAssignedDay(solution.getIntVal(day[i]));
            }
        }
        
        return exams;
    }
}
