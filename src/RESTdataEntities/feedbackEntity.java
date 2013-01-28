/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTdataEntities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * @author cchen
 */
@Entity
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "feedbackEntity.findAll", query = "SELECT f FROM feedbackEntity f")})
public class feedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Feedback_Id")
    private Integer feedbackId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="电子邮件无效")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "Comment")
    private String comment;
    @Column(name = "ReviewedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isReviewed")
    private boolean isReviewed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "ReviewedBy", referencedColumnName = "User_Id")
    @ManyToOne
    private Users reviewedBy;

    public feedbackEntity() {
    }

    public feedbackEntity(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public feedbackEntity(Integer feedbackId, String name, String email, String comment, boolean isReviewed, Date timestamp) {
        this.feedbackId = feedbackId;
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.isReviewed = isReviewed;
        this.timestamp = timestamp;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
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

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public boolean getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(boolean isReviewed) {
        this.isReviewed = isReviewed;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Users getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Users reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof feedbackEntity)) {
            return false;
        }
        feedbackEntity other = (feedbackEntity) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RESTdataEntities.feedbackEntity[ feedbackId=" + feedbackId + " ]";
    }
    
}
