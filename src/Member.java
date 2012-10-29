import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Member implements ICalendarEntry {
	private Calendar cal;
	private final String name;
	private final String tel;
	private final String instrument;
	private boolean permanent;
	
	public Member(String name, String tel, String instrument, boolean permanent) {
		// name, tel and instrument shouldnt be empty or null
		this.name=name;
		cal = new Calendar();
		this.tel=tel;
		this.instrument=instrument;
		this.permanent=permanent;
		// returns member instance
	}
	
	public String getName() {
		// name has to be initializied
		return this.name;
		// returns stored name
	}

	public String getTelNumber() {
		// tel has to be initializied
		return this.tel;
		// returns stored tel
	}
	
	public String getInstrument() {
		// intrument has to be initializied
		return this.instrument;
		// returns stored instrument
	}	

	public boolean getStatus() {
		return this.permanent;
		// returns stored permanent
	}

	public void changeStatus() {
		this.permanent = !this.getStatus();
		// status toggled
	}
	
	public void addSong(GregorianCalendar date, Song song) {
		// date and song must not be null
		cal.addEvent(date, song);
		// song added to calendar
	}

	public void removeSong(GregorianCalendar date, Song song) {
		// date and song must not be null
		cal.removeEvent(date, song);
		// song removed from calendar
	}
	
	public ArrayList<Song> getSongs(GregorianCalendar date) {
		// date must not be null and should be valid
		return cal.getCalendarEvents(null, date, Song.class);
		// returns arraylist of memer songs
	}

	public String getSongList(GregorianCalendar date) {
		// date must not be null
		ArrayList<Song> list= this.getSongs(date);
		String output = "";
		for (Song s : list) {
			output += s.toString() + "\n";
		}
		return output;
		// returns songlist in a readable string
	}
	
	public void inform(String text) {
		// text shoulnt be empty or null
	}
	
	public String toString() {
		return "Name: " +this.getName()+ 
		     ", Tel.nr.: " +this.getTelNumber()+ 
		     ", Instrument: " +this.getInstrument()+ ", is a "
			 + ((this.getStatus()) ? "permanent":"temporary") + " Member";
		// returns member in a readable string
	}
}
