import java.math.BigDecimal;
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
		/*
		 BAD: finances should be abstract, because we only want to use earnings and spendings
		 BAD: finances could be bound to musicevents, to navigate between them
		 GOOD: calendar and band are fully independent
		 GOOD: visibility modifications used properly
		 ERROR: song should list a group of members which are required to play the song instead of adding songs to only one member
		 
		 comments starting with retruns could be obvious due to the return statement
		 */
		
		
		Band band1 = new Band("Lol");				//add bands
		Band band2 = new Band("Rofl");
		
		Location graz = new Location("Graz", 4, true, true);					//add locations
		Location wien = new Location("Wien", 5,  true, true);
		Location linz = new Location("Linz", 6,  true, true);
		Location berlin = new Location("Berlin", 4,  true, true);
		Location eisenstadt = new Location("Eisenstadt", 5,  false, true);
		Location hamburg = new Location("Hamburg", 4,  true, false);
		Location hinterstadl = new Location("Hinterstadl", 2, false, true);
		
		band1.addLocation(graz);				//add locations to bands
		band1.addLocation(wien);
		band1.addLocation(linz);
		band1.addLocation(berlin);
		band1.addLocation(eisenstadt);
		band1.addLocation(hamburg);
		
		band2.addLocation(hinterstadl);
		
		Member m1 = new Member("Hans", "0187487894", "Gitarre", true);		//create members for band1s
		Member m2 = new Member("Nina", "0198340121", "Klavier", true);
		Member m3 = new Member("Tim",  "0155555555", "Schlagzeug", true);
		Member m4 = new Member("Tom",  "0133333333", "E-Bass", false);
		
		band1.addMember(new GregorianCalendar(2012, 8, 13), m1); 	//add members to band1
		band1.addMember(new GregorianCalendar(2012, 7,  9), m2);
		band1.addMember(new GregorianCalendar(2007, 9, 20), m3);
		band1.addMember(new GregorianCalendar(2009, 10, 3), m4);
		
		Member m1b = new Member("Juergen", "0187487858", "Gitarre", true);	//create members for band2
		Member m2b = new Member("Jens", "0187492858", "Bass", true);
		band2.addMember(new GregorianCalendar(2012, 9, 5), m1b);			//add members to band2
		band2.addMember(new GregorianCalendar(2012, 9, 6), m2b);
		
		Song s1 = new Song("hearthfire", 200);				//create songs	
		Song s2 = new Song("dawnguard",300);
		Song s3 = new Song("skyrim", 400);
		Song s4 = new Song("dat song", 180);
		
		System.out.println(band1.getBandName() + " can use this/these location/s:");				//print locations for band1
		ArrayList<Location> locationList = band1.findLocation(band1.numberOfMembers());
		String output = "";
		for(Location l : locationList) {
			output += l.getName() + ", " + forWhat(l) + "\n";
		}
		System.out.println(output);

		System.out.println(band2.getBandName() + " can use this/these location/s:");			//print locations for band2
		ArrayList<Location> locationList2 = band2.findLocation(band2.numberOfMembers());
		output = "";
		for(Location l : locationList2) {
			output += l.getName() + ", " + forWhat(l) + "\n";
		}
		System.out.println(output);
		
		Concert c1 = new Concert(wien, new GregorianCalendar(2012,10,12),  900, new BigDecimal(300));		//create concerts
		Concert c2 = new Concert(graz, new GregorianCalendar(2012,10,20), 1800, new BigDecimal(200));
		Concert c3 = new Concert(linz, new GregorianCalendar(2011,11, 9), 3000, new BigDecimal(500));
		Concert c4 = new Concert(wien, new GregorianCalendar(2011,12,12), 2000, new BigDecimal(100));
		
		Rehearsal r1 = new Rehearsal(wien, new GregorianCalendar(2012,7,29),  900, new BigDecimal(300));	//create rehearsals
		Rehearsal r2 = new Rehearsal(berlin, new GregorianCalendar(2012,6,1),  900, new BigDecimal(300));
		Rehearsal r3 = new Rehearsal(eisenstadt, new GregorianCalendar(2011,8,3),  900, new BigDecimal(300));
		Rehearsal r4 = new Rehearsal(hamburg, new GregorianCalendar(2010,5,12),  900, new BigDecimal(300));
		
		m1.addSong(new GregorianCalendar(2006, 9, 3), s1);		//add songs
		m3.addSong(new GregorianCalendar(2012, 5, 8), s2);
		m2.addSong(new GregorianCalendar(2008, 4, 6), s3);
		m3.addSong(new GregorianCalendar(2009, 2, 1), s4);

		band1.addMusicEvent(c1.getDate(), c1); 	//add concerts
		band1.addMusicEvent(c2.getDate(), c2);
		band1.addMusicEvent(c3.getDate(), c3);
		band1.addMusicEvent(c4.getDate(), c4);
		
		band1.addMusicEvent(r1.getDate(), r1); 	//add rehearsals
		band1.addMusicEvent(r2.getDate(), r2);
		band1.addMusicEvent(r3.getDate(), r3);
		band1.addMusicEvent(r4.getDate(), r4);
		
		band1.changeMusicEvent(c4.getDate(), c4, new Concert(linz, c4.getDate(), c4.getDuration(), c4.getSalary()));	//change music event
				
		for(MusicEvent m : band1.getMusicEvents(null, null)) {		//print music events after change
			System.out.println(m);
		}
		System.out.println("\n");
		
		System.out.println(m3.getName() + ": " + m3.getSongList(new GregorianCalendar(2012, 10, 14)));	//print m3 song list
		m3.removeSong(new GregorianCalendar(2012, 5, 8), s2);											//remove song from m3
		System.out.println(m3.getName() + ": " + m3.getSongList(new GregorianCalendar(2012, 10, 15)));	//print again to see if remove works
		
		System.out.println("Get Full Songlist: ");				//different song lists per member
		for(Member m : band1.getMembers(null)) {
			System.out.println(m.getName() +"'s Songlist: ");
			System.out.print(m.getSongList(null) + "\n");
		}
		System.out.println("\n" + m3.getName() + " 's Songlist: " + m3.getSongList(new GregorianCalendar(2012, 10, 15))); //single song list

		
		System.out.println("Events:");
		ArrayList<MusicEvent> music_events = band1.getMusicEvents(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24)); //music events of band1
		output = band1.getBandName() + ":"+"\n";
		for(MusicEvent m : music_events) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		
		ArrayList<Member> members = band1.getMembers(new GregorianCalendar(2012, 10, 20));	//band1 member list
		output = band1.getBandName() + ": \n";
		for(Member m : members) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		band1.removeMember(new GregorianCalendar(2012, 9, 31), m3);		//remove member from band1
		
		ArrayList<Member> members2 = band2.getMembers(new GregorianCalendar(2012, 9, 8));	//band2 member list
			output = band2.getBandName() + ": \n";
		for(Member m : members2) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		
		band1.changeMemberStatus(m2);				//change member status
		
		members = band1.getMembers(new GregorianCalendar(2012, 10, 20));	//member after member change
		output = band1.getBandName() + ": \n";
		for(Member m : members) {
			output += m.toString() +"\n";
		}
		System.out.println(output);
		
		BigDecimal moneyGained = band1.moneyGained(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));			//money situation
		BigDecimal moneySpent  = band1.moneySpent(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		BigDecimal moneySituation = band1.moneySituation(new GregorianCalendar(2009,1,1), new GregorianCalendar(2012, 12, 24));
		System.out.println(band1.getBandName() + ":\ngained money: "+moneyGained+ ", spent money: " +moneySpent+ ", money situation: " +moneySituation); //output: finances
		
		
	}

	public static String forWhat(Location l) {
		if (l.forConcerts() && l.forRehearsals()) {
			return "Available for concerts and rehearsals.";
		} else if (l.forConcerts()) {
			return "Available for concerts.";
		} else { // ERROR: should be else if (l.forRehearsal()) and else "location not usable"
			return "Available for rehearsals.";
		}
		// returns location usage in a readable string
	}	


}
