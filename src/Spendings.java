import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Spendings extends Finances {
	
	public Spendings(double expenditure, GregorianCalendar date) {
		// income should be positive, valid date
		super(expenditure, date);
		// returns Spendings instance
	}
	
	public double getExpenditure() {
		// amount has to be initialized
		return this.amount;
		// returns stored amount
	}

}