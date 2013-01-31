package RESTservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hibernateEntities.Event;
import hibernateManagers.EventManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/eventService")
public class EventService {
	 @GET
	 @Path("/json/events")
	 @Produces("application/json")
	 public Map<Integer,ArrayList<String>> geEventsJSON(){
	    	return EventManager.getEvents();       
	    }
}
