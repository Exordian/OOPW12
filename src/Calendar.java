
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TreeMap;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Calendar {
	private TreeMap<GregorianCalendar, CalendarEntry> entries;
	
	public Calendar(){
		entries = new TreeMap<GregorianCalendar, CalendarEntry>();
		// returns calendar instances
	}

	public void addEvent(GregorianCalendar date, ICalendarEntry entry){
		// date and entry must not be null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_ADD, entry));		
		// entry added to calendar
	}

	public void changeEvent(GregorianCalendar date, ICalendarEntry oldevent, ICalendarEntry newevent){
		// date, oldevent and newevent must not be null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_CHANGED, oldevent, newevent));		
		// entry changed in calendar
	}

	public void removeEvent(GregorianCalendar date, ICalendarEntry entry){
		// date and entry must not be null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_REMOVE, entry));		
		// entry removed from calendar
	}

	private boolean checkDate(GregorianCalendar date, GregorianCalendar from, GregorianCalendar to) {
		if(from == null && to == null)
			return true;
		else if(from == null)
			return date.before(to) || date.compareTo(to) == 0;
		else if(to == null) 
			return date.after(from) || date.compareTo(from) == 0;
		else
			return (date.before(to) && date.after(from)) || ((date.compareTo(to) == 0) || (date.compareTo(from) == 0));
	}

	@SuppressWarnings("unchecked")
	public <T extends ICalendarEntry> ArrayList<T> getCalendarEvents(GregorianCalendar from, GregorianCalendar to, Class<T> clazz) {
		// clazz must not be null
		ArrayList<T> events = new ArrayList<T>();
		for(GregorianCalendar date : entries.keySet()) {
			if(checkDate(date, from, to))
				if(clazz.isInstance(entries.get(date).getEntry())) {
					if(entries.get(date).getEvent() == CalendarEvent.EVENT_ADD)
						events.add((T) entries.get(date).getEntry());
					else if(entries.get(date).getEvent() == CalendarEvent.EVENT_CHANGED) {
						events.remove((T) entries.get(date).getPrevEntry());
						events.add((T) entries.get(date).getEntry());
					} else
						events.remove((T) entries.get(date).getEntry());
				}
		}
		return events;
		// returns list of events
	}
}
