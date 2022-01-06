package client;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import repository.TimeFrameRepository;

@Path("/client/service")
public class ServiceController {

    @Inject
    TimeFrameRepository timeFrameRepository;
    
    @GET
    @Path("/test")
    public String test()
    {
        return timeFrameRepository.findById((long) 1).getStartingTime().toString();
    }
    
    @GET
    @Path("/{parameter}")
    public String doSomething(@PathParam("parameter") String parameter) {
        return String.format("Processed parameter value '%s'", parameter);
    }
}
