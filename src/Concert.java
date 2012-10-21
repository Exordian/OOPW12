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
		super(place, date, duration);
		this.salary=salary;
	}

	/**
	 * Returns salary.
	 * 
	 * @return salary
	 */
	public int getSalary() {
		return this.salary;
	}
	
	/**
	 * Returns Turnover of the Event, mostly positive at concerts
	 * 
	 * @return turnover
	 */
	public int getTurnover() {
		return getSalary();
	}
	
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "CONCERT! location: " + super.getPlace() + ", date:" + format.format(super.getDate().getTime())
				+ ", duration:" + super.getDuration() + "s, salary:" + this.salary;
	}

}
