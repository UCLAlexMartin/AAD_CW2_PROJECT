package RESTClient;


import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import systemDBHibernateEntities.Charity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class CharityClient {
	public static Integer addCharity(Charity charity){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		GenericEntity<Charity> ch = new GenericEntity<Charity>(charity){};
		ClientResponse clientresponse = 
				client.resource("http://localhost:8080/CharityWare/REST/charityService").path("/addCharity/")
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).entity(ch).post(ClientResponse.class);
		return clientresponse.getStatus();
	}

}
