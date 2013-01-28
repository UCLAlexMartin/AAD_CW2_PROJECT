/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservices;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appleapple
 */
@Entity
@Table(name = "Charity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Charity.findAll", query = "SELECT c FROM Charity c"),
    @NamedQuery(name = "Charity.findByCharityID", query = "SELECT c FROM Charity c WHERE c.charityID = :charityID"),
    @NamedQuery(name = "Charity.findByCharityName", query = "SELECT c FROM Charity c WHERE c.charityName = :charityName"),
    @NamedQuery(name = "Charity.findByCharityDescription", query = "SELECT c FROM Charity c WHERE c.charityDescription = :charityDescription"),
    @NamedQuery(name = "Charity.findByAddressLine1", query = "SELECT c FROM Charity c WHERE c.addressLine1 = :addressLine1"),
    @NamedQuery(name = "Charity.findByAddressLine2", query = "SELECT c FROM Charity c WHERE c.addressLine2 = :addressLine2"),
    @NamedQuery(name = "Charity.findByLocation", query = "SELECT c FROM Charity c WHERE c.location = :location"),
    @NamedQuery(name = "Charity.findByPostCode", query = "SELECT c FROM Charity c WHERE c.postcode = :postcode"),
    @NamedQuery(name = "Charity.findByEmail", query = "SELECT c FROM Charity c WHERE c.email = :email"),
    @NamedQuery(name = "Charity.findByPhone", query = "SELECT c FROM Charity c WHERE c.phone = :phone"),
    @NamedQuery(name = "Charity.findByUserId", query = "SELECT c FROM Charity c WHERE c.userId = :userId"),
    @NamedQuery(name = "Charity.findByRegistrationNo", query = "SELECT c FROM Charity c WHERE c.registrationNo = :registrationNo"),
    @NamedQuery(name = "Charity.findByAccountNo", query = "SELECT c FROM Charity c WHERE c.accountNo = :accountNo"),
    @NamedQuery(name = "Charity.findByConnectionString", query = "SELECT c FROM Charity c WHERE c.connectionString = :connectionString"),
    @NamedQuery(name = "Charity.findByIsVerified", query = "SELECT c FROM Charity c WHERE c.isVerified = :isVerified"),
    @NamedQuery(name = "Charity.findByIsActive", query = "SELECT c FROM Charity c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Charity.findByTimestamp", query = "SELECT c FROM Charity c WHERE c.timestamp = :timestamp")})
public class Charity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Charity_ID")
    private Integer charityID;
    @Size(max = 100)
    @Column(name = "Charity_Name")
    private String charityName;
    @Size(max = 300)
    @Column(name = "Charity_Description")
    private String charityDescription;
    @Size(max = 100)
    @Column(name = "Address_Line1")
    private String addressLine1;
    @Size(max = 100)
    @Column(name = "Address_Line2")
    private String addressLine2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "Location")
    private String location;
    @Size(max = 50)
    @Column(name = "PostCode")
    private String postcode;
    @Size(max = 7)
    @Column(name = "Email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 100)
    @Column(name = "User_Id")
    private Integer userId;
    @Size(max = 11)
    @Column(name = "Registration_No")
    private String registrationNo;
    @Size(max = 100)
    @Column(name = "Account_No")
    private String accountNo;
    @Column(name = "Connection_String")
    private String connectionString;
    @Size(max = 500)
    @Column(name = "isVerified")
    private Boolean isVerified;
    @Column(name = "isActive")
    private Boolean isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Charity() {
    }

    public Charity(Integer charityID) {
        this.charityID = charityID;
    }

    public Charity(Integer charityID, Date timestamp) {
        this.charityID = charityID;
        this.timestamp = timestamp;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (charityID != null ? charityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Charity)) {
            return false;
        }
        Charity other = (Charity) object;
        if ((this.charityID == null && other.charityID != null) || (this.charityID != null && !this.charityID.equals(other.charityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RESTservices.Charity[ charityID=" + charityID + " ]";
    }
    
}
