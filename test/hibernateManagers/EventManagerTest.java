package hibernateManagers;

import com.google.common.collect.ImmutableList;
import hibernateEntities.Event;
import hibernateEntities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.*;

/**
 * test class for {@link EventManager}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionManager.class)
public class EventManagerTest {
    @Test
    public void testGetEvents() {
        mockStatic(ConnectionManager.class);

        List<Event> eventList = ImmutableList.of(
                createEvent(1, "Event1", "This event came an hour ago", "London",
                        new Date(Calendar.getInstance().getTimeInMillis() - 60 * 60 * 1000), new Time(1000), null, null),
                createEvent(2, "Event2", "This event is coming after an hour", "London",
                    new Date(Calendar.getInstance().getTimeInMillis() + 60 * 60 * 1000), new Time(1000), null, null)
        );
        expect(ConnectionManager.getEventTable()).andReturn(eventList);
        replayAll();

        Map<Integer,ArrayList<String>> result = EventManager.getEvents();

        verifyAll();

        assertEquals(1, result.size());
        assertTrue(result.get(1) == null);
        assertNotNull(result.get(2));
        assertFalse(result.get(2).contains("Event1"));
        assertTrue(result.get(2).contains("Event2"));
    }



    private Event createEvent(int eventId, String name, String description, String location, Date date, Time time,
            User userId, Timestamp timeStamp) {
        Event event = new Event();
        event.setEvent_id(eventId);
        event.setEvent_name(name);
        event.setEvent_description(description);
        event.setEvent_location(location);
        event.setEvent_date(date);
        event.setEvent_time(time);
        event.setUser_id(userId);
        event.setTimestamp(timeStamp);
        return event;
    }
}
