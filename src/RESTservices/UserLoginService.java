package RESTservices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import RESTdataEntities.UserLoginEntity;
@Path("/UserLoginService")
public class UserLoginService {
	
	@GET
	@Path("/Username/{Username}")
	@Produces({MediaType.APPLICATION_XML})
	public UserLoginEntity loginXML(@PathParam("Username") String Username) {
		
		RESTdataEntities.UserLoginEntity UserResult = new UserLoginEntity();
		org.hibernate.Session HibernateSession = hibernateEntities.HibernateUtil.getSessionFactory().openSession();
		/*try{
			org.hibernate.Query QueryResult = HibernateSession.createQuery("FROM Users");
			java.util.List users = QueryResult.list();
	        for (int i = 0; i < users.size(); i++) {
	        	hibernateEntities.User currentUser = (hibernateEntities.User) users.get(i);
	        	UserResult.setUsername(currentUser.getUserName());
	        	UserResult.setPassword(currentUser.getUserPassword());
	        	UserResult.setSalt(currentUser.getSalt());
	        }
	      }catch (Exception e) {
	         e.printStackTrace(); 
	      }finally {
	    	  HibernateSession.close(); 
	      }*/
		HibernateSession.close();
		return UserResult;
	}
}
