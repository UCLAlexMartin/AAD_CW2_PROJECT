package hibernateManagers;

import java.util.ArrayList;

import charityDBHibernateEntities.User;



public class UserManager {
	
	public static ArrayList<User> retrieve(){
		ArrayList<User> users = (ArrayList<User>) ConnectionManager.getTable("User");
		return users;
	}
	
	public static Integer addUserSample (String name,String pass) {
		
		User user = new User (name,pass);
		return (Integer) ConnectionManager.transaction("save",user);
		
	}
	
	public static User getUser(Integer id){
		User user = (User)ConnectionManager.get(User.class,id);
		return user;
	}
	
	public static ArrayList<User> getUsersFromName(String name){
		ArrayList<User> user = (ArrayList<User>)ConnectionManager.getTable("hibernateEntities.User where userName = '" + name+"'");
		return user;
	}
	
	public static void updateUserPassword (Integer userId,String userPassword ) {
		
		User user = (User) ConnectionManager.get(User.class, userId);
		user.setUserPassword(userPassword);
		ConnectionManager.transaction("update",user);
	}
}
