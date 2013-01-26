package env.Entities;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class FormPermissions {

	private Integer form_permission_id;
	private Form form;
	private UserType user_type;
	private String permission;
	private Boolean isActive;
	private Timestamp timestamp;
	public Integer getForm_permission_id() {
		return form_permission_id;
	}
	public void setForm_permission_id(Integer form_permission_id) {
		this.form_permission_id = form_permission_id;
	}
	public Form getForm() {
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
	}
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
