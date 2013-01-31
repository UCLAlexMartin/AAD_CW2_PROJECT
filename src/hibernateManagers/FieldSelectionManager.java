package hibernateManagers;

import hibernateEntities.FieldSelection;

import java.util.ArrayList;
import java.util.Iterator;

public class FieldSelectionManager {
	
	public static ArrayList<String>getValues(Integer field_id ){
		ArrayList<String> values = new ArrayList<String>();
		ArrayList<FieldSelection> fieldSelections = (ArrayList<FieldSelection>) ConnectionManager.getTable("FieldSelection");
		Iterator<FieldSelection> iter = fieldSelections.iterator();
		while(iter.hasNext()){
			FieldSelection cur = iter.next();
			if(cur.getField_selection_id().equals(field_id)){
				values.add(cur.getField_selection_value());
			}
		}
		
		return values;
	}

}
