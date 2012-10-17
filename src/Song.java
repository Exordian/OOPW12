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
		this.title=title;
		this.duration=duration;
	}
	
	/**
	 * Returns the title of the Song.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the duration of the song.
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return this.duration;
	}
	
	public String toString() {
		return this.title + " (" + this.duration + " sec)";

	}	
}	