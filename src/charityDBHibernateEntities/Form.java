package charityDBHibernateEntities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Form {

	
	private Integer formId;
	private FormType formTypeId;
	private String formName;
	private Date dateCreated;
	private String url;
	private Boolean isActive;
	private Timestamp timestamp;
	private Set<FormFields> fields;
	private Set<FormPermissions> permissions;
	
	
	public Form(){}
	public Form(FormType ft) {
		this.formTypeId = ft;
		this.dateCreated = new Date(Calendar.DATE);
		this.setIsActive(true);
		this.setTimestamp(new Timestamp(Calendar.DATE));
	}
	
	

	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public FormType getFormTypeId() {
		return formTypeId;
	}
	public void setFormTypeId(FormType formTypeId) {
		this.formTypeId = formTypeId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Set<FormFields> getFields() {
		return fields;
	}
	public void setFields(Set<FormFields> fields) {
		this.fields = fields;
	}
	public Set<FormPermissions> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<FormPermissions> permissions) {
		this.permissions = permissions;
	}
	
}
