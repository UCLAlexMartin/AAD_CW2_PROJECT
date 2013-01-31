package hibernateManagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hibernateEntities.FieldType;
import hibernateEntities.FormFields;

public class FormFieldsManager {
	
	public static Map<Integer,List<String>> retrieve(Integer form_id){
		ArrayList<FormFields> formFields = (ArrayList<FormFields>) ConnectionManager.getTable("FormFields");
		Iterator<FormFields> iter = formFields.iterator();
		Map<Integer,List<String>> results = new TreeMap<Integer,List<String>>();
		while(iter.hasNext()){
			FormFields formFieldsTemp = iter.next();
			if (formFieldsTemp.getForm_id().getFormId().equals(form_id))
			{
				FieldType fieldTypeTemp= FieldTypeManager.getFieldType(formFieldsTemp.getField_type_id().getField_type_id());
				ArrayList<String> dataArray = new ArrayList<String>();
				dataArray.add(formFieldsTemp.getField_label());
				dataArray.add(fieldTypeTemp.getField_type());
				dataArray.add(fieldTypeTemp.getField_dataType());
				dataArray.add(formFieldsTemp.getIsRequired().toString());
				results.put(formFieldsTemp.getF_id(), dataArray);
			}
		}
		return results;
	}
/*
 * select B.Field_Id, B.Field_Label,C.Field_Type, C.Field_DataType, B.isRequired 
 * FROM Form A INNER JOIN Form_Fields B ON A.Form_Id = B.Form_Id
 * INNER JOIN Field_Type C ON B.Field_Type_Id = C.Field_Type_Id
 * WHERE A.isActive = 1 AND B.isActive = 1 AND C.isActive = 1 AND A.Form_Id = 1
 */
}
