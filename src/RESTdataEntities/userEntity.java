package RESTdataEntities;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rootUser")
public class userEntity {
	
	
	private int User_Id;
	String name;
	private int User_Type_Id;
	private String password;
	private String userEmail;
	private Date dateCreated;
	private boolean isActive;
	
	//might need to use the java.sql.date for date retrieval
	private Date timeStamp;
	
	
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(int user_Id) {
		this.User_Id = user_Id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_Type_Id() {
		return User_Type_Id;
	}

	public void setUser_Type_Id(int user_Type_Id) {
		User_Type_Id = user_Type_Id;
	}
	
}

//User_Id INT(11)
//Username VARCHAR(20) 
//User_Type_Id INT(11) 
//User_Password VARCHAR(250) 
//User_Email VARCHAR(250) 
//Date_Created DATETIME
//isActive BIT(1)
//Timestamp TIMESTAMP


