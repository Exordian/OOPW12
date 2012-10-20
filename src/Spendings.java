import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Spendings extends Finances {
	
	public Spendings(double expenditure, GregorianCalendar date) {
		super(expenditure, date);
	}
	
	public double getExpenditure() {
		return this.amount;
	}

}