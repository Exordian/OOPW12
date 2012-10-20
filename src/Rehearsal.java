import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Rehearsal extends MusicEvent {
	private final int rent;
	
	public Rehearsal(Location place, GregorianCalendar date, int duration, int rent) {
		super(place, date, duration);
		this.rent = rent;
	}
	
	/**
	 * Returns the rent of the rehearsal place.
	 * 
	 * @return rent
	 */
	public int getRent() {
		return this.rent;
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "REHEARSAL! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, rent:" + this.rent;
	}
}
