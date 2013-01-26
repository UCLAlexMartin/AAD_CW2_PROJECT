package env.Entities;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class FieldSelection {

	private Integer field_selection_id;
	private FormFields field_id;
	private String field_selection_value;
	private Timestamp timestamp;
	
	
	public Integer getField_selection_id() {
		return field_selection_id;
	}
	public void setField_selection_id(Integer field_selection_id) {
		this.field_selection_id = field_selection_id;
	}
	public FormFields getField_id() {
		return field_id;
	}
	public void setField_id(FormFields field_id) {
		this.field_id = field_id;
	}
	public String getField_selection_value() {
		return field_selection_value;
	}
	public void setField_selection_value(String field_selection_value) {
		this.field_selection_value = field_selection_value;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
