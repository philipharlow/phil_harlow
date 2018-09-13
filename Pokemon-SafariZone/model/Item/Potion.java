package model.Item;

import java.io.Serializable;

public class Potion extends Item implements Serializable{
	
	private int amount;
	/**
	 * <b>Potion</b>
	 * <p>
	 * Constructs a new Potion item. 
	 * 
	 * @param name The name of the Potion
	 * 
	 * @param descripton A description of the Potion item
	 * 
	 * @author Philip Harlow
	 */
	public Potion(String name, String description) {
		super(name, description);
		this.amount = 5;
	}
	/**
	 * <b>getAmount</b>
	 * <p>
	 * Gets the amount to add to a Pokemon's Health
	 * 
	 * @return int The amount to add to a Pokemon's Health
	 * 
	 * @author Philip Harlow
	 */
	public int getAmount() {
		return this.amount;
	}
}
