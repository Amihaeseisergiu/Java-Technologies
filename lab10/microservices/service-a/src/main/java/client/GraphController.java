package client;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/graphs")
@ApplicationScoped
public class GraphController {

    @Inject
    @RestClient
    GraphService graphService;
    
    @GET
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> graph() {
        CompletionStage<List<String>> result = graphService.order();
        
        try {
            return result.toCompletableFuture().get();
        } catch (InterruptedException | ExecutionException ex) {
            return Arrays.asList(ex.getMessage());
        }
    }
}