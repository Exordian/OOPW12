import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Rehearsal extends MusicEvent {
	// rent >= 0
	private final BigDecimal rent;
	
	public Rehearsal(Location place, GregorianCalendar date, int duration, BigDecimal rent) {
		// (place and rent) != null; valid date; duration > 0; rent >= 0
		super(place, date, duration);
		this.rent = rent;
	}
	
	public BigDecimal getRent() {
		return this.rent;
		// returns stored rent
	}
	
	public BigDecimal getTurnover() {
		return this.getRent().negate();
		// returns negative rent
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "REHEARSAL! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, rent:" + this.rent;
		// returns rehearsal in a readable string

	}
}
