
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
	}
	
	/**
	 * Adds CalendarEntry.
	 * 
	 * @param date
	 * @param entry
	 */
	public void addEvent(GregorianCalendar date, ICalendarEntry entry){
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_ADD, entry));		
	}

	/**
	 * Removes CalendarEntry.
	 * 
	 * @param date
	 * @param entry
	 */
	public void changeEvent(GregorianCalendar date, ICalendarEntry oldevent, ICalendarEntry newevent){
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_CHANGED, oldevent, newevent));		
	}

	/**
	 * Removes CalendarEntry.
	 * 
	 * @param date
	 * @param entry
	 */
	public void removeEvent(GregorianCalendar date, ICalendarEntry entry){
		entries.put(date, new CalendarEntry(CalendarEvent.EVENT_REMOVE, entry));		
	}
	
	/**
	 * Checks...
	 * 
	 * @param date
	 * @param from
	 * @param to
	 * @return boolean for getCalendarEvents()
	 */
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

	/**
	 * Returns a list with Calendar entries.
	 * 
	 * @param from
	 * @param to
	 * @param clazz
	 * @return ArrayList with calendar entries
	 */
	@SuppressWarnings("unchecked")
	public <T extends ICalendarEntry> ArrayList<T> getCalendarEvents(GregorianCalendar from, GregorianCalendar to, Class<T> clazz) {
		ArrayList<T> events = new ArrayList<T>();
		// we could get a subtree from our treemap, this would increase our performance
		// at first this wasn't a treemap, we changed it afterwards, so this is a leftover
		// but it works and currently performance is not a required request ;)
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
	}
}
