import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Earnings extends Finances {
	
	public Earnings(BigDecimal income, GregorianCalendar date) {
		// valid date; income >= 0
		super(income, date);
	}

	public BigDecimal getIncome() {
		// amount has to be initialized
		return this.amount;
		// returns stored amount
	}

}