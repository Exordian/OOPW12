import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Concert extends MusicEvent {
	private final int salary;

	public Concert(Location place, GregorianCalendar date, int duration, int salary) {
		// place and date must not be null, duration and salary should be positive 
		super(place, date, duration);
		this.salary=salary;
		// returns concert instance
	}

	public int getSalary() {
		return this.salary;
		// returns stored salary
	}
	
	public int getTurnover() {
		return getSalary();
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "CONCERT! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, salary:" + this.salary;
		// returns concert in a readable string
	}

}
