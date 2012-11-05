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
		// name != (null or empty); capacity >= positive; concerts or rehearsals - at least one of them is true
		this.name = name;
		this.capacity = capacity;
		this.concerts = concerts;
		this.rehearsals = rehearsals;
	}
	
	public String getName() {
		return this.name;
		// returns stored name
	}
	
	public int getCapacity() {
		return this.capacity;
		// returns stored capacity
	}
	
	public boolean forConcerts() {
		return this.concerts;
		// returns stored concerts
	}
	
	public boolean forRehearsals() {
		return this.rehearsals;
		// returns stored rehearsals
	}
}
