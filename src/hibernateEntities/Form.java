package hibernateEntities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;

import java.util.Set;
import java.util.HashSet;

@Entity
@Audited
@XmlRootElement(name = "form")
public class Form {
	private Integer formId;
	private FormType formTypeId;
	private String formName;
	private Date dateCreated;
	private String url;
	private Boolean isActive;
	private Timestamp timestamp;
	private Set<FormFields> fields = new HashSet<FormFields>();
	private Set<FormPermissions> permissions = new HashSet<FormPermissions>();	
	
	public Form(){}
	public Form(FormType ft) {
		this.formTypeId = ft;
		this.dateCreated = new Date(Calendar.DATE);
		this.setIsActive(true);
		this.setTimestamp(new Timestamp(Calendar.DATE));
	}
	
	
	@XmlElement
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	@XmlElement(name = "formType")
	public FormType getFormTypeId() {
		return formTypeId;
	}
	public void setFormTypeId(FormType formTypeId) {
		this.formTypeId = formTypeId;
	}
	@XmlElement
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	@XmlElement
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@XmlElement
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@XmlElement
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@XmlElement
	@JsonIgnore
	public Set<FormFields> getFields() {
		return fields;
	}
	public void setFields(Set<FormFields> fields) {
		this.fields = fields;
	}
	@XmlElement
	public Set<FormPermissions> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<FormPermissions> permissions) {
		this.permissions = permissions;
	}
	
}
