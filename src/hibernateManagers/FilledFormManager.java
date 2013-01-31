package hibernateManagers;

import hibernateEntities.FilledForm;
import hibernateEntities.FormFields;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class FilledFormManager {
	public static  String getRecordsData(){
		Map<String,HashSet<Integer>> results = new TreeMap<String,HashSet<Integer>>();
		ArrayList<FilledForm> filled_forms = (ArrayList<FilledForm>) ConnectionManager.getTable("FilledForm");
		Iterator<FilledForm> iter = filled_forms.iterator();
		while (iter.hasNext()){
			FilledForm filled_form = iter.next();
			if(filled_form.getIsActive()){
				FormFields formfields = filled_form.getForm_field_id();
				if(formfields.getIsActive()){
					Integer temp_record_id = filled_form.getRecord_id();
					HashSet<Integer> records = results.get(filled_form.getUser_id().getUserName());
					if(records!=null){
						records.add(temp_record_id);
						results.put(filled_form.getUser_id().getUserName(),records );
					}						
					else{
						records = new HashSet<Integer>();
						records.add(temp_record_id);
						results.put(filled_form.getUser_id().getUserName(),records );
					}
				}
			}
		}
		StringBuilder finalresult = new StringBuilder();
		//Map<String,Integer> final_results = new TreeMap<String,Integer>();
		Iterator<Entry<String,HashSet<Integer>>> results_iter = results.entrySet().iterator();
		finalresult.append('[');
		while(results_iter.hasNext()){
			Entry<String,HashSet<Integer>> entry = results_iter.next();
			//final_results.put(entry.getKey(), entry.getValue().size());
			finalresult.append(String.format("[\"%s\",%d],", entry.getKey(), entry.getValue().size()));
		}
		finalresult.setCharAt(finalresult.length()-1, ']');
		return finalresult.toString();
	}
}
