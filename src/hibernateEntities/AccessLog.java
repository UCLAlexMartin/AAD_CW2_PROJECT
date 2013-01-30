package hibernateEntities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class AccessLog {

	private Integer access_log_id;
	private Form form;
	private Set<FormFields> fields;
	private User user;
	private Date access_start_time;
	private Date access_end_time;
	private String device;
	private String location;
	
	public AccessLog() {}
	public AccessLog(Date start, User user){
		this.timestamp = new Timestamp(Calendar.DATE);
		this.access_start_time=start;
		this.user=user;
	}
	public Integer getAccess_log_id() {
		return access_log_id;
	}
	public void setAccess_log_id(Integer access_log_id) {
		this.access_log_id = access_log_id;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public Set<FormFields> getFields() {
		return fields;
	}
	public void setFields(Set<FormFields> fields) {
		this.fields = fields;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getAccess_start_time() {
		return access_start_time;
	}
	public void setAccess_start_time(Date access_start_time) {
		this.access_start_time = access_start_time;
	}
	public Date getAccess_end_time() {
		return access_end_time;
	}
	public void setAccess_end_time(Date access_end_time) {
		this.access_end_time = access_end_time;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	private Timestamp timestamp;
	
}
