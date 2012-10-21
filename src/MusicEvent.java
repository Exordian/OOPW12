import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class MusicEvent implements ICalendarEntry {
	private final Location place;
	private final GregorianCalendar date;
	private final int duration;
	
	public MusicEvent(Location place, GregorianCalendar date, int duration) {
		this.place=place;
		this.date=date;
		this.duration=duration;
	}
	
	/**
	 * Returns place of the music event.
	 * 
	 * @return place
	 */
	public String getPlace() {
		return this.place.getName();		
	}
	
	/**
	 * Returns date of the music event.
	 * 
	 * @return date
	 */	
	public GregorianCalendar getDate() {
		return this.date;		
	}
	
	/**
	 * Returns the duration of the music event.
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return this.duration;		
	}
	
}
