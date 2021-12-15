package controller;

import entity.Document;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import webservice.GraphWebService;

@Path("/graphs")
@RequestScoped
public class GraphController {
    
    @Inject
    GraphWebService graphWebService;
    
    @GET
    @Path("/order")
    @Asynchronous
    @Produces(MediaType.APPLICATION_JSON)
    public void order(@Suspended final AsyncResponse async)
    {
        Client client = ClientBuilder.newClient();
        
        WebTarget viewDocuments = client
                .target("http://localhost:8080/lab10/resources/documents/view");
        
        Future<List<Document>> documents = viewDocuments
                .request(MediaType.APPLICATION_JSON)
                .async()
                .get(new InvocationCallback<List<Document>>() {
                    
                    @Override
                    public void completed(List<Document> documents) {
                        
                        List<String> sorted = graphWebService
                                .sortTopologically(documents);
                        
                        if(sorted == null)
                        {
                            async.resume(Response
                                .status(Response.Status.CONFLICT)
                                .entity("The graph contains a cycle")
                                .build());
                        }
                        
                        async.resume(Response
                            .ok(sorted)
                            .type(MediaType.APPLICATION_JSON)
                            .build());
                    }
                    
                    @Override
                    public void failed(Throwable throwable) {
                        throwable.printStackTrace();
                        async.resume(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("An error occured")
                            .build());
                    }
                });
    }
}
