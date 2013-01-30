package charityDBHibernateEntities;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class UserType {

	private Integer userTypeId;
	private String userType;
	private String description;
	private Boolean isActive;
	private Timestamp timestamp;
//	private User user;
	
/*	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
