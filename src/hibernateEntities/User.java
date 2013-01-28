package hibernateEntities;

import java.sql.Date; 
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class User {

	private Integer user_id;
	private String userName;
	private UserType userTypeId;
	private String userPassword;
	private String salt;

	private String userEmail;
	private Date dateCreated;
	private Boolean isActive;
	

	public User(){}
	public User(String name, String pass) {
		this.userName=name;
		this.userPassword=pass;
		this.isActive=true;
		this.salt="456";
		this.dateCreated = new Date(1);
		this.userTypeId = new UserType();
		this.userTypeId.setUserType("Charity_Administrator");
		this.userTypeId.setUserTypeId(1);
		this.userTypeId.setIsActive(true);
		this.userTypeId.setTimestamp(new Timestamp(1));
	}
	
	
	
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserType getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(UserType userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}

