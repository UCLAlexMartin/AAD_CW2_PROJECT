package systemDBHibernateEntities;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class Charity {
	
	private Integer charity_id;
	private String charity_name;
	private String charity_description;
	private String address_line1;
	private String address_line2;
	private String location;
	private String postcode;
	private String email;
	private String phone;
	private User user_id;
	private String registration_no;
	private String account_no;
	private String connection_string;
	private Boolean isVerified;	
	private Boolean isActive;
	private Timestamp timestamp;
	
	public Charity(){}
	
	public Charity(String Charity_Name, String Charity_Description,String Address_Line1,String Address_Line2,String Location,String Postcode,String Email,String Phone,String Registration_No){
		this.charity_name = Charity_Name;
		this.charity_description = Charity_Description;
		this.address_line1 = Address_Line1;
		this.address_line2 = Address_Line2;
		this.location = Location;
		this.postcode = Postcode;
		this.email = Email;
		this.phone = Phone;
		this.registration_no = Registration_No;		
		this.timestamp = new Timestamp(Calendar.DATE);
	}
	
	public Integer getCharityId() {
		return charity_id;
	}
	public void setCharityId(Integer charity_id) {
		this.charity_id = charity_id;
	}
	
	public String getCharityName() {
		return charity_name;
	}
	public void setCharityName(String charity_name) {
		this.charity_name = charity_name;
	}	
	
	public String getCharityDescription() {
		return charity_description;
	}
	public void setCharityDescription(String charity_description) {
		this.charity_description = charity_description;
	}
	
	public String getAddressLine1() {
		return address_line1;
	}
	public void setAddressLine1(String address_line1) {
		this.address_line1 = address_line1;
	}
	
	public String getAddressLine2() {
		return address_line2;
	}
	public void setAddressLine2(String address_line2) {
		this.address_line2 = address_line2;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
			
	public User getUserId() {
		return user_id;
	}
	public void setUserId(User user_id) {
		this.user_id = user_id;
	}
		
	public String getRegistrationNo() {
		return registration_no;
	}
	public void setRegistrationNo(String registration_no) {
		this.registration_no = registration_no;
	}
	
	public String getAccountNo() {
		return account_no;
	}
	public void setAccountNo(String account_no) {
		this.account_no = account_no;
	}
	
	public String getConnectionString() {
		return connection_string;
	}
	public void setConnectionString(String connection_string) {
		this.connection_string = connection_string;
	}
			
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
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
		
}

