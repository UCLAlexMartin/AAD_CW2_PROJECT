package RESTservices;

import hibernateManagers.FieldSelectionManager;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/fieldSelectionService")
public class FieldSelectionService {
	@GET
	@Path("/json/selectionValues/{field_id}")
	@Produces("application/json")
	public ArrayList<String> getSelectionValues(@PathParam("field_id")Integer field_id){
		ArrayList<String> values = FieldSelectionManager.getValues(field_id);
		return values;    
	}
}
