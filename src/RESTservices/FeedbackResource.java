/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import RESTdataEntities.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import ConnectionManager.DatabaseManager;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author cchen
 */
@Path("/")
public class FeedbackResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FeedbackResource
     */
    public FeedbackResource() {
    }

    /**
     * Retrieves representation of an instance of RESTservices.FeedbackResource
     * @return an instance of feedbackEntity
     */
    @Path("/feedbacks")
    @GET
    @Produces("application/xml")
    public List<feedbackEntity> getFeedbacks() {
        //TODO return proper representation object
        List<feedbackEntity> fds = new ArrayList();
        try{
            fds = DatabaseManager.readFeedbacks();
        }
        catch (Exception e)
        {
            fds = null;
        }
        return fds;
        
    }

    @Path("/feedbacks/{feedbackid}")
    @GET
    @Produces("application/xml")
    public feedbackEntity getSingleFeedback(@PathParam("feedbackid") 
    int feedbackid) throws Exception {
        //TODO return proper representation object
        feedbackEntity fd = new feedbackEntity();
        try{
            fd = DatabaseManager.readSingleFeedback(feedbackid);
        }
        catch (Exception e)
        {
            fd = null;
        }
        return fd;
        
    }
        
    /**
     * POST method for Creating an instance of FeedbackResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @Path("/feedbacks")
    @POST
    @Consumes("application/xml")
    public feedbackEntity addFeedback(feedbackEntity fd) throws Exception {
        try{
            DatabaseManager.addFeedback(fd);
        }
        catch (Exception e)
        {
            fd = null;
        }       
        return fd;
    }
}