package env.Entities;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class FormType {
	
	private Integer form_type_id;
	private String form_type;
	private Boolean isActive;
	public Integer getForm_type_id() {
		return form_type_id;
	}
	public void setForm_type_id(Integer form_type_id) {
		this.form_type_id = form_type_id;
	}
	public String getForm_type() {
		return form_type;
	}
	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

}
