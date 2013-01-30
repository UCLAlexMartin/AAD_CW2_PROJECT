package RESTservices;

import java.util.ArrayList;
import java.util.List;

import hibernateManagers.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

import charityDBHibernateEntities.User;


@Path("/userService")
public class UserService {
	
	@GET
	@Path("/users/")
	@Produces("application/xml")
	public List<User> listUsers(){
		return new ArrayList<User>(UserManager.retrieve());
	}
	    
	@GET
	@Path("/user/{userid}")
	@Produces("application/xml")
	public User getUser(@PathParam("userid")Integer userid){
		return UserManager.getUser(userid);       
	}
	
	@GET
	@Path("/userName/{username}")
	@Produces("application/json")
	public User getUsersFromName(@PathParam("username")String username){
		ArrayList<User> holder = UserManager.getUsersFromName(username);
		return holder.get(0);    
	}
	
	
    @GET
    @Path("/json/users/")
    @Produces("application/json")
    public List<User> listUsersJSON(){
    	return new ArrayList<User>(UserManager.retrieve());
    	}

    @GET
    @Path("/json/user/{userid}")
    @Produces("application/json")
    public User getUserJSON(@PathParam("userid")Integer userid){
    	return UserManager.getUser(userid);       
    }
    
    @POST
	@Path("/user/{name}/{pass}")
    public void addUser(@PathParam("name") String name,@PathParam("pass") String pass){
    	UserManager.addUserSample(name, pass);
    }
}