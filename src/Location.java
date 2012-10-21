/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Location {
	private String name ="";
	private int capacity = 0;
	private boolean concerts;
	private boolean rehearsals;
	
	public Location(String name, int capacity, boolean concerts, boolean rehearsals) {
		this.name = name;
		this.capacity = capacity;
		this.concerts = concerts;
		this.rehearsals = rehearsals;
	}
	
	/**
	 * Returns name of the location.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns capacity of the location.
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Returns boolean whether location can be used for concerts.
	 * 
	 * @return
	 */
	public boolean forConcerts() {
		return this.concerts;
	}
	
	/**
	 * Returns boolean whether location can be used for rehearsals.
	 * 
	 * @return
	 */
	public boolean forRehearsals() {
		return this.rehearsals;
	}
	
	/**
	 * Returns a message whether the location is for rehearsals, concerts or both.
	 * 
	 * @return message
	 */
	public String forWhat() {
		if (this.concerts == true && this.rehearsals == true) {
			return "Available for concerts and rehearsals.";
		} else if (this.concerts == true) {
			return "Available for concerts.";
		} else {
			return "Available for rehearsals.";
		}
	}	
}
