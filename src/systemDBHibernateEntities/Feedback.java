package systemDBHibernateEntities;

import java.sql.Date; 
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "feedback")
public class Feedback {
	
	private Integer feedback_id;
	private String name;
	private String email;
	private String comment;
	private User reviewed_by;
	private Date reviewed_date;
	private Boolean isReviewed;
	private Timestamp timestamp;
	
	public Feedback(){}
	public Feedback(String Name, String Email,String Comment){
		this.name = Name;
		this.email = Email;
		this.comment = Comment;
		this.timestamp = new Timestamp(Calendar.DATE);
	}
	
	@XmlElement
	public Integer getFeedback_id() {
		return this.feedback_id;
	}
	public void setFeedback_id(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement	
	public User getReviewed_by() {
		return reviewed_by;
	}
	public void setReviewed_by(User reviewed_by) {
		this.reviewed_by = reviewed_by;
	}
	@XmlElement
	public Date getReviewed_date() {
		return reviewed_date;
	}
	public void setReviewed_date(Date reviewed_date) {
		this.reviewed_date = reviewed_date;
	}
	@XmlElement	
	public Boolean getIsReviewed() {
		return isReviewed;
	}
	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}
	@XmlElement
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

	
	
}
