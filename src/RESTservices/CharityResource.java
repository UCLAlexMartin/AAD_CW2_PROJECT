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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import ConnectionManager.DatabaseManager;
import RESTdataEntities.Charity;

/**
 * REST Web Service
 *
 * @author appleapple
 */
@Path("/charity")
public class CharityResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CharityResource
     */
    public CharityResource() {
    }


    /**
     * Retrieves representation of an instance of RESTservices.CharityResource
     * @return an instance of RESTservices.Charity
     */
    @Path("/charities/")
    @GET 
    @Produces("application/xml")
    public List<Charity> getCharities() {
        //TODO return proper representation object

        List<Charity> chs = new ArrayList<Charity>();
        try {
        chs = DatabaseManager.readCharityTable();
        }
        catch (Exception e)
        {
            
        }
        return chs;
    }

    /**
     * PUT method for updating or creating an instance of CharityResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/xml")
    public Charity postCharity(Charity ch) {
     try{
            DatabaseManager.addCharity(ch);
        }
        catch (Exception e)
        {
            ch = null;
        }       
        return ch;
    }
    
    
    @Path("/{charityid}")
    @GET
    @Produces("application/xml")
    public Charity getCharity(@PathParam("charityid") 
    int charityid) {
        //TODO return proper representation object

        Charity ch = new Charity();
        try {
        ch = DatabaseManager.getCharity(charityid);
        }
        catch (Exception e)
        {
            ch = null;
            
        }
        return ch;
    }
    /**
     * PUT method for updating or creating an instance of CharityResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */

}
