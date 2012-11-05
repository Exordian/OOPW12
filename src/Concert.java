import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Concert extends MusicEvent {
	private final BigDecimal salary;

	public Concert(Location place, GregorianCalendar date, int duration, BigDecimal salary) {
		// (place, date, salary) != null; duration > 0; salary >= 0 
		super(place, date, duration);
		this.salary=salary;
	}

	public BigDecimal getSalary() {
		return this.salary;
		// returns stored salary
	}
	
	public BigDecimal getTurnover() {
		return getSalary();
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "CONCERT! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, salary:" + this.salary;
		// returns concert in a readable string
	}

}
