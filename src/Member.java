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
	private boolean permanent;
	
	public Member(String name, String tel, String instrument, boolean permanent) {
		this.name=name;
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
		if	(this.getStatus()==true)
			this.permanent=false;
		else
			this.permanent=true;
	}
	
	/**
	 * Inform Member about something
	 * 
	 * @param text to inform
	 */
	public void inform(String text) {
		// print to stdout emulates an information messaging service, this could be replaced by an email service, fb notice etc
		System.out.println("Member: "+name+" got informed about: "+text);
	}
	
	public String toString() {
		return "Name: " +this.getName()+ 
		     ", Tel.nr.: " +this.getTelNumber()+ 
		     ", Instrument: " +this.getInstrument()+ ", is a "
			 + ((this.getStatus() == true) ? "permanent":"temporary") + " Member";
	}
}
