package hibernateEntities;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "fieldSelection")
public class FieldSelection {

	private Integer field_selection_id;
	private String field_selection_value;
	private Timestamp timestamp;
	private FormFields formField;
	
	public FieldSelection() {}
	
	public FieldSelection( String value) {
		this.timestamp = new Timestamp(Calendar.DATE);
		this.field_selection_value= value;
	}
	@XmlElement
	public Integer getField_selection_id() {
		return field_selection_id;
	}
	public void setField_selection_id(Integer field_selection_id) {
		this.field_selection_id = field_selection_id;
	}
	@XmlElement
	public String getField_selection_value() {
		return field_selection_value;
	}
	public void setField_selection_value(String field_selection_value) {
		this.field_selection_value = field_selection_value;
	}
	@XmlElement
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@JsonIgnore
	public FormFields getFormField() {
		return this.formField;
	}
	
	public void setFormField(FormFields ff) {
		formField = ff;
	}
	
}
