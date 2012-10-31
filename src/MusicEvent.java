import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
abstract public class MusicEvent implements ICalendarEntry {
	private final Location place;
	private final GregorianCalendar date;
	private final int duration;
	
	public MusicEvent(Location place, GregorianCalendar date, int duration) {
		// place should not be null, date should be valid, duration should be positive
		this.place=place;
		this.date=date;
		this.duration=duration;
		// returns music event
	}
	
	public String getPlace() {
		// place has to be initialized
		return this.place.getName();
		// returns place name
	}

	public GregorianCalendar getDate() {
		// date has to be initialized
		return this.date;		
		// returns stored date
	}

	abstract public BigDecimal getTurnover();
	
	public int getDuration() {
		// duration has to be initialized
		return this.duration;		
		// returns stored duration
	}
	
}
