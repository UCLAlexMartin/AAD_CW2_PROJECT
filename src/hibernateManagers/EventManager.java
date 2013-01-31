package hibernateManagers;

import hibernateEntities.Event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EventManager {
	
	
	public static Map<Integer,ArrayList<String>> getEvents(){
		ArrayList<Event> events = (ArrayList<Event>) ConnectionManager.getTable("Event");
		Iterator<Event> iter = events.iterator();
		Map<Integer,ArrayList<String>> results = new TreeMap<Integer,ArrayList<String>>();
		while(iter.hasNext()){
			Event event = iter.next();
			if(event.getEvent_date().after(new Date(Calendar.getInstance().getTimeInMillis()))){
						
				ArrayList<String> value = new ArrayList<String>();
				value.add(event.getEvent_date().toString());
				value.add(event.getEvent_name());
				value.add(event.getEvent_location());
				value.add(event.getEvent_time().toString());
				value.add(event.getEvent_description());
				results.put(event.getEvent_id(), value);
				
			}
		}
		return results;
		
	}

}
