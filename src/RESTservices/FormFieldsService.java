package RESTservices;

import hibernateManagers.FormFieldsManager;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;


@Path("/formFieldsService")
public class FormFieldsService {
	
	@GET
	@Path("/json/formFields/{formId}")
	@Produces("application/json")
	public GenericEntity<Map<Integer,List<String>>> JSONformFieldsretrieve(@PathParam("formId")Integer formId){
		Map<Integer,List<String>> map= FormFieldsManager.retrieve(formId);
		GenericEntity<Map<Integer, List<String>>> entity = new GenericEntity<Map<Integer, List<String>>>(map){};
		return entity;
	}
	
	/*@GET
	@Path("/formFields/{formId}")
	@Produces(MediaType.APPLICATION_XML)
	public JResponse<GenericEntity<Map<Integer,List<String>>>> formFieldsretrieve(@PathParam("formId")Integer formId){
		Map<Integer,List<String>> map= FormFieldsManager.retrieve(formId);
		GenericEntity<Map<Integer, List<String>>> entity = new GenericEntity<Map<Integer, List<String>>>(map){};
		return JResponse.ok(entity).build();
		//return entity;
	}*/

}
