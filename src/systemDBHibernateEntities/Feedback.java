package systemDBHibernateEntities;

import java.sql.Date; 
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import org.hibernate.envers.Audited;

@Entity
@Audited
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
	
	public Integer getFeedbackId() {
		return feedback_id;
	}
	public void setFeedbackId(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	public User getReviewedBy() {
		return reviewed_by;
	}
	public void setReviewedBy(User reviewed_by) {
		this.reviewed_by = reviewed_by;
	}
	
	public Date getReviewedDate() {
		return reviewed_date;
	}
	public void ReviewedDate(Date reviewed_date) {
		this.reviewed_date = reviewed_date;
	}
		
	public Boolean getIsReviewed() {
		return isReviewed;
	}
	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
