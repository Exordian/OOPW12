/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class PermMember extends Member {

	public PermMember(String name, String tel, String instrument) {
		super(name, tel, instrument);
	}
	
	public String toString() {
		return super.toString() + ", permanent Member!";
	}
}
