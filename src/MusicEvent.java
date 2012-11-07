import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
abstract public class MusicEvent implements ICalendarEntry {
	// place != null
	private final Location place;
	private final GregorianCalendar date;
	// duration > 0
	private final int duration;
	
	public MusicEvent(Location place, GregorianCalendar date, int duration) {
		// place != null; valid date; duration > 0
		this.place=place;
		this.date=date;
		this.duration=duration;
	}
	
	public String getPlace() {
		return this.place.getName();
		// returns place name
	}

	public GregorianCalendar getDate() {
		return this.date;		
		// returns stored date
	}

	abstract public BigDecimal getTurnover();
	
	public int getDuration() {
		return this.duration;		
		// returns stored duration
	}
	
}
