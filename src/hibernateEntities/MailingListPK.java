package hibernateEntities;

import java.io.Serializable;
import java.util.Set;

public class MailingListPK implements Serializable {

	private MailingGroup mailing_group;
//	private Set<User> Users;
	private User user;
	public MailingGroup getMailing_group() {
		return mailing_group;
	}
	public void setMailing_group(MailingGroup mailing_group) {
		this.mailing_group = mailing_group;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
/*	public Set<User> getUsers() {
		return Users;
	}
	public void setUsers(Set<User> users) {
		Users = users;
	}
	*/
}
