/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Member implements ICalendarEntry {
	private final String name;
	private final String tel;
	private final String instrument;
	
	public Member(String name, String tel, String instrument) {
		this.name=name;
		this.tel=tel;
		this.instrument=instrument;
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
	
	public String toString() {
		return "Name: " +this.getName()+ 
		     ", Telefonnr.: " +this.getTelNumber()+ 
		     ", Instrument: " +this.getInstrument();
	}
	
}
