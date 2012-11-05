import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Finances implements ICalendarEntry {
	
	protected final BigDecimal amount;
	private final GregorianCalendar date;
	
	public Finances(BigDecimal amount, GregorianCalendar date) {
		// valid date; amount >= 0
		this.amount = amount;
		this.date = date;
		// returns Finances instance
	}
	
	public GregorianCalendar getDate() {
		// date has to be initialized
		return this.date;
		// returns stored date
	}
	
	public BigDecimal getAmount() {
		// amount has to be initialized
		return this.amount;
		// returns stored amount
	}
	
}