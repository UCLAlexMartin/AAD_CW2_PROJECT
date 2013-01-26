package env.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public class FormFields {
	private Integer field_id;
	private Form form_id;
	private String field_label;
	private FieldType field_type_id;
	private Set<FieldSelection> field_selection_Id;
	private Float X_coordinate;
	private Float Y_coordinate;
	private Boolean isRequired;
	private Boolean default_value;
	private Float minValue;
	private Float maxValue;
	private User user_id;
	private Boolean isActive;
	private Date date_created;
	private Timestamp timeStamp;
	
	public Integer getFieldId() {
		return field_id;
	}
	public void setFieldId(Integer field_Id) {
		this.field_id = field_Id;
	}
	public Form getFormId() {
		return form_id;
	}
	public void setFormId(Form form_Id) {
		this.form_id = form_Id;
	}
	public String getFieldLabel() {
		return field_label;
	}
	public void setFieldLabel(String fieldLabel) {
		this.field_label = fieldLabel;
	}
	public FieldType getFieldTypeId() {
		return field_type_id;
	}
	public void setFieldTypeId(FieldType fieldTypeId) {
		this.field_type_id = fieldTypeId;
	}
	public Set<FieldSelection> getFieldSelectionId() {
		return field_selection_Id;
	}
	public void setFieldSelectionId(Set<FieldSelection> fieldSelectionId) {
		this.field_selection_Id = fieldSelectionId;
	}
	public Float getxCoordinate() {
		return X_coordinate;
	}
	public void setxCoordinate(Float xCoordinate) {
		this.X_coordinate = xCoordinate;
	}
	public Float getyCoordinate() {
		return Y_coordinate;
	}
	public void setyCoordinate(Float yCoordinate) {
		this.Y_coordinate = yCoordinate;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Boolean getDefaultValue() {
		return default_value;
	}
	public void setDefaultValue(Boolean defaultValue) {
		this.default_value = defaultValue;
	}
	public Float getMinValue() {
		return minValue;
	}
	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}
	public Float getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}
	public User getUserId() {
		return user_id;
	}
	public void setUserId(User userId) {
		this.user_id = userId;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
