/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class CalendarEntry {
	private final CalendarEvent event;
	private final ICalendarEntry entry;
	private final ICalendarEntry prevEntry;
	

	public CalendarEntry(CalendarEvent event, ICalendarEntry entry) {
		this(event, entry, null);
	}
	
	public CalendarEntry(CalendarEvent event, ICalendarEntry entry, ICalendarEntry prevEntry) {
		this.event = event;
		this.entry = entry;
		this.prevEntry = prevEntry;
	}
	
	

	/**
	 * Returns event.
	 * 
	 * @return event
	 */
	public CalendarEvent getEvent() {
		return event;
	}
	
	/**
	 * Returns Calendar entry.
	 * 
	 * @return entry
	 */
	public ICalendarEntry getEntry() {
		return entry;
	}
	
	/**
	 * Returns Calendar entry.
	 * 
	 * @return entry
	 */
	public ICalendarEntry getPrevEntry() {
		return prevEntry;
	}
	
}
