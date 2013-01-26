package env.Entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class FilledForm {
	
	private Integer filled_form_id;
	private Form form_id;
	private Set<FormFields> field_id;
	private String value;
	private User user_id;
	private Integer record_id;
	private Boolean isActive;
	private Timestamp timestamp;
	public Integer getFilled_form_id() {
		return filled_form_id;
	}
	public void setFilled_form_id(Integer filled_form_id) {
		this.filled_form_id = filled_form_id;
	}
	public Form getForm_id() {
		return form_id;
	}
	public void setForm_id(Form form_id) {
		this.form_id = form_id;
	}
	public Set<FormFields> getField_id() {
		return field_id;
	}
	public void setField_id(Set<FormFields> field_id) {
		this.field_id = field_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
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
