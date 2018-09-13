package model.Trainer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Item.Bait;
import model.Item.Berry;
import model.Item.Candy;
import model.Item.LifeStone;
import model.Item.Potion;
import model.Item.Rock;
import model.Item.SafariBall;
import model.Map.Map;
import model.Pokemon.CommonPokemon;
import model.Pokemon.Pokemon;
import model.Pokemon.RarePokemon;
import model.Pokemon.UncommonPokemon;

public class Trainer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int hp;
	private int steps;
	private int xPos;
	private int yPos;
	private String Gender;
	private List<Pokemon> pokemonList;
	private List<SafariBall> safariBallList;
	private List<Potion> potionList;
	private List<Candy> candyList;
	private List<Berry> berryList;
	private List<LifeStone> lifestoneList;
	private String[] commonPokemon;
	private String[] uncommonPokemon;
	private String[] rarePokemon;
	private Pokemon activePokemon;
	private Map map;
	private boolean onLeftMap;
	
	
	/**
	 * <b>Trainer</b>
	 * <p>
	 * Constructs a new trainer. 
	 * 
	 * @Note: the trainers active pokemon is not set 
	 * during construction.
	 * @param name the name of the trainer
	 * @param map the map the trainer is on
	 * @param gender the gender of this trainer
	 */
	public Trainer(String name, Map map, String Gender) {
		this.name = name;
		hp    = 100;
		steps = 500;  // Steps remaining
		xPos  = 1;
		yPos  = 25;
		pokemonList   = new LinkedList<Pokemon>();
		this.Gender = Gender;
		safariBallList = new LinkedList<SafariBall>();
		potionList     = new LinkedList<Potion>();
		candyList      = new LinkedList<Candy>();
		berryList      = new LinkedList<Berry>();
		lifestoneList  = new LinkedList<LifeStone>();
		activePokemon  = null;  // activePokemon must be changed in changeActivePokemon()
		this.map = map;
		onLeftMap = false;
		commonPokemon   = new String[] {"Pikachu", "Pidgey", "Rattata", "Growlithe", "Bulbasaur",
										"Sandshrew", "Diglett", "Ekans"};
		uncommonPokemon = new String[] {"Ninetales", "Pinsir", "Scyther", "Charmander", "Squirtle",
										"Ponyta", "Abra", "Eevee"};
		rarePokemon     = new String[] {"Zapdos", "Mewtwo", "Articuno", "Moltres", "Onix", "Lapras",
										"Snorlax", "Magmar"};
		
		for(int i=0; i<30; i++) {
			safariBallList.add(new SafariBall("SafariBall", "SafariBall"));
			if(i % 6 == 0) {
				potionList.add(new Potion("Potion", "Potion"));
				candyList.add(new Candy("Candy", "Candy"));
				berryList.add(new Berry("Berry", "Berry"));
				lifestoneList.add(new LifeStone("Lifestone", "Lifestone"));
				
			}
		}
	}
	
	/**
	 * <b>getName</b>
	 * <p>
	 * Returns the name of this trainer
	 *  
	 * @return String, the name of this trainer
	 * 
	 * @author Marcos Samayoa
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <b>getSteps</b>
	 * <p>
	 * Returns the number of steps this trainer has left
	 * before the game ends.
	 * 
	 * @return int, the number of steps remaining 
	 *  
	 * @author Marcos Samayoa
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * <b>getGender</b>
	 * 
	 * @return String the gender of this trainer
	 */
	public String getGender() {
		return Gender;
	}
	
	/**
	 * <b>getHp</b>
	 * <p>
	 * Returns the trainers remaining hp
	 * 
	 * @return int trainers remaining hp
	 * 
	 * @author Marcos Samayoa
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * <b>getPokemon</b>
	 * <p>
	 * Returns a list of this trainers current pokemon
	 *  
	 * @return a linkedList of pokemon
	 * 
	 * @author Marcos Samayoa
	 */
	public List<Pokemon> getPokemon(){
		return pokemonList;
	}
	
	
	/**
	 * <b>getPotions</b>
	 * <p>
	 * Returns a list of the potions this trainer has.
	 * @return List<potions>
	 */
	public List<Potion> getPotions(){
		return potionList;
	}
	
	
	/**
	 * <b>getCandies</b>
	 * <p>
	 * Returns a list of the candies this trainer has.
	 * @return List<Candy>
	 */
	public List<Candy> getCandies(){
		return candyList;
	}
	
	
	/**
	 * <b>getLifestones</b>
	 * <p>
	 * Returns a list of the lifestones this trainer has.
	 * @return List<LifeStone>
	 */
	public List<LifeStone> getLifestones(){
		return lifestoneList;
	}
	
	/**
	 * <b>getBerries</b>
	 * <p>
	 * Returns a list of the berries this trainer has.
	 * @return List<Berry>
	 */
	public List<Berry> getBerries(){
		return berryList;
	}
	
	/**
	 * <b>getSafariBalls</b>
	 * <p>
	 * Returns a list of the safariBalls this trainer has.
	 * @return List<SafariBall>
	 */
	public List<SafariBall> getSafariBalls(){
		return safariBallList;
	}
	
	/**
	 * <b>getOnLeftMap</b>
	 * <p>
	 * Returns a boolean indicating if the trainer is on the 
	 * left map or the right map.
	 * 
	 * @return boolean 
	 */
	public boolean getOnLeftMap() {
		return onLeftMap;
	}
	
	
	
	/**
	 * <b>generatePokemon</b>
	 * <p>
	 * Randomly chooses a pokemon and returns it.
	 * 
	 * @return a pokemon
	 */
	public Pokemon generatePokemon() {
		int max = 10;
		int min = 1;
		Random rand = new Random();
		int rollType = (int) rand.nextInt(max - min) + min;
		int rollName = (int) rand.nextInt(6-1);
		
		if(rollType > 8) {
			RarePokemon pokemon = new RarePokemon(rarePokemon[rollName], null, "Unknown");
			return pokemon;
		}
		else if(rollType > 5) {
			UncommonPokemon pokemon = new UncommonPokemon(uncommonPokemon[rollName], null, "Unknown");
			return pokemon;
		}
		else {
			CommonPokemon pokemon = new CommonPokemon(commonPokemon[rollName], null, "Unknown");
			return pokemon;
		}
	}
	
	/**
	 * <b>getXPos</b>
	 * <p>
	 * Returns this trainers current x position
	 * 
	 * @return this trainers current x Position
	 * 
	 * @author Marcos Samayoa
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * <b>getYPos</b>
	 * <p>
	 * Returns this trainers current y position
	 * 
	 * @return this trainers current y Position
	 * 
	 * @author Marcos Samayoa
	 */
	public int getYPos() {
		return yPos;
	}
	
	
	/**
	 * <b>outOfSafariBalls</b>
	 * <p>
	 * Indicates whether this trainer has any safari balls
	 * left
	 * 
	 * @return true if this trainer is out of safari balls
	 * @return false if this trainer still has safari balls
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean outOfSafariBalls() {
		if(safariBallList.size() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * <b>battlePokemon</b>
	 * <p>
	 *  This function controls a battle between this trainer and
	 *  the pokemon.
	 *  
	 *  @param opponent the pokemon this trainer is battling
	 *  
	 *  @author Marcos Samayoa
	 */
	public void battlePokemon(Pokemon opponent) {
		while(true) {
			System.out.println("====================================");
			System.out.println(name + " is in battle with: " + opponent.getName());
			System.out.println("Enter S to throw a safari ball (" + safariBallList.size() + " remaining)");
			System.out.println("Enter B to throw bait");
			System.out.println("Enter R to throw a rock");
			System.out.println("====================================");
			
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			
			if(input.equals("S") || input.equals("s")) {
				boolean caught = throwPokeball(opponent);
				System.out.println(name + " threw a safari ball!");
				if(caught) {
					System.out.println(name + " caught " + opponent.getName() + "!");
					addPokemon(opponent);
					break;
				}
				else if(safariBallList.size() == 0) {
					System.out.println("Out of safari Balls");
					break;
				}
				else {
					System.out.println("...");
					System.out.println("...");
					System.out.println("...");
					System.out.println(opponent.getName() + " Escaped from the safari ball");
				}
			}
			
			else if(input.equals("B") || input.equals("b")) {
				throwBait(opponent);
				System.out.println(name + " threw bait!");
			}
			
			else if(input.equals("R") || input.equals("r")) {
				boolean stillHere = throwRock(opponent);
				System.out.println(name + " Threw a rock!");
				if(!stillHere) {
					System.out.println(opponent.getName() + " ran away!");
					break;
				}
			}
			
			else {
				System.out.println("Unknown Command, please try again.");
			}
			
		}
	}
	
	/**
	 * <b>throwPokeball</b>
	 * <p>
	 * Attempts to catch the target pokemon by throwing a
	 * Safari ball. The probability of a catch depends on
	 * the pokemon.
	 * 
	 * @param target the pokemon this trainer is attempting 
	 * to catch.
	 * 
	 * @return true if the pokemon is caught
	 * @return false if the pokemon is not caught
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean throwPokeball(Pokemon target) {
		if(safariBallList.size() > 0) {
			boolean captured = target.useItem(safariBallList.get(0));
			safariBallList.remove(0);
			if(captured) {
				addPokemon(target);
				target.setTrainer(this);
			}
			return captured;
		}
		else {
			System.out.println("Out of Safari Balls! Thanks for playing!");
			return false;
		}
	}
	
	/**
	 * <b>throwRock</b>
	 * <p>
	 * Throws a rock at the target pokemon
	 * 
	 * @param target
	 * 
	 * @return true if the pokemon is still in battle,
	 * false if the pokemon ran away
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean throwRock(Pokemon target) {
		return target.useItem(new Rock("Rock","Rock"));
	}
	
	/**
	 * <b>throwBait</b>
	 * <p>
	 * Throws bait at the target pokemon
	 * 
	 * @param target
	 * 
	 * @author Marcos Samayoa
	 */
	public void throwBait(Pokemon target) {
		target.useItem(new Bait("Bait","Bait"));
	}
	
	/**
	 * <b>giveBerry</b>
	 * <p>
	 * Gives a berry to the target pokemon,
	 * The target pokemon must be the trainers own
	 * pokemon, not a wild pokemon
	 * 
	 * @see Items
	 * @param target
	 * 
	 * @return true if the target pokemon is the trainer's
	 * pokemon.
	 * @return false if the target pokemon is not the trainer's
	 * pokemon
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean giveBerry(Pokemon target) {
		for(Pokemon pokemon : pokemonList) {
			if(target.getName() == pokemon.getName()) {
				if(berryList.size() > 0) {
					boolean rc = pokemon.useItem(berryList.get(0));
					if(rc == true) {
						berryList.remove(0);
						System.out.println("Berry given to " + target.getName());
					}
					return true;
				}
			}
		}
		System.out.println("Out of Berries");
		return false; 
	}
	
	/**
	 * <b>givePotion</b>
	 * <p>
	 * Gives a Potion to the target pokemon,
	 * The target pokemon must be the trainers own
	 * pokemon, not a wild pokemon.
	 * 
	 * @see Items
	 * @param target
	 * 
	 * @return true if the target pokemon is the trainer's
	 * pokemon.
	 * @return false if the target pokemon is not the trainer's
	 * pokemon
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean givePotion(Pokemon target) {
		for(Pokemon pokemon : pokemonList) {
			if(target.getName() == pokemon.getName()) {
				if(potionList.size() > 0) {
					if(pokemon.useItem(potionList.get(0))) {
						potionList.remove(0);
						System.out.println("Potion given to " + target.getName());
					}
					return true;
				}
			}
		}
		System.out.println("Out of Potions");
		return false; 
	}
	
	/**
	 * Gives a lifestone to the target Pokemon.
	 * 
	 * @param target the pokemon to give a lifestone to
	 * 
	 * @return true if the target pokemon accepted the lifestone
	 */
	public boolean giveLifestone(Pokemon target) {
		for(Pokemon pokemon : pokemonList) {
			if(target.getName() == pokemon.getName()) {
				if(lifestoneList.size() > 0) {
					if(pokemon.useItem(lifestoneList.get(0))) {
						lifestoneList.remove(0);
						System.out.println("Lifestone given to " + target.getName());
					}
					return true;
				}
			}
		}
		System.out.println("Out of Lifestones");
		return false; 
	}
	
	/**
	 * Gives a candy to the target Pokemon.
	 * 
	 * @param target the pokemon to give a candy to
	 * 
	 * @return true if the target pokemon accepted the candy
	 */
	public boolean givecandy(Pokemon target) {
		for(Pokemon pokemon : pokemonList) {
			if(target.getName() == pokemon.getName()) {
				if(candyList.size() > 0) {
					if(pokemon.useItem(candyList.get(0))) {
						candyList.remove(0);
						System.out.println("Candy given to " + target.getName());
						return true;
					}
					
				}
			}
		}
		System.out.println("Out of Candy");
		return false; 
	}
	
	/**
	 * <b>changeActivePokemon</b>
	 * <p>
	 * Changes this trainers activePokemon, the pokemon that is 
	 * sent out during battles.
	 * 
	 * @param newPokemon, the new pokemon to set as active
	 */
	public void changeActivePokemon(Pokemon newPokemon) {
		activePokemon = newPokemon;
		return;
	}
	
	
	/**
	 * <b>getActivePokemon</b>
	 * <p>
	 * Returns this trainers active Pokemon
	 * 
	 * @return Pokemon this trainers active pokemon
	 */
	public Pokemon getActivePokemon() {
		return activePokemon;
	}
	
	/**
	 * <b>moveRight</b>
	 * <p>
	 * Changes the trainers current position by one to 
	 * the right
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean moveRight() {
		// Check if we are able to move right before moving right
		if(xPos+1 >= map.getXdimension()) {
			return false; 
		}
		if(map.getMapObjects()[xPos+1][yPos].getTraverseStatus()) {
			xPos += 1;
			return true;
		}
		
		return false;
	}
	
	/**
	 * <b>moveLeft</b>
	 * <p>
	 * Changes the trainers current position by one to 
	 * the left
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean moveLeft() {
		// Check if we are able to move left before moving left
		if(xPos-1 < 0) {
			return false; // 
		}
		if(map.getMapObjects()[xPos-1][yPos].getTraverseStatus()) {
			xPos -= 1;
			return true;
		}
		return false;
	}
	
	/**
	 * <b>moveUp</b>
	 * <p>
	 * Changes the trainers current position by one up
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean moveUp() {
		if(yPos-1 < 0) {
			return false;
		}
		if(map.getMapObjects()[xPos][yPos-1].getTraverseStatus()) {
			yPos -= 1;
			return true;
		}
		return false;
	}
	
	/**
	 * <b>moveDown</b>
	 * <p>
	 * Changes the trainers current position by one down
	 * 
	 * @author Marcos Samayoa
	 */
	public boolean moveDown() {
		if(yPos+1 > map.getYdimension()) {
			return false;
		}
		if(map.getMapObjects()[xPos][yPos+1].getTraverseStatus()) {
			yPos += 1;
			return true;
		}
		return false;
	}
	
	/**
	 * <b>newPokemon</b>
	 * <p>
	 * Adds the newPokemon to this trainers pokemon list
	 * 
	 * @param newPokemon
	 * 
	 * @author Marcos Samayoa
	 */
	public void addPokemon(Pokemon newPokemon) {
		pokemonList.add(newPokemon);
		if(activePokemon == null) {
			changeActivePokemon(newPokemon);
		}
	}
	
	
	/**
	 * <b>changeMap</b>
	 * <p>
	 * Changes this trainers map.
	 * 
	 * @param Map newMap the new map for this trainer
	 * @param Boolean bool true if the new map is the left map
	 */
	public void changeMap(Map newMap, Boolean bool) {
		this.map = newMap;
		if(bool) { //if bool is true, it is moving to left map
			this.xPos = 48;
			this.yPos = 25;
			this.onLeftMap = true;
		}
		else {
			this.xPos = 1;
			this.yPos = 25;
		}
	}
}
