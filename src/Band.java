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
		//bandName must not be null
		cal = new Calendar();
		financialcal = new Calendar();
		this.bandName = bandName;
		//returns instance
	}
	
	/* even more generic, but i think addMember/addSong/addMusicEvent is fine too
	public <T extends ICalendarEntry> void add(GregorianCalendar date, T entry) {
		cal.addEvent(date, entry);
	}
	
	public <T extends ICalendarEntry> void remove(GregorianCalendar date, T entry) {
		cal.addEvent(date, entry);
	}
	*/
	
	public String getBandName() {
		//band has to be instanced
		return this.bandName;
		//return band<name
	}
	
	public void addMember(GregorianCalendar date, Member member) {
		//calendar, member and band has to be instanced; memberCount has to be initialized
		this.memberCount++;
		cal.addEvent(date, member);
		//adds member to calendar and increments member count
	}
	
	public void removeMember(GregorianCalendar date, Member member) {
		//calendar, member and band has to be instanced; memberCount has to be initialized
		this.memberCount--;
		cal.removeEvent(date, member);
		//removes member from calendar and decrements member count
	}

	public int numberOfMembers(){
		//memberCount has to be initialized, therefore a instance of band must exist
		return this.memberCount;
		//returns member count
	}
	
	public ArrayList<Member> getMembers(GregorianCalendar date) {
		//calendar and member has to be instanced
		return cal.getCalendarEvents(null, date, Member.class);
		//returns an ArrayList with the members of the band on a specific date
	}
	
	public void changeMemberStatus(Member member) {
		//band and member has to be instanced
		member.changeStatus();
		//changes the member status either to PermMember or to TempMember
	}

	public void addLocation(Location loc) {
		//locations and band has to be instanced
		this.locationList.add(loc);
		//adds a location that is available to the bands
	}
	
	public ArrayList<Location> findLocation(int capacity) {
		//locations and band has to be instanced; capacity has to be initialized
		ArrayList<Location> temp = new ArrayList<Location>();
		for (Location l : this.locationList) {
			//locationList has to exist
			if (l.getCapacity() >= capacity)
				//l.getCapacity() has to be int
				temp.add(l);
		}
		return temp;
		//finds an adequate location for the Band to host their event
	}
	
	public void addMusicEvent(GregorianCalendar date, MusicEvent event) {
		//calendar, musicEvent and band must be instanced
		cal.addEvent(date, event);
		for(Member m : getMembers(date)) {
			//member has to be instanced
			m.inform("New Event: " + event);
		}
		if(event.getTurnover() < 0)
			//getTurnover() has to return an int
			this.addExpenditure(date, -event.getTurnover());
		else
			this.addIncome(date, event.getTurnover());
		//adds music event to calendar and adds expenditures or income to the financial calendar
	}

	public void changeMusicEvent(GregorianCalendar date, MusicEvent oldevent, MusicEvent newevent) {
		//calendar, musicEvent and band must be instanced
		if (oldevent.getTurnover() > 0) {
			this.removeIncome(date, oldevent.getTurnover());
			this.addIncome(date, newevent.getTurnover());
		} else {
			this.removeExpenditure(date, -oldevent.getTurnover());
			this.addExpenditure(date, -newevent.getTurnover());
		}		
		for(Member m : getMembers(date)) {
			//member must not be null
			m.inform("Event changed: " +oldevent+ " to " + newevent);
		}
		cal.changeEvent(date, oldevent, newevent);
		//modifies specific event from calendar and changes finances from finance calendar
	}
	
	public void removeMusicEvent(GregorianCalendar date, MusicEvent event) {
		//calendar, musicEvent and band must be instanced
		if (event.getTurnover() > 0) {
			this.removeIncome(date, event.getTurnover());
		} else {
			this.removeExpenditure(date, -event.getTurnover());
		}
		
		for(Member m : getMembers(date)) {
			//member must not be null
			m.inform("Event removed: " + event);
		}
		cal.removeEvent(date, event);
		//removes specific event from calendar and removes finances from finance calendar
	}
	
	public ArrayList<MusicEvent> getMusicEvents(GregorianCalendar from, GregorianCalendar to) {
		//calendar, musicEvent and band must be instanced
		return cal.getCalendarEvents(from, to, MusicEvent.class);
		//returns music events from a specific time to a specific time
	}

	public void addIncome(GregorianCalendar date, double income) {
		//band and finance calendar has to be instanced; income must be positive
		financialcal.addEvent(date, new Earnings(income, date));
		//adds income to finance calendar
	}
	
	public void addExpenditure(GregorianCalendar date, double expenditure) {
		//band and finance calendar has to be instanced, expenditure must be positive
		financialcal.addEvent(date, new Spendings(expenditure, date));
		//adds expenditures to finance calendar
	}
	
	public void removeIncome(GregorianCalendar date, double income) {
		//band and finance calendar has to be instanced, income must be positive
		financialcal.removeEvent(date, new Earnings(income, date));
		//removes income from the financial calendar
	}
	
	public void removeExpenditure(GregorianCalendar date, double expenditure) {
		//band and finance calendar has to be instanced, expenditure must be positive
		financialcal.removeEvent(date, new Spendings(expenditure, date));
		//removes expenditure from the financial calendar
	}

	public double moneyGained(GregorianCalendar from, GregorianCalendar to) {
		//band and finance calendar has to be instanced, from and to be a calendar date
		double sum = 0;
		for(Earnings e : financialcal.getCalendarEvents(from, to, Earnings.class)) {
			sum += e.getIncome();
		}
		return sum;
		//returns the money the band gained with their concerts
	}

	public double moneySpent(GregorianCalendar from, GregorianCalendar to) {
		//band and finance calendar has to be instanced, from and to be a calendar date
		double sum = 0;
		for(Spendings s : financialcal.getCalendarEvents(from, to, Spendings.class)) {
				sum += s.getExpenditure();
		}
		return sum;
		//returns the money the band spent on their rehearsals
	}

	public double moneySituation(GregorianCalendar from, GregorianCalendar to) {
		//band and finance calendar has to be instanced, from and to be a calendar date
		return moneyGained(from, to) - moneySpent(from, to);
		//returns the band's money situation
	}
}	
