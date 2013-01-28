/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservices;

import RESTdataEntities.UserTypeJpaController;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import RESTdataEntities.UserType;
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
@Path("restdataentities.usertype")
public class UserTypeRESTFacade {

    private EntityManagerFactory getEntityManagerFactory() throws NamingException {
        return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
    }

    private UserTypeJpaController getJpaController() {
        try {
            UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            return new UserTypeJpaController(utx, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public UserTypeRESTFacade() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response create(UserType entity) {
        try {
            getJpaController().create(entity);
            return Response.created(URI.create(entity.getUserTypeId().toString())).build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Response edit(UserType entity) {
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
    public UserType find(@PathParam("id") Integer id) {
        return getJpaController().findUserType(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<UserType> findAll() {
        return getJpaController().findUserTypeEntities();
    }

    @GET
    @Path("{max}/{first}")
    @Produces({"application/xml", "application/json"})
    public List<UserType> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return getJpaController().findUserTypeEntities(max, first);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(getJpaController().getUserTypeCount());
    }
    
}
