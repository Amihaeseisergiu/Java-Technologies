package interceptor;

import entity.Document;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Submission
@Interceptor
public class SubmissionLogInterceptor implements Serializable {
    
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        
        try {
            File submissions = new File("D:\\Facultate\\Tehnologii Java\\lab7\\submissions.log");
            if(!submissions.exists())
            {
                submissions.createNewFile();
            }
            
            try(FileWriter fileWriter = new FileWriter(submissions, true))
            {
                fileWriter.write(ctx.getMethod().getName() + "\n");
            }
          } catch (IOException e) {
            System.out.println("Error writing to file");
          }
        
        return ctx.proceed();
    }
}
