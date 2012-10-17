/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class CalendarEntry {
	private final CalendarEvent event;
	private final ICalendarEntry entry;
	
	public CalendarEntry(CalendarEvent event, ICalendarEntry entry) {
		this.event = event;
		this.entry = entry;
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
}
