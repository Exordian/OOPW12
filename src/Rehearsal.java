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
	
	public Rehearsal(String place, GregorianCalendar date, int duration, int rent) {
		super(place, date, duration);
		this.rent=rent;
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
		return "PROBE! Ort: " + super.getPlace() + ", Datum:" + format.format(super.getDate().getTime())
				+ ", Dauer:" + super.getDuration() + "s, Miete:" + this.rent;
	}
}
