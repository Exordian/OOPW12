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
	// if this calendar gets too big, it should be splitted into one calendar per event
	private Calendar cal;
	private String bandName ="";
	private ArrayList<Location> locationList = new ArrayList<Location>();
	private int memberCount = 0;
	
	public Band(String bandName){
		cal = new Calendar();
		this.bandName = bandName;
	}
	
	/* even more generic, but i think addMember/addSong/addMusicEvent is fine too
	public <T extends ICalendarEntry> void add(GregorianCalendar date, T entry) {
		cal.addEvent(date, entry);
	}
	
	public <T extends ICalendarEntry> void remove(GregorianCalendar date, T entry) {
		cal.addEvent(date, entry);
	}
	*/
	
	/**
	 * Returns band name.
	 * 
	 * @return name
	 */
	public String getBandName() {
		return this.bandName;
	}
	
	/**
	 * Adds member.
	 * 
	 * @param date
	 * @param member
	 */
	public void addMember(GregorianCalendar date, Member member) {
		this.memberCount++;
		cal.addEvent(date, member);
	}
	
	/**
	 * Removes specific member.
	 * 
	 * @param date
	 * @param member
	 */
	public void removeMember(GregorianCalendar date, Member member) {
		this.memberCount--;
		cal.removeEvent(date, member);
	}
	
	/**
	 * Returns the current number of Members in the band.
	 * 
	 * @return quantity
	 */
	public int numberOfMembers(){
		return this.memberCount;
	}
	
	/**
	 * Returns specific member.
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<Member> getMembers(GregorianCalendar date) {
		return cal.getCalendarEvents(null, date, Member.class);
	}
	
	/** TODO
	 * Changes the Member Status either to PermMember or to TempMember.
	 * 
	 * @param member
	 */
	public void changeMemberStatus(Member member) {
		member.changeStatus();
	}

	/**
	 * Adds a location to the bands available event locations.
	 * 
	 * @param loc
	 */
	public void addLocation(Location loc) {
		this.locationList.add(loc);
	}
	
	/**
	 * Finds an adequate location for the Band to host their event.
	 * 
	 * @param capacity
	 * @return location
	 */
	public ArrayList<Location> findLocation(int capacity) {
		ArrayList<Location> temp = new ArrayList<Location>();
		for (Location l : this.locationList) {
			if (l.getCapacity() >= capacity)
				temp.add(l);
		}
		return temp;
	}
	
	/**
	 * Adds music event to calendar.
	 * 
	 * @param date
	 * @param member
	 */
	public void addMusicEvent(GregorianCalendar date, MusicEvent event) {
		cal.addEvent(date, event);
		for(Member m : getMembers(date)) {
			m.inform("New Event: " + event);
		}
		if(event.getTurnover() < 0)
			this.addExpenditure(date, -event.getTurnover());
		else
			this.addIncome(date, event.getTurnover());
	}

	/**
	 * Modify specific Event from calendar.
	 * 
	 * @param date
	 * @param event
	 */
	public void changeMusicEvent(GregorianCalendar date, MusicEvent oldevent, MusicEvent newevent) {
		if (oldevent instanceof Concert) {
			this.removeIncome(date, ((Concert) oldevent).getSalary());
			this.addIncome(date, ((Concert)newevent).getSalary());
		} else {
			this.removeExpenditure(date, ((Rehearsal) oldevent).getRent());
			this.addExpenditure(date, ((Rehearsal)newevent).getRent());
		}
		
		for(Member m : getMembers(date)) {
			m.inform("Event changed: " +oldevent+ " to " + newevent);
		}
		cal.changeEvent(date, oldevent, newevent);
	}
	
	/**
	 * Removes specific Event from calendar.
	 * 
	 * @param date
	 * @param event
	 */
	public void removeMusicEvent(GregorianCalendar date, MusicEvent event) {
		if (event instanceof Concert) {
			this.removeIncome(date, ((Concert) event).getSalary());
		} else {
			this.removeExpenditure(date, ((Rehearsal) event).getRent());
		}
		
		for(Member m : getMembers(date)) {
			m.inform("Event removed: " + event);
		}
		cal.removeEvent(date, event);
	}
	
	/**
	 * Returns music events from a specific time to a specific time.
	 * 
	 * @param from
	 * @param to
	 * @return ArrayList with specific music events.
	 */
	public ArrayList<MusicEvent> getMusicEvents(GregorianCalendar from, GregorianCalendar to) {
		return cal.getCalendarEvents(from, to, MusicEvent.class);
	}

	/**
	 * Adds income.
	 * 
	 * @param date
	 * @param income
	 */
	public void addIncome(GregorianCalendar date, double income) {
		cal.addEvent(date, new Earnings(income, date));
	}
	
	/**
	 * Adds expenditures.
	 * 
	 * @param date
	 * @param expenditure
	 */
	public void addExpenditure(GregorianCalendar date, double expenditure) {
		cal.addEvent(date, new Spendings(expenditure, date));
	}
	
	/**
	 * Removes income.
	 * 
	 * @param date
	 * @param income
	 */
	public void removeIncome(GregorianCalendar date, double income) {
		cal.removeEvent(date, new Earnings(income, date));
	}
	
	/**
	 * Removes expenditures.
	 * 
	 * @param date
	 * @param expenditure
	 */
	public void removeExpenditure(GregorianCalendar date, double expenditure) {
		cal.removeEvent(date, new Spendings(expenditure, date));
	}

	/**
	 * Returns the money the band gained with their concerts.
	 * 
	 * @param from
	 * @param to
	 * @return Sum of money gained
	 */

	public double moneyGained(GregorianCalendar from, GregorianCalendar to) {
		return cal.moneyGained(from, to);
	}

	/**
	 * Returns the money the band spent on their rehearsals.
	 * 
	 * @param from
	 * @param to
	 * @return Sum of money spent
	 */
	public double moneySpent(GregorianCalendar from, GregorianCalendar to) {
		return cal.moneySpent(from, to);
	}

	/**
	 * Returns the band's money situation.
	 * 
	 * @param from
	 * @param to
	 * @return Difference of money gained and money spent
	 */
	public double moneySituation(GregorianCalendar from, GregorianCalendar to) {
		return moneyGained(from, to) - moneySpent(from, to);
	}
}
