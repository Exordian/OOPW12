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
		// name must not be null and should not be empty, capacity should be positive, concerts or rehearsals - at least one of them should be true
		this.name = name;
		this.capacity = capacity;
		this.concerts = concerts;
		this.rehearsals = rehearsals;
		// returns location instance
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
