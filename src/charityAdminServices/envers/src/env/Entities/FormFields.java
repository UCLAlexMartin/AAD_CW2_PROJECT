package env.Entities;

import java.sql.Timestamp;

public class FormFields {
	private Integer fieldId;
	private Integer formId;
	private String fieldLabel;
	private Integer fieldTypeId;
	private Integer fieldSelectionId;
	private Float xCoordinate;
	private Float yCoordinate;
	private Boolean isRequired;
	private Boolean defaultValue;
	private Float minValue;
	private Float maxValue;
	private Float userId;
	private Boolean isActive;
	private Timestamp timeStamp;
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public Integer getFieldTypeId() {
		return fieldTypeId;
	}
	public void setFieldTypeId(Integer fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}
	public Integer getFieldSelectionId() {
		return fieldSelectionId;
	}
	public void setFieldSelectionId(Integer fieldSelectionId) {
		this.fieldSelectionId = fieldSelectionId;
	}
	public Float getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(Float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public Float getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(Float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Boolean getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
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
	public Float getUserId() {
		return userId;
	}
	public void setUserId(Float userId) {
		this.userId = userId;
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
