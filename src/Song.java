/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Song implements ICalendarEntry {
	private final String title;
	private final int duration;
	
	public Song(String title, int duration) {
		// title should not be empty, duration should be over 0
		this.title=title;
		this.duration=duration;
		// returns song instance
	}
	
	public String getTitle() {
		// title has to be initialized
		return this.title;
		// returns stored title
	}
	
	public int getDuration() {
		// duration has to be initialized
		return this.duration;
		// returns stored duration
	}
	
	public String toString() {
		// title, duration had to be set
		return this.title + " (" + this.duration + " sec)";
		// returns song in a readable string
	}	
}	