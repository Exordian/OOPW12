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
		this.name=name;
		cal = new Calendar();
		this.tel=tel;
		this.instrument=instrument;
		this.permanent=permanent;
	}
	
	/**
	 * Returns the name of the member.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the telephone number of the member.
	 * 
	 * @return telephone number
	 */
	public String getTelNumber() {
		return this.tel;
	}
	
	/**
	 * Returns the instrument the member is playing.
	 * 
	 * @return instrument
	 */
	public String getInstrument() {
		return this.instrument;
	}	
	
	/**
	 * Returns the status of the member.
	 * 
	 * @return true if permanent, false if temporary
	 */
	public boolean getStatus() {
		return this.permanent;
	}
	
	/**
	 * Changes the status of the member.
	 * 
	 */
	public void changeStatus() {
		this.permanent = !this.getStatus();
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
	 * Inform member about something.
	 * 
	 * @param text to inform
	 */
	public void inform(String text) {
		// print to sys.out emulates an information messaging service, this could be replaced by an email service, fb notice etc
		//System.out.println("Member: "+name+" got informed about: "+text);
	}
	
	public String toString() {
		return "Name: " +this.getName()+ 
		     ", Tel.nr.: " +this.getTelNumber()+ 
		     ", Instrument: " +this.getInstrument()+ ", is a "
			 + ((this.getStatus() == true) ? "permanent":"temporary") + " Member";
	}
}
