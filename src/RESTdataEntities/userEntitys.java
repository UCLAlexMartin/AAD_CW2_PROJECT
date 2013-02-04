package RESTdataEntities;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;


@XmlRootElement(namespace = "userEntitiys")
public class userEntitys {
	
	
	@XmlElementWrapper(name = "WrapperuserList")
	@XmlElement(name = "user")  
	//private ArrayList<userEntity> Users;
	private ArrayList<userEntity> Users;
	
	public void setUsers(ArrayList<userEntity> Users) {
	    this.Users = Users;
	  }

	  public ArrayList<userEntity> getUsers() {
	    return this.Users;
	  }
}
