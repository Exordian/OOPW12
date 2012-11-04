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
	// atm all events are stored in one single calendar, 
	// if this calendar gets too big, it should be splitted into one calendar per event -> partly doen with financial calendar
	private Calendar cal;
	private Calendar financialcal;
	private String bandName ="";
	private ArrayList<Location> locationList = new ArrayList<Location>();
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
		//calendar, member have to be instanced; memberCount >= 0
		//cal != null
		this.memberCount++;
		cal.addEvent(date, member);
		//member has been added to calendar and member count has been incremented
	}
	
	public void removeMember(GregorianCalendar date, Member member) {
		//calendar, member have to be instanced; memberCount >= 0
		//cal != null
		this.memberCount--;
		cal.removeEvent(date, member);
		//member has been removed from calender and member count has been decremented
	}

	public int numberOfMembers(){
		//memberCount has to be initialized, therefore a instance of band must exist
		return this.memberCount; //memberCount >= 0
		//returns member count
	}
	
	public ArrayList<Member> getMembers(GregorianCalendar date) {
		//calendar, member have to be instanced
		//cal != null
		return cal.getCalendarEvents(null, date, Member.class);
		//neither of the elements is null
		//returns an ArrayList with the members of the band on a specific date
	}
	
	public void changeMemberStatus(Member member) {
		//member has to be instanced
		member.changeStatus();
		//member status has been changed either to PermMember or to TempMember
	}

	public void addLocation(Location loc) {
		//location has to be instanced
		this.locationList.add(loc);
		//location which is available to bands has been added
	}
	
	public ArrayList<Location> findLocation(int capacity) {
		//location has to be instanced; capacity has to be initialized
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
		//calendar, musicEvent have to be instanced
		//cal != null, financialcal != 0
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
		//calendar, musicEvent have to be instanced
		//cal != null, financialcal != 0
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
		//calendar, musicEvent have to be instanced
		//cal != null
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
		//calendar, musicEvent have to be instanced
		//cal != null
		return cal.getCalendarEvents(from, to, MusicEvent.class);
		//returns music events from a specific time to a specific time
	}

	public void addIncome(GregorianCalendar date, BigDecimal income) {
		//finance calendar has to be instanced; income >= 0
		//financialcal != null
		financialcal.addEvent(date, new Earnings(income, date));
		//income has been added to finance calendar
	}
	
	public void addExpenditure(GregorianCalendar date, BigDecimal expenditure) {
		//finance calendar has to be instanced; expenditure >= 0
		//financialcal != null
		financialcal.addEvent(date, new Spendings(expenditure, date));
		//expenditure has been added to finance calendar
	}
	
	public void removeIncome(GregorianCalendar date, BigDecimal income) {
		//finance calendar has to be instanced; income >= 0
		//financialcal != null
		financialcal.removeEvent(date, new Earnings(income, date));
		//income has been removed from financial calendar
	}
	
	public void removeExpenditure(GregorianCalendar date, BigDecimal expenditure) {
		//finance calendar has to be instanced; expenditure >= 0
		//financialcal != null
		financialcal.removeEvent(date, new Spendings(expenditure, date));
		//expenditure has been removed from financial calendar
	}

	public BigDecimal moneyGained(GregorianCalendar from, GregorianCalendar to) {
		//finance calendar has to be instanced; from is earlier than to
		//financialcal != null
		BigDecimal sum = new BigDecimal(0);
		for(Earnings e : financialcal.getCalendarEvents(from, to, Earnings.class)) {
			sum.add(e.getIncome());
		}
		return sum;
		//returns the money the band gained with their concerts
	}

	public BigDecimal moneySpent(GregorianCalendar from, GregorianCalendar to) {
		//finance calendar has to be instanced; from is earlier than to
		//financialcal != null
		BigDecimal sum = new BigDecimal(0);
		for(Spendings s : financialcal.getCalendarEvents(from, to, Spendings.class)) {
				sum.add(s.getExpenditure());
		}
		return sum;
		//returns the money the band spent on their rehearsals
	}

	public BigDecimal moneySituation(GregorianCalendar from, GregorianCalendar to) {
		//finance calendar has to be instanced; from is earlier than to
		//financialcal != null
		return moneyGained(from, to).subtract(moneySpent(from, to));
		//returns the band's money situation
	}
}	
