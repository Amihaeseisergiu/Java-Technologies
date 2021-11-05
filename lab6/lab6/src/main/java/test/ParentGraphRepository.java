package test;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityGraph;

@Stateless
public class ParentGraphRepository extends DataRepository<ParentGraph, Long> implements Serializable {
    
    public ParentGraphRepository()
    {
        super(ParentGraph.class, false);
    }
    
    public void runTest()
    {
        EntityGraph graph = em.getEntityGraph("graph.ParentGraph.children");
        
        em.createQuery("SELECT e FROM ParentGraph e")
        .setHint("javax.persistence.loadgraph", graph)
        .getResultList();
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

