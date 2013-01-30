package hibernateEntities;


import java.sql.Timestamp;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class FormPermissions {

/*	private Form form;
	private UserType user_type;*/
	private	FormPermissionsPK pk;
	public FormPermissionsPK getPk() {
		return pk;
	}
	public void setPk(FormPermissionsPK pk) {
		this.pk = pk;
	}
	private String permission;
	private Boolean isActive;
	private Timestamp timestamp;
	
	/*public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public UserType getUser_type() {
		return user_type;
	}
	public void setUser_type(UserType user_type) {
		this.user_type = user_type;
	}*/
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
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
