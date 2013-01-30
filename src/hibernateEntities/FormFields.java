package hibernateEntities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "formFields")
public class FormFields {
	private Integer f_id;
	private String field_label;
	private FieldType field_type_id;
	private Set<FieldSelection> field_selections;
	private Float X_coordinate;
	private Float Y_coordinate;
	private Boolean isRequired;
	private Boolean default_value;
	private Float minValue;
	private Float maxValue;
	private User user_id;
	private Boolean isActive;
	private Date date_created;
	private Form form_id;
	private Timestamp timestamp;
	
	@XmlElement
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timeStamp) {
		this.timestamp = timeStamp;
	}
	@XmlElement(name = "form")
	public Form getForm_id() {
		return form_id;
	}
	public void setForm_id(Form form_id) {
		this.form_id = form_id;
	}
	@XmlElement
	public String getField_label() {
		return field_label;
	}
	@XmlElement
	public Integer getF_id() {
		return f_id;
	}
	public void setF_id(Integer f_id) {
		this.f_id = f_id;
	}
	public void setField_label(String field_label) {
		this.field_label = field_label;
	}
	@XmlElement(name = "fieldType")
	public FieldType getField_type_id() {
		return field_type_id;
	}
	public void setField_type_id(FieldType field_type_id) {
		this.field_type_id = field_type_id;
	}
	@XmlElement
	public Set<FieldSelection> getField_selections() {
		return field_selections;
	}
	public void setField_selection_Id(Set<FieldSelection> field_selections) {
		this.field_selections = field_selections;
	}
	@XmlElement
	public Float getX_coordinate() {
		return X_coordinate;
	}
	public void setX_coordinate(Float x_coordinate) {
		X_coordinate = x_coordinate;
	}
	@XmlElement
	public Float getY_coordinate() {
		return Y_coordinate;
	}
	public void setY_coordinate(Float y_coordinate) {
		Y_coordinate = y_coordinate;
	}
	@XmlElement
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	@XmlElement
	public Boolean getDefault_value() {
		return default_value;
	}
	public void setDefault_value(Boolean default_value) {
		this.default_value = default_value;
	}
	@XmlElement
	public Float getMinValue() {
		return minValue;
	}
	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}
	@XmlElement
	public Float getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}
	@XmlElement(name = "user")
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@XmlElement
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
/*	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
*/
	
	
}
