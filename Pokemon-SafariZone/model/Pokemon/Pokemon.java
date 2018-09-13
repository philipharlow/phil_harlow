package model.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Item.Bait;
import model.Item.Berry;
import model.Item.Candy;
import model.Item.Item;
import model.Item.LifeStone;
import model.Item.Potion;
import model.Item.Rock;
import model.Item.SafariBall;
import model.Trainer.Trainer;

public abstract class Pokemon implements Serializable{
	
	private String name;
	private Trainer trainer;
	private String type;
	private int healthPoints;
	private int maxHealth;
	private int runChance;
	private int captureChance;
	private int captureThreshhold;
	private boolean ranAway;
	private int maxDuration;
	private int currentDuration;
	private boolean captured;
	public List<Ability> abilities;
	private int attack;
	private int defense;
	public boolean inBattle;
	
	/**<b>Pokemon<b>
	 * <p>
	 * 
	 * Creates a new pokemon
	 * 
	 * @param name The name of the new pokemon
	 * 
	 * @param trainer The Trainer who is trying to capture the new pokemon
	 * 
	 * @param type The pokemons type
	 * 
	 * 
	 * @author Philip Harlow
	 */
	public Pokemon(String name, Trainer trainer, String type) {
		this.name = name;
		this.trainer = trainer;
		this.type = type;
		this.ranAway = false;
		this.currentDuration = 0;
		abilities = new ArrayList<Ability>();
		abilities.add(0, new AttackAbility());
		abilities.add(1, new RaiseDefenseAbility());
		abilities.add(2, new RaiseAttackAbility());
		abilities.add(3, new LowerTargetDefenseAbility());
	}
	
	/**<b>getName<b>
	 * <p>
	 * 
	 * Returns the name of the pokemon
	 * 
	 * @return name The name of the pokemon
	 * 
	 * @author Philip Harlow
	 */
	public String getName() {
		return this.name;
	}
	
	/**<b>getTrainer<b>
	 * <p>
	 * 
	 * Returns the trainer of the pokemon
	 * 
	 * @return trainer The trainer of the pokemon
	 * 
	 * @author Philip Harlow
	 */
	public Trainer getTrainer() {
		return this.trainer;
	}
	
	public abstract boolean runAway();
	
	/**<b>getCaptured<b>
	 * <p>
	 * 
	 * Returns the status of being captured of the pokemon
	 * 
	 * @return captured The state of the pokemon, captured or uncaptured
	 * 
	 * @author Philip Harlow
	 */
	public boolean getCaptured() {
		return this.captured;
	}
	
	public void useAbility(int index, Pokemon target) {
		Ability p = this.abilities.get(index);
		p.setAmount(this.getAttack());
		target.recieveAbility(p);
		
		if (p instanceof LowerTargetDefenseAbility) {
			if(this.getTrainer().getName().equals("Nick")) {
				System.out.println("Nick used Lower Defense!");
				System.out.println("\t" + target.getName() + " defense is now " + target.getDefense());
			}
			
			else {
				System.out.println(this.getTrainer().getName().toString() + " used Lower Defense!");
				System.out.println("\t" + target.getName() + " defense is now " + target.getDefense());
			}
			
		}
		
		if (p instanceof AttackAbility) {
			if(this.getTrainer().getName().equals("Nick")) {
				System.out.println("Nick used Attack!");
				System.out.println("\t" + target.getName() + " hp is now " + target.getHp());
			}
			else {
				System.out.println(this.getTrainer().getName().toString() + " used Attack!");
				System.out.println("\t" + target.getName() + " hp is now " + target.getHp());
			}
		}
		
		if (p instanceof RaiseAttackAbility) {
			if(this.getTrainer().getName().equals("Nick")) {
				System.out.println("Nick used Raise Attack!");
				System.out.println("\t" + target.getName() + " attack is now " + target.getAttack());
			}
			else {
				System.out.println(this.getTrainer().getName() + " used Raise Attack!");
				System.out.println("\t" + target.getName() + " attack is now " + target.getAttack());
			}
		}
		
		if (p instanceof RaiseDefenseAbility) {
			if(this.getTrainer().getName().equals("Nick")) {
				System.out.println("Nick used Raise Defense!");
				System.out.println("\t" + target.getName() + " defense is now " + target.getAttack());
			}
			else {
				System.out.println(this.getTrainer().getName().toString() + " used Raise Defense!");
				System.out.println("\t" + target.getName() + " defense is now " + target.getAttack());
			}
		}
	}
	
	public boolean recieveAbility(Ability ability) {
		if(ability instanceof AttackAbility) {
			setHealth(-(ability.getAmount() / getDefense()));
			//System.out.println(this.getHp());
			return true;
		}
		else if(ability instanceof RaiseDefenseAbility) {
			setDefense(5);
			return true;
		}
		else if(ability instanceof RaiseAttackAbility) {
			setAttack(5);
			return true;
		}
		else if(ability instanceof LowerTargetDefenseAbility) {
			setDefense(-5);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**<b>rock<b>
	 * <p>
	 * 
	 * Throwing a rock at the pokemon will make it more likely to run away, so we increase the run chance 
	 * and then call our runAway() method, if the pokemon ran away we return false(the rock failed and the pokemon ran)
	 * otherwise we increase the capture chance of the pokemon and return true.
	 * 
	 * @return boolean The status if the pokemon ran away or not
	 * 
	 * @author Philip Harlow
	 */
	private boolean rock() {
		this.runChance += 10;
		if(runAway()) {
			this.ranAway = true;
			return false;
		}
		else {
			this.currentDuration++;
			this.captureChance += 15;
			return true;
		}
	}
	
	/**<b>bait<b>
	 * <p>
	 * 
	 * Throwing bait at the pokemon will make it less likely to run away and less likely to be caught,
	 * so we decrease the run chance, and decrease the capture chance.
	 * 
	 * 
	 * @author Philip Harlow
	 */
	private void bait() {
		this.currentDuration++;
		this.runChance += -15;
		this.captureChance += -10;
	}
	
	/**<b>ball<b>
	 * <p>
	 * 
	 * Throwing a SafariBall will attempt to catch the pokemon, if successful the pokemon will be added to 
	 * the trainers list of pokemon. If unsuccessful we check if the pokemon ran away, if not we increase the
	 * pokemons runChance.
	 * 
	 * @return boolean The status if the pokemon was caught or not
	 * 
	 * @author Philip Harlow
	 */
	private boolean ball() {
		if(capture()) {
			return true;
		}
		else {
			if(runAway()) {
				this.ranAway = true;
			}
			else {
				this.currentDuration++;
				this.runChance += 5;
			}
			return false;
		}
	}
	
	public abstract boolean capture();

	/**<b>useItem<b>
	 * <p>
	 * 
	 * Using an item on a pokemon will increase its stats according to which item is used.
	 * 
	 * @param item The item the trainer wishes to use.
	 * 
	 * @return boolean The status if the pokemon used the item or not
	 * 
	 * @author Philip Harlow
	 */
	public boolean useItem(Item item) {
		if(item instanceof Potion) {
			setHealth(item.getAmount());
			return true;
		}
		else if(item instanceof Candy) {
			setAttack(item.getAmount());
			return true;
		}
		else if(item instanceof Berry) {
			setDefense(item.getAmount());
			return true;
		}
		else if(item instanceof LifeStone) {
			if(this.healthPoints > 0) {
				return false;
			}
			else {
				setHealth(this.maxHealth/2);
				return true;
			}
		}
		else if(item instanceof SafariBall) {
			return ball();
		}
		else if(item instanceof Rock) {
			return rock();
		}
		else if(item instanceof Bait) {
			bait();
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * <b>setTrainer</b>
	 * <p>
	 * Sets this pokemons trainer
	 * 
	 * @param trainer the new trainer for this pokemon
	 */
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public abstract void setHealth(int x);
	
	public abstract int getHp();
	
	public abstract int getAttack();
	
	public abstract int getDefense();
	
	public abstract void setAttack(int x);
	
	public abstract void setDefense(int x);
	
}