package RESTservices;

import hibernateManagers.FilledFormManager;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;

@Path("/filledFormService")
public class FilledFormService {
	
	@GET
    @Path("/json/filledforms/records")
    @Produces("application/json")
    public GenericEntity<String> getRecordsDataJSON(){
    	GenericEntity<String> entity = new GenericEntity<String>(FilledFormManager.getRecordsData()){};
    	return entity;       
    }

}
