package controller;

import cache.Cached;
import entity.Document;
import exception.DocumentException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.glassfish.jersey.media.multipart.FormDataParam;
import webservice.DocumentWebService;

@Path("/documents")
@RequestScoped
public class DocumentController {
    
    @Inject
    DocumentWebService documentWebService;
    
    @POST
    @Path("/add")
    @RolesAllowed("author")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "406",
        description = "Document error",
        content = @Content(mediaType = "application/json"))
    @APIResponseSchema(value = String.class,
        responseDescription = "Document added succesfully",
        responseCode = "200")
    @Operation(
        summary = "Adds a document",
        description = "Adds a documents, its content and its authors to the database")
    public Response addDocument(
            @FormDataParam("document") String documentJson,
            @FormDataParam("content") InputStream content) throws IOException
    {
        try {
            documentWebService.addDocument(documentJson, content);
        } catch (DocumentException ex) {
            return Response
                .status(Status.NOT_ACCEPTABLE)
                .entity(ex.getMessage())
                .build();
        }
        
        return Response
                .ok("Document added succesfully")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @PUT
    @Path("{id}/update")
    @RolesAllowed("author")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "406",
        description = "Document error",
        content = @Content(mediaType = "application/json"))
    @APIResponseSchema(value = String.class,
        responseDescription = "Document updated succesfully",
        responseCode = "200")
    @Operation(
        summary = "Updates a document",
        description = "Updates a documents, its content and its authors from the database")
    public Response updateDocument(
            @PathParam("id") Long id,
            @FormDataParam("document") String documentJson,
            @FormDataParam("content") InputStream content) throws IOException
    {
        try {
            documentWebService.updateDocument(id, documentJson, content);
        } catch (DocumentException ex) {
            return Response
                .status(Status.NOT_ACCEPTABLE)
                .entity(ex.getMessage())
                .build();
        }
        
        return Response
                .ok("Document updated succesfully")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @DELETE
    @Path("/{id}/delete")
    @RolesAllowed("author")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "406",
        description = "Document error",
        content = @Content(mediaType = "application/json"))
    @APIResponseSchema(value = String.class,
        responseDescription = "Document deleted succesfully",
        responseCode = "200")
    @Operation(
        summary = "Deletes a document",
        description = "Deletes a documents, its content and its authors from the database")
    public Response deleteDocument(@PathParam("id") Long id)
    {
        try {
            documentWebService.deleteDocument(id);
        } catch (DocumentException ex) {
            return Response
                .status(Status.NOT_ACCEPTABLE)
                .entity(ex.getMessage())
                .build();
        }
        
        return Response
                .ok("Document deleted succesfully")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @Cached
    @Asynchronous
    @GET
    @Path("/view")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "406",
        description = "Document error",
        content = @Content(mediaType = "application/json"))
    @APIResponseSchema(value = Document.class,
        responseDescription = "A list of documents",
        responseCode = "200")
    @Operation(
        summary = "Returns a list of documents",
        description = "Returns the list of all documents. If the userId "
            + " parameter is specified then it returns the user's documents")
    public void viewDocuments(@QueryParam("userId") Long id, @Suspended final AsyncResponse async)
    {
        List<Document> documents;
        
        try {
            documents = documentWebService.viewDocuments(id);
            async.resume(Response
                .ok(documents)
                .type(MediaType.APPLICATION_JSON)
                .build());
        } catch (DocumentException ex) {
            async.resume(Response
                .status(Status.NOT_ACCEPTABLE)
                .entity(ex.getMessage())
                .build());
        }
    }
}
