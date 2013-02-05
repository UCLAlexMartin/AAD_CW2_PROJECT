package RESTservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hibernateEntities.User;
import hibernateManagers.CharityLoginManager;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

@Path("/charityLogin")
public class CharityLogin {
	

	@GET
	@Path("/CharityDB/{DBConfigPath}/userName/{username}")
	@Produces("application/json")
	public User getUsersFromName(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("username")String username){
		
		System.out.println("Get Request recieved");
		ArrayList<User> holder = CharityLoginManager.getUsers(username, DBConfigPath);
		System.out.println("User array list populated");

		return holder.get(0);    
	}
	
}
