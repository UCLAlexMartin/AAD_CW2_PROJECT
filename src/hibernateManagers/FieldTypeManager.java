package hibernateManagers;

import hibernateEntities.FieldType;


public class FieldTypeManager {
	
	public static FieldType getFieldType(Integer id){
		FieldType fieldType = (FieldType)ConnectionManager.get(FieldType.class,id);
		return fieldType;
	}

}
