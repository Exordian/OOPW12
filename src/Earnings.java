import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Earnings extends Finances {
	
	public Earnings(double income, GregorianCalendar date) {
		super(income, date);
	}

	/**
	 * Returns the amount that was added to the Calendar.
	 * 
	 * @return amount
	 */
	public double getIncome() {
		return this.amount;
	}

}