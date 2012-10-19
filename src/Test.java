import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * 
 * @author Englisch (e1125164), Lenz (e1126963), Schuster (e1025700) 
 * @since October 2012
 * 
 */
public class Test {

	/** TESTING
	 * @param args
	 */
	public static void main(String[] args) {
		
		Band band1 = new Band("Lol");
		Band band2 = new Band("Rofl");
		
		Location graz = new Location("Graz", 4, true, true);
		Location wien = new Location("Wien", 5,  true, true);
		Location linz = new Location("Linz", 6,  true, true);
		Location berlin = new Location("Berlin", 4,  true, true);
		Location eisenstadt = new Location("Eisenstadt", 5,  false, true);
		Location hamburg = new Location("Hamburg", 4,  true, false);
		Location hinterstadl = new Location("Hinterstadl", 2, false, true);
		
		band1.addLocation(graz);
		band1.addLocation(wien);
		band1.addLocation(linz);
		band1.addLocation(berlin);
		band1.addLocation(eisenstadt);
		band1.addLocation(hamburg);
		band2.addLocation(hinterstadl);
		
		Member m1 = new PermMember("Hans", "0187487894", "Gitarre");
		Member m2 = new PermMember("Nina", "0198340121", "Klavier");
		Member m3 = new PermMember("Tim",  "0155555555", "Schlagzeug");
		Member m4 = new TempMember("Tom",  "0133333333", "E-Bass");
		band1.addMember(new GregorianCalendar(2012, 8, 13), m1); 	//add members
		band1.addMember(new GregorianCalendar(2012, 7,  9), m2);
		band1.addMember(new GregorianCalendar(2007, 9, 20), m3);
		band1.addMember(new GregorianCalendar(2009, 10, 3), m4);
		
		Member m1b = new PermMember("Jürgen", "0187487858", "Gitarre");
		band2.addMember(new GregorianCalendar(2012, 9, 5), m1b);
		
		Song s1 = new Song("dfdfd", 200);
		Song s2 = new Song("werwb",300);
		Song s3 = new Song("bbbbb", 400);
		Song s4 = new Song("enrbf", 180);
		
		System.out.println(band1.findLocation(band1.numberOfMembers())+ "\n");
		System.out.println(band2.findLocation(band2.numberOfMembers())+ "\n");
		
		Concert c1 = new Concert("Wien", new GregorianCalendar(2012,10,12),  900, 300);
		Concert c2 = new Concert("Graz", new GregorianCalendar(2012,10,20), 1800, 200);
		Concert c3 = new Concert("Linz", new GregorianCalendar(2011,11, 9), 3000, 500);
		Concert c4 = new Concert("Wien", new GregorianCalendar(2005,12,12), 2000, 100);
		Rehearsal r1 = new Rehearsal("Wien", new GregorianCalendar(2012,7,29),  900, 300);
		Rehearsal r2 = new Rehearsal("Berlin", new GregorianCalendar(2012,6,1),  900, 300);
		Rehearsal r3 = new Rehearsal("Eisenstadt", new GregorianCalendar(2011,8,3),  900, 300);
		Rehearsal r4 = new Rehearsal("Hamburg", new GregorianCalendar(2010,5,12),  900, 300);
		
		band1.addSong(new GregorianCalendar(2006, 9, 3), s1);		//add songs
		band1.addSong(new GregorianCalendar(2012, 5, 8), s2);
		band1.addSong(new GregorianCalendar(2008, 4, 6), s3);
		band1.addSong(new GregorianCalendar(2009, 2, 1), s4);
		
		band1.addMusicEvent(c1.getDate(), c1); 	//add concerts
		band1.addMusicEvent(c2.getDate(), c2);
		band1.addMusicEvent(c3.getDate(), c3);
		band1.addMusicEvent(c4.getDate(), c4);
		band1.addMusicEvent(r1.getDate(), r1); 	//add rehearsals
		band1.addMusicEvent(r2.getDate(), r2);
		band1.addMusicEvent(r3.getDate(), r3);
		band1.addMusicEvent(r4.getDate(), r4);
		System.out.println(band1.getSongList(new GregorianCalendar(2012, 10, 15)));
		
		band1.removeSong(new GregorianCalendar(2012, 5, 8), s2);
		System.out.println(band1.getSongList(new GregorianCalendar(2012, 10, 15)));
		
		ArrayList<MusicEvent> music_events = band1.getMusicEvents(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		String output = "";
		for(MusicEvent m : music_events) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		ArrayList<Member> members = band1.getMembers(new GregorianCalendar(2012, 10, 20));
		output = band1.getBandName() + ": \n";
		for(Member m : members) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		band1.removeMember(new GregorianCalendar(2012, 9, 31), m3);
		
		ArrayList<Member> members2 = band2.getMembers(new GregorianCalendar(2012, 9, 8));
			output = band2.getBandName() + ": \n";
		for(Member m : members2) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		
		//band1.changeMemberStatus(new GregorianCalendar(2012, 7,  9), m2);
		
		members = band1.getMembers(new GregorianCalendar(2012, 10, 20));
		output = band1.getBandName() + ": \n";
		for(Member m : members) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		
		int moneyGained = band1.moneyGained(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		int moneySpent  = band1.moneySpent(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		int moneySituation = band1.moneySituation(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		System.out.println("gained money: "+moneyGained+ ", spent money: " +moneySpent+ ", money situation: " +moneySituation);
		
		
	}

}
