package hibernateEntities;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class MailingGroup {
		private Integer mailing_group_id;
		private String mailing_group;
		private Timestamp timestamp;
		
		public MailingGroup(){}
		
		public Integer getMailing_group_id() {
			return mailing_group_id;
		}

		public void setMailing_group_id(Integer mailing_group_id) {
			this.mailing_group_id = mailing_group_id;
		}

		public String getMailing_group() {
			return mailing_group;
		}

		public void setMailing_group(String mailing_group) {
			this.mailing_group = mailing_group;
		}

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}

		public MailingGroup ( String mailing_group) {
			this.mailing_group=mailing_group;
			this.timestamp = new Timestamp(Calendar.DATE);
		}
}
