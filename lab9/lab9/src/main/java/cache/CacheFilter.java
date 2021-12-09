package cache;

import entity.Document;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Cached
public class CacheFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    Cache cache;
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        
        Long userId = getIdFromRequest(request);
        
        if(cache.exists(userId))
        {
            request.abortWith(
                    Response.ok(cache.getDocuments(userId))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        
        Long userId = getIdFromRequest(request);
        
        if(!cache.exists(userId))
        {
            cache.addDocuments(userId, (List<Document>) response.getEntity());
        }
    }
    
    public Long getIdFromRequest(ContainerRequestContext request)
    {
        String userIdString = request.getUriInfo().getQueryParameters().getFirst("userId");
        return userIdString != null ? Long.valueOf(userIdString) : null;
    }
}
