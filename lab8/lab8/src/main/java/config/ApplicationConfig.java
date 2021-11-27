package config;

import cache.CacheFilter;
import controller.DocumentController;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("resources")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(JacksonFeature.class);
        classes.add(DocumentController.class);
        classes.add(MultiPartFeature.class);
        classes.add(CacheFilter.class);
        return classes;
    }
}
