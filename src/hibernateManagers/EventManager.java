package hibernateManagers;

import com.google.common.annotations.VisibleForTesting;
import hibernateEntities.Event;

import java.sql.Date;
import java.util.*;

public class EventManager {

    /**
     * get a list of events from database and returns oncoming events.
     * @return map from eventId to a list storing event information.
     */
	public static Map<Integer,ArrayList<String>> getEvents(){
		List<Event> events = ConnectionManager.getEventTable();
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
