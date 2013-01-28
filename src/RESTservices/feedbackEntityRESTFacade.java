/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservices;

import RESTdataEntities.feedbackEntityJpaController;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import RESTdataEntities.feedbackEntity;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author cchen
 */
@Path("restdataentities.feedbackentity")
public class feedbackEntityRESTFacade {

    private EntityManagerFactory getEntityManagerFactory() throws NamingException {
        return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
    }

    private feedbackEntityJpaController getJpaController() {
        try {
            UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            return new feedbackEntityJpaController(utx, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public feedbackEntityRESTFacade() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response create(feedbackEntity entity) {
        try {
            getJpaController().create(entity);
            return Response.created(URI.create(entity.getFeedbackId().toString())).build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Response edit(feedbackEntity entity) {
        try {
            getJpaController().edit(entity);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            getJpaController().destroy(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public feedbackEntity find(@PathParam("id") Integer id) {
        return getJpaController().findfeedbackEntity(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<feedbackEntity> findAll() {
        return getJpaController().findfeedbackEntityEntities();
    }

    @GET
    @Path("{max}/{first}")
    @Produces({"application/xml", "application/json"})
    public List<feedbackEntity> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return getJpaController().findfeedbackEntityEntities(max, first);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(getJpaController().getfeedbackEntityCount());
    }
    
}
