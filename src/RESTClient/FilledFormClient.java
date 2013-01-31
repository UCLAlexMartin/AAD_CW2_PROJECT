package RESTClient;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class FilledFormClient {
	
	public static String getRecordsData(){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource("http://localhost:8080/CharityWare/REST/filledFormService").path("/json/filledforms/records").
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<String>(){});
	}

}
