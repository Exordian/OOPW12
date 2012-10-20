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

	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "AUFTRITT! Ort: " + super.getPlace() + ", Datum:" + format.format(super.getDate().getTime())
				+ ", Dauer:" + super.getDuration() + "s, Miete:" + this.salary;
	}

}
