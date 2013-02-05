package hibernateManagers;

import hibernateEntities.Form;
import hibernateEntities.FormFields;
import hibernateEntities.FormPermissions;
import hibernateEntities.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CharityLoginManager {

	public static ArrayList<User> getUsers(String name, String DBConfname){
		ConnectionManagerV2 conn = new ConnectionManagerV2();
		conn.setDBConfname(DBConfname);
		ArrayList<User> user = (ArrayList<User>)conn.getTable("User where userName = '" + name+"'");
		return user;
	}
	
}
