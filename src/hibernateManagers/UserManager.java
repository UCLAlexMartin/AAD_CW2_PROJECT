package hibernateManagers;

import systemDBHibernateEntities.User;

import java.util.ArrayList;




public class UserManager {
	/*
	public static ArrayList<User> retrieve(){
		ArrayList<User> users = (ArrayList<User>) ConnectionManager.charityGetTable("User");
		return users;
	}
	
	public static Integer addUserSample (String name,String pass) {
		
		User user = new User (name,pass);
		return (Integer) ConnectionManager.charityTransaction("save",user);
		
	}
	
	public static User getUser(Integer id){
		User user = (User)ConnectionManager.charityGet(User.class,id);
		return user;
	}
	*/
	public static ArrayList<User> getUsersFromName(String name){
		System.out.println("get users from name started");
		ArrayList<User> user = (ArrayList<User>)ConnectionManager.systemGetTable("systemDBHibernateEntities.User where userName = '" + name+"'");
		System.out.println("user array list populated");
		return user;
	}
	/*
	public static void updateUserPassword (Integer userId,String userPassword ) {
		
		User user = (User) ConnectionManager.charityGet(User.class, userId);
		user.setUserPassword(userPassword);
		ConnectionManager.charityTransaction("update",user);
	}*/
}
