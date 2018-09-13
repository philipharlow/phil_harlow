package model.Pokemon;

import java.io.Serializable;

import model.Trainer.Trainer;

public class CommonPokemon extends Pokemon implements Serializable{

	private int healthPoints;
	private int maxHealth;
	private int runChance;
	private int captureThreshhold;
	private int captureChance;
	private int currentDuration;
	private int maxDuration;
	private int attack;
	private int defense;
	
	/**<b>CommonPokemon<b>
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
	public CommonPokemon(String name, Trainer trainer, String type) {
		super(name, trainer, type);
		this.maxHealth = 10;
		this.healthPoints = this.maxHealth;
		this.runChance = 15;
		this.captureThreshhold = 5; //the pokemons health where it becomes capturable
		this.maxDuration = 15;
		this.attack = 5;
		this.defense = 5;
		this.captureChance = 80;
	}
	
	/**<b>capture<b>
	 * <p>
	 * 
	 * This method will simulate attempting to catch a pokemon. if the capture chance is
	 * high enough the pokemon will be caught.
	 * 
	 * @return boolean The status if the pokemon is caught or not
	 * 
	 * @author Philip Harlow
	 */
	public boolean capture() {
		//if(this.healthPoints > this.captureThreshhold) {
		//	return false;
		//}
		//else {
		
			int temp = (int) (Math.random() * ((100-1) +1));
			if(temp <= captureChance) {
				return true;
			}
			else {
				runChance += 5;
				return false;
			}
		//}
	}

	/**<b>runAway<b>
	 * <p>
	 * 
	 * Determines if the pokemon will run away after certain events.
	 * 
	 * @return boolean The status if the pokemon ran away or not
	 * 
	 * @author Philip Harlow
	 */
	public boolean runAway() {
		if(this.maxDuration < currentDuration) {
			return true;
		}
		int temp = (int) (Math.random() * ((100-1) +1));
		if(temp <= runChance) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * <b>getHp</b>
	 * 
	 * @return this pokemons current hp
	 * 
	 * @author Marcos Samayoa
	 */
	public int getHp() {
		return healthPoints;
	}
	
	/**
	 * <b>getattack</b>
	 * 
	 * @return this pokemons attack stat
	 * 
	 * @author Marcos Samayoa
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * <b>getDefense</b>
	 * 
	 * @return this pokemons defense stat
	 * 
	 * @author Marcos Samayoa
	 */
	public int getDefense() {
		return defense;
	}
	
	/**<b>setDefense<b>
	 * <p>
	 * 
	 * When a defensive boost item is used this method will increase the pokemons defense.
	 * 
	 * @param amount The amount to increase the pokemon's defense by.
	 * 
	 * 
	 * @author Philip Harlow
	 */
	public void setDefense(int amount) {
		this.defense += amount;
		if(this.defense <= 0) {
			this.defense = 1;
		}
	}

	/**<b>setAttack<b>
	 * <p>
	 * 
	 * When an offensive boost item is used this method will increase the pokemons offense.
	 * 
	 * @param amount The amount to increase the pokemon's offense by.
	 * 
	 * 
	 * @author Philip Harlow
	 */
	public void setAttack(int amount) {
		this.attack += amount;
	}
	
	/**<b>setHealth<b>
	 * <p>
	 * 
	 * When a healing item is used this method will increase the pokemons health.
	 * 
	 * @param amount The amount to increase the pokemon's health by.
	 * 
	 * 
	 * @author Philip Harlow
	 */
	public void setHealth(int amount) {
		if(this.maxHealth < (this.healthPoints+amount)) {
			this.healthPoints = this.maxHealth;
		}
		else {
			this.healthPoints += amount;
		}
	}
}