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
	
	public Band(){
		cal = new Calendar();
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
	 * Adds member.
	 * 
	 * @param date
	 * @param member
	 */
	public void addMember(GregorianCalendar date, Member member) {
		cal.addEvent(date, member);
	}
	
	/**
	 * Removes specific member.
	 * 
	 * @param date
	 * @param member
	 */
	public void removeMember(GregorianCalendar date, Member member) {
		cal.removeEvent(date, member);
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
	
	/**
	 * Adds song.
	 * 
	 * @param date
	 * @param song
	 */
	public void addSong(GregorianCalendar date, Song song) {
		cal.addEvent(date, song);
	}

	/**
	 * Removes song.
	 * 
	 * @param date
	 * @param song
	 */
	public void removeSong(GregorianCalendar date, Song song) {
		cal.removeEvent(date, song);
	}
	
	/**
	 * Returns specific song.
	 * 
	 * @param date
	 * @return 
	 */
	public ArrayList<Song> getSongs(GregorianCalendar date) {
		return cal.getCalendarEvents(null, date, Song.class);
	}

	/**
	 * Returns the repertoire at a specific time.
	 * 
	 * @param date
	 * @return ArrayList with songs.
	 */
	public String getSongList(GregorianCalendar date) {
		ArrayList<Song> list= this.getSongs(date);
		String output = "";
		for (Song s : list) {
			output += s.toString() + "\n";
		}
		return output;
	}

	/**
	 * Adds music event to calendar.
	 * 
	 * @param date
	 * @param member
	 */
	public void addMusicEvent(GregorianCalendar date, MusicEvent event) {
		cal.addEvent(date, event);
	}

	/**
	 * Removes specific Event from calendar.
	 * 
	 * @param date
	 * @param event
	 */
	public void removeMusicEvent(GregorianCalendar date, MusicEvent event) {
		cal.removeEvent(date, event);
	}
	
	/**
	 * Returns music events from a specific time to a specific time.
	 * 
	 * @param from
	 * @param to
	 * @return Arraylist with specific music events.
	 */
	public ArrayList<MusicEvent> getMusicEvents(GregorianCalendar from, GregorianCalendar to) {
		return cal.getCalendarEvents(from, to, MusicEvent.class);
	}

	/**
	 * Returns the money the band gained with their concerts.
	 * 
	 * @param from
	 * @param to
	 * @return Sum of money gained
	 */
	public int moneyGained(GregorianCalendar from, GregorianCalendar to) {
		int sum = 0;
		for(MusicEvent e : getMusicEvents(from, to)) {
			if(e instanceof Concert)
				sum += ((Concert)e).getSalary();
		}
		return sum;
	}
	
	/**
	 * Returns the money the band spent on their rehearsals.
	 * 
	 * @param from
	 * @param to
	 * @return Sum of money spent
	 */
	public int moneySpent(GregorianCalendar from, GregorianCalendar to) {
		int sum = 0;
		for(MusicEvent e : getMusicEvents(from, to)) {
			if(e instanceof Rehearsal)
				sum += ((Rehearsal)e).getRent();
		}
		return sum;
	}
	
	/**
	 * Returns the band's money situation.
	 * 
	 * @param from
	 * @param to
	 * @return Difference of money gained and money spent
	 */
	public int moneySituation(GregorianCalendar from, GregorianCalendar to) {
		return moneyGained(from, to) - moneySpent(from, to);
	}

}
