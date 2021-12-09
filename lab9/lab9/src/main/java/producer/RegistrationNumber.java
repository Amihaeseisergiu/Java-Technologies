package producer;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import qualifier.Random;

@ApplicationScoped
public class RegistrationNumber {
    
    @Produces
    @Random
    String registrationNumber()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
