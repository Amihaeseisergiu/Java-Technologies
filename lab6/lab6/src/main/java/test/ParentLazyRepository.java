package test;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Stateless
public class ParentLazyRepository extends DataRepository<ParentLazy, Long> implements Serializable {
    
    public ParentLazyRepository()
    {
        super(ParentLazy.class, false);
    }
    
    public void runTest()
    {
        findAll();
    }
    
    @AroundInvoke
    public Object log (InvocationContext ctx) throws Exception {
        String className = ctx.getTarget().getClass().getName();
        String methodName = ctx.getMethod().getName();
        
        String target = className + "." + methodName + "()";
        long t1 = System.currentTimeMillis();
        
        try {
            return ctx.proceed();
        } catch(Exception e) {
            throw e;
        } finally {
            long t2 = System.currentTimeMillis();
            System.out.println(target + " took " + (t2-t1) + "ms to execute");
        }
    }
}
