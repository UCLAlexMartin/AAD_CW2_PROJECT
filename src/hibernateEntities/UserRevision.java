package hibernateEntities;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(UserListener.class)
public class UserRevision extends DefaultRevisionEntity {
	
	 private String username;
	 
	     public String getUsername() { return username; }
	
	     public void setUsername(String username) { this.username = username; }

}
