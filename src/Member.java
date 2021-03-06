import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Member implements ICalendarEntry {
	// cal != null
	private Calendar cal;
	// name != null
	private final String name;
	// tel != null
	private final String tel;
	// instrument != null
	private final String instrument;
	private boolean permanent;
	
	public Member(String name, String tel, String instrument, boolean permanent) {
		// name, tel and instrument != (empty or null)
		this.name=name;
		cal = new Calendar();
		this.tel=tel;
		this.instrument=instrument;
		this.permanent=permanent;
		// returns member instance
	}
	
	public String getName() {
		// name has to be initialized
		return this.name;
		// returns stored name
	}

	public String getTelNumber() {
		// tel has to be initialized
		return this.tel;
		// returns stored tel
	}
	
	public String getInstrument() {
		// intrument has to be initialized
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
		// date and song != null
		cal.addEvent(date, song);
		// song added to calendar
	}

	public void removeSong(GregorianCalendar date, Song song) {
		// date and song != null
		cal.removeEvent(date, song);
		// song removed from calendar
	}
	
	public ArrayList<Song> getSongs(GregorianCalendar date) {
		// date != null, date is valid
		return cal.getCalendarEvents(null, date, Song.class);
		// returns arraylist of member songs
	}

	public String getSongList(GregorianCalendar date) {
		// date != null
		ArrayList<Song> list= this.getSongs(date);
		String output = "";
		for (Song s : list) {
			output += s.toString() + "\n";
		}
		return output;
		// returns songlist in a readable string
	}
	
	public void inform(String text) {
		// text != (empty or null)
	}
	
	public String toString() {
		return "Name: " +this.getName()+ 
		     ", Tel.nr.: " +this.getTelNumber()+ 
		     ", Instrument: " +this.getInstrument()+ ", is a "
			 + ((this.getStatus()) ? "permanent":"temporary") + " Member";
		// returns member in a readable string
	}
}
