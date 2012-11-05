
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
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
	}

	public void addEvent(GregorianCalendar date, ICalendarEntry entry){
		// (date and entry) != null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_ADD, entry));		
		// entry has been added to calendar
	}

	public void changeEvent(GregorianCalendar date, ICalendarEntry oldevent, ICalendarEntry newevent){
		// (date, oldevent and newevent) != null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_CHANGED, oldevent, newevent));		
		// entry has been changed in calendar
	}

	public void removeEvent(GregorianCalendar date, ICalendarEntry entry){
		// (date and entry) != null
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_REMOVE, entry));		
		// entry has been removed from calendar
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
		// clazz != null
		ArrayList<T> events = new ArrayList<T>();
		for(Entry<GregorianCalendar, CalendarEntry> e : entries.entrySet()) {
			if(checkDate(e.getKey(), from, to))
				if(clazz.isInstance(e.getValue().getEntry())) {
					if(e.getValue().getEvent() == CalendarEvent.EVENT_ADD)
						events.add((T) e.getValue().getEntry());
					else if(e.getValue().getEvent() == CalendarEvent.EVENT_CHANGED) {
						events.remove((T) e.getValue().getPrevEntry());
						events.add((T) e.getValue().getEntry());
					} else
						events.remove((T) e.getValue().getEntry());
				}
		}
		return events;
		// returns list of events
	}
}
