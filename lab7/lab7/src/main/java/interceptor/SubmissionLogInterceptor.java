package interceptor;

import auth.AuthenticationManager;
import entity.Document;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Submission
@Interceptor
public class SubmissionLogInterceptor implements Serializable {
    
    @Inject
    AuthenticationManager authenticationManager;
    
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        
        Document document = (Document) ctx.getParameters()[0];
        
        try {
            return ctx.proceed();
        } catch(Exception e) {
            throw e;
        } finally {
            try {
                File submissions = new File("D:\\Facultate\\Tehnologii Java\\lab7\\submissions.log");
                if(!submissions.exists())
                {
                    submissions.createNewFile();
                }

                try(FileWriter fileWriter = new FileWriter(submissions, true))
                {
                    fileWriter.write(
                            "[" + document.getRegistrationNumber() + "] " +
                            "User [" + authenticationManager.getUser().getUsername() + 
                            "] submitted the document [" + document.getName() + "] having the authors [" +
                            document.getAuthors().stream().map(a -> a.getUsername()).collect(Collectors.joining(",")) +
                            "]\n");
                }
            } catch (IOException e) {
              System.out.println("Error writing to file");
            }
        }
    }
}
