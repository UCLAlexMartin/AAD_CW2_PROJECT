package hibernateEntities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class MailingList {

	//private MailingGroup mailing_group;
	//private Set<User> users;
	private Timestamp timestamp;
	private MailingListPK pk;
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public MailingListPK getPk() {
		return pk;
	}
	public void setPk(MailingListPK pk) {
		this.pk = pk;
	}
	public MailingList() {
//		this.mailing_group = new MailingGroup();
		this.timestamp= new Timestamp(Calendar.DATE);

	}
	public MailingList(MailingGroup group, User u){
//		this.mailing_group=group;
//		this.users=users;
		this.pk = new MailingListPK();
		this.pk.setMailing_group(group);
		this.pk.setUser(u);
		this.timestamp= new Timestamp(Calendar.DATE);
	}
/*	public MailingGroup getMailing_group() {
		return mailing_group;
	}
	public void setMailing_group(MailingGroup mailing_group) {
		this.mailing_group = mailing_group;
	}*/
	/*public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
