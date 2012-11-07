import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Band {
	// cal, financialcal != null
	private Calendar cal;
	private Calendar financialcal;
	private String bandName ="";
	private ArrayList<Location> locationList = new ArrayList<Location>();
	// memberCount >= 0
	private int memberCount = 0;
	
	public Band(String bandName){
		cal = new Calendar();
		financialcal = new Calendar();
		this.bandName = bandName;
	}
	
	public String getBandName() {
		return this.bandName;
		//returns band name
	}
	
	public void addMember(GregorianCalendar date, Member member) {
		// date != null, member != null
		this.memberCount++;
		cal.addEvent(date, member);
		//member has been added to calendar and member count has been incremented
	}
	
	public void removeMember(GregorianCalendar date, Member member) {
		// date != null, member != null, memberCount > 0
		this.memberCount--;
		cal.removeEvent(date, member);
		//member has been removed from calender and member count has been decremented
	}

	public int numberOfMembers(){
		return this.memberCount;
		//returns member count
	}
	
	public ArrayList<Member> getMembers(GregorianCalendar date) {
		return cal.getCalendarEvents(null, date, Member.class);
		//neither of the elements is null
		//returns an ArrayList with the members of the band on a specific date
	}
	
	public void changeMemberStatus(Member member) {
		member.changeStatus();
		//member status has been changed either to PermMember or to TempMember
	}

	public void addLocation(Location loc) {
		//loc != null
		this.locationList.add(loc);
		//location which is available to bands has been added
	}
	
	public ArrayList<Location> findLocation(int capacity) {
		// capacity > 0
		ArrayList<Location> temp = new ArrayList<Location>();
		for (Location l : this.locationList) {
			//locationList has to exist
			if (l.getCapacity() >= capacity)
				temp.add(l);
		}
		return temp;
		//adequate location for the band to host their event has been found
	}
	
	public void addMusicEvent(GregorianCalendar date, MusicEvent event) {
		// date != null, musicEvent != null
		cal.addEvent(date, event);
		for(Member m : getMembers(date)) {
			m.inform("New Event: " + event);
		}
		if(event.getTurnover().doubleValue() < 0)
			this.addExpenditure(date, event.getTurnover().negate());
		else
			this.addIncome(date, event.getTurnover());
		//music event has been added to calendar and expenditures/income has been added to financial calendar
	}

	public void changeMusicEvent(GregorianCalendar date, MusicEvent oldevent, MusicEvent newevent) {
		// date != null, oldevent != null, newevent != null
		if (oldevent.getTurnover().doubleValue() > 0) {
			this.removeIncome(date, oldevent.getTurnover());
			this.addIncome(date, newevent.getTurnover());
		} else {
			this.removeExpenditure(date, oldevent.getTurnover().negate());
			this.addExpenditure(date, newevent.getTurnover().negate());
		}		
		for(Member m : getMembers(date)) {
			m.inform("Event changed: " +oldevent+ " to " + newevent);
		}
		cal.changeEvent(date, oldevent, newevent);
		//specific event has been modified from calendar and finances from financial calendar has been changed
	}
	
	public void removeMusicEvent(GregorianCalendar date, MusicEvent event) {
		// date != null, musicEvent != null
		if (event.getTurnover().doubleValue() > 0) {
			this.removeIncome(date, event.getTurnover());
		} else {
			this.removeExpenditure(date, event.getTurnover().negate());
		}
		
		for(Member m : getMembers(date)) {
			m.inform("Event removed: " + event);
		}
		cal.removeEvent(date, event);
		//specific event has been removed from calendar and finances has been removed from finance calendar
	}
	
	public ArrayList<MusicEvent> getMusicEvents(GregorianCalendar from, GregorianCalendar to) {
		return cal.getCalendarEvents(from, to, MusicEvent.class);
		//returns music events from a specific time to a specific time
	}

	public void addIncome(GregorianCalendar date, BigDecimal income) {
		//date ! = null; income >= 0
		financialcal.addEvent(date, new Earnings(income, date));
		//income has been added to finance calendar
	}
	
	public void addExpenditure(GregorianCalendar date, BigDecimal expenditure) {
		//date ! = null; income >= 0
		financialcal.addEvent(date, new Spendings(expenditure, date));
		//expenditure has been added to finance calendar
	}
	
	public void removeIncome(GregorianCalendar date, BigDecimal income) {
		//date ! = null; income >= 0
		financialcal.removeEvent(date, new Earnings(income, date));
		//income has been removed from financial calendar
	}
	
	public void removeExpenditure(GregorianCalendar date, BigDecimal expenditure) {
		//date ! = null; income >= 0
		financialcal.removeEvent(date, new Spendings(expenditure, date));
		//expenditure has been removed from financial calendar
	}

	public BigDecimal moneyGained(GregorianCalendar from, GregorianCalendar to) {
		BigDecimal sum = new BigDecimal(0);
		for(Earnings e : financialcal.getCalendarEvents(from, to, Earnings.class)) {
			sum.add(e.getIncome());
		}
		return sum;
		//returns the money the band gained with their concerts
	}

	public BigDecimal moneySpent(GregorianCalendar from, GregorianCalendar to) {
		BigDecimal sum = new BigDecimal(0);
		for(Spendings s : financialcal.getCalendarEvents(from, to, Spendings.class)) {
				sum.add(s.getExpenditure());
		}
		return sum;
		//returns the money the band spent on their rehearsals
	}

	public BigDecimal moneySituation(GregorianCalendar from, GregorianCalendar to) {
		return moneyGained(from, to).subtract(moneySpent(from, to));
		//returns the band's money situation
	}
}	
