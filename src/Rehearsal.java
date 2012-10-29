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
		// place should not be null, date should be valid, duration should be positive, rent should be positive
		super(place, date, duration);
		this.rent = rent;
		// returns rehearsal instance
	}
	
	public int getRent() {
		// rent has to be initialized
		return this.rent;
		// returns stored rent
	}
	
	public int getTurnover() {
		return -this.getRent();
		// returns negative rent
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "REHEARSAL! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, rent:" + this.rent;
		// returns rehearsal in a readable string

	}
}
