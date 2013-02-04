package ConnectionManager;

import java.util.Date;

public class Charity {
private String registrationNo; 
private String charityName;
private String charityDescription;
private String email;
private String phone;
private Boolean isVerified;
private Integer charityID;
private Integer userId;
private String accountNo;
private String connectionString;
private Boolean isActive;
private Date timestamp;
private String addressLine1;
private String addressLine2;
private String location;
private String postcode;
	  
	 

	    public Integer getCharityID() {
	        return charityID;
	    }

	    public void setCharityID(Integer charityID) {
	        this.charityID = charityID;
	    }

	    public String getCharityName() {
	        return charityName;
	    }

	    public void setCharityName(String charityName) {
	        this.charityName = charityName;
	    }

	    public String getCharityDescription() {
	        return charityDescription;
	    }

	    public void setCharityDescription(String charityDescription) {
	        this.charityDescription = charityDescription;
	    }

	    public String getAddressLine1() {
	        return addressLine1;
	    }

	    public void setAddressLine1(String addressLine1) {
	        this.addressLine1 = addressLine1;
	    }

	    public String getAddressLine2() {
	        return addressLine2;
	    }

	    public void setAddressLine2(String addressLine2) {
	        this.addressLine2 = addressLine2;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }
	    
	    public String getPostCode() {
	        return postcode;
	    }

	    public void setPostCode(String postcode) {
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
	    
	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public String getRegistrationNo() {
	        return registrationNo;
	    }

	    public void setRegistrationNo(String registrationNo) {
	        this.registrationNo = registrationNo;
	    }

	    public String getAccountNo() {
	        return accountNo;
	    }

	    public void setAccountNo(String accountNo) {
	        this.accountNo = accountNo;
	    }
	    
	    public String getConnectionString() {
	        return connectionString;
	    }

	    public void setConnectionString(String connectionString) {
	        this.connectionString = connectionString;
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

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(Date timestamp) {
	        this.timestamp = timestamp;
	    }

	    
}
