import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Finances implements ICalendarEntry {
	
	protected final double amount;
	private final GregorianCalendar date;
	
	public Finances(double amount, GregorianCalendar date) {
		this.amount = amount;
		this.date = date;
	}
	
	public GregorianCalendar getDate() {
		return this.date;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
}