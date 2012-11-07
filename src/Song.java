/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Song implements ICalendarEntry {
	private final String title;
	// duration > 0
	private final int duration;
	
	public Song(String title, int duration) {
		// title != empty; duration > 0
		this.title=title;
		this.duration=duration;
	}
	
	public String getTitle() {
		return this.title;
		// returns stored title
	}
	
	public int getDuration() {
		return this.duration;
		// returns stored duration
	}
	
	public String toString() {
		return this.title + " (" + this.duration + " sec)";
		// returns song in a readable string
	}	
}	