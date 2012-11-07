/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class CalendarEntry {
	private final CalendarEvent event;
	private final ICalendarEntry entry;
	// prevEntry != only if event = EVENT_CHANGED
	private final ICalendarEntry prevEntry;
	

	public CalendarEntry(CalendarEvent event, ICalendarEntry entry) {
		// only used if event is not event_changed
		this(event, entry, null);
	}
	
	public CalendarEntry(CalendarEvent event, ICalendarEntry entry, ICalendarEntry prevEntry) {
		// (event, entry) != null, prevEntry != null if event = event_changed
		this.event = event;
		this.entry = entry;
		this.prevEntry = prevEntry;
	}

	public CalendarEvent getEvent() {
		return event;
		// returns stored event
	}

	public ICalendarEntry getEntry() {
		return entry;
		// returns stored entry
	}

	public ICalendarEntry getPrevEntry() {
		return prevEntry;
		// returns stored preEntry
	}
	
}
