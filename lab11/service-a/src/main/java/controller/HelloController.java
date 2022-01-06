package controller;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import repository.TimeFrameRepository;

@Path("/hello")
@Singleton
public class HelloController {
    
    @Inject
    TimeFrameRepository timeFrameRepository;

    @GET
    public String sayHello() {
        return timeFrameRepository.findById((long) 1).getStartingTime().toString();
    }
}
