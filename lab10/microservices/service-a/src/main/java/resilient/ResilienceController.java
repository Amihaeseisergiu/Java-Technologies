package resilient;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/resilience")
@ApplicationScoped
public class ResilienceController {

    @Fallback(fallbackMethod = "fallback")
    @Retry(maxRetries = 2, delay = 200, jitter = 50)
    @Timeout(500)
    @GET
    @Path("/fallback")
    public String checkFallback() {
        int wait = new Random().nextInt(1000);
        
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            //
        }
        return "Fallback function returned normally";
    }

    public String fallback() {
        return "Fallback answer due to timeout";
    }
    
    @CircuitBreaker(successThreshold = 10,
            requestVolumeThreshold = 4,
            failureRatio=0.75,
            delay = 1000)
    @GET
    @Path("/circuitbreaker")
    public String checkCircuitBreaker()
    {
        return "Circuit Breaker function returned normally";
    }
    
    @Bulkhead(5)
    @GET
    @Path("/bulkhead/semaphore")
    public String checkBulkheadSemaphore()
    {
        return "Bulkhead Semaphore function returned normally";
    }
    
    @Asynchronous
    @Bulkhead(value = 5, waitingTaskQueue = 8)
    @GET
    @Path("/bulkhead/threadpool")
    public CompletionStage<String> checkBulkheadThreadPool()
    {
        return CompletableFuture
                .completedFuture("Bulkhead Thread Pool"
                        + " function returned normally");
    }
}
