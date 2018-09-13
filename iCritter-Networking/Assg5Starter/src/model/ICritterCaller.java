package model;

public class ICritterCaller {

	/**
	 * Main method to run and semi-test the code.
	 * Be sure to write JUnit tests to go along with this.
	 */
	public static void main(String args[]){
		ICritterWorld world = new ICritterWorld();
		
		ICritter curr = new ICritterDog("Spot",null);
		curr.addInterest("Anime");
		curr.addInterest("Nintendo");
		curr.addInterest("Music");
		curr.addInterest("RPG");
		curr.addInterest("Games");
		world.addICritter(curr);
		curr = new ICritterDog("Rover",null);
		curr.addInterest("Books");
		curr.addInterest("Buttons");
		curr.addInterest("Comedy");
		curr.addInterest("Comics");
		curr.addInterest("Manga");
		world.addICritter(curr);
		curr = new ICritterCat("Felix",null);
		curr.addInterest("Garlic");
		curr.addInterest("Nintendo");
		curr.addInterest("Buttons");
		curr.addInterest("Statistics");
		curr.addInterest("Science");
		world.addICritter(curr);
		curr = new ICritterCat("Socks",null);
		curr.addInterest("Canaries");
		curr.addInterest("Dogs");
		curr.addInterest("Music");
		curr.addInterest("Games");
		curr.addInterest("Books");
		curr.addInterest("Vampires");
		world.addICritter(curr);
		curr = new ICritterPenguin("Zbignew",null);
		curr.addInterest("Games");
		curr.addInterest("Music");
		curr.addInterest("Anime");
		curr.addInterest("Canaries");
		world.addICritter(curr);
		curr = new ICritterPenguin("Jermaine",null);
		curr.addInterest("Science");
		curr.addInterest("Buttons");
		curr.addInterest("Garlic");
		curr.addInterest("Games");
		curr.addInterest("Manga");
		world.addICritter(curr);
		
		world.runJamboree();//run the jamboree
		
		for(ICritter i : world.listICritters()){
			System.out.println("\n=====================================");
			System.out.println("Name: "+i.getName());
			System.out.print("Interests:");
			for(String p : i.listInterests())
				System.out.print(" "+p);
			System.out.println("");//new line after the interests
			System.out.println("Friends:");
			for(ICritter p : i.getFriends()){
				//a little bit of reflection here by retrieving the simple class name and using that
				//could have most definitely used a series of if statements and instance of though.
				System.out.println("  =>Name:"+p.getName()+" Type:"+p.getClass().getSimpleName());
			}
		}
	}
}
