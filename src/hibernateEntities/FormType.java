package hibernateEntities;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "formType")
public class FormType {
	
	private Integer form_type_id;
	private String form_type;
	private Boolean isActive;
	@XmlElement
	public Integer getForm_type_id() {
		return form_type_id;
	}
	public void setForm_type_id(Integer form_type_id) {
		this.form_type_id = form_type_id;
	}
	@XmlElement
	public String getForm_type() {
		return form_type;
	}
	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
