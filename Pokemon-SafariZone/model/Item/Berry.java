package model.Item;

import java.io.Serializable;

public class Berry extends Item implements Serializable {
	
	private int amount;
	
	/**
	 * <b>Berry</b>
	 * <p>
	 * Constructs a new Berry item. 
	 * 
	 * @param name The name of the Berry
	 * 
	 * @param descripton A description of the Berry item
	 * 
	 * @author Philip Harlow
	 */
	public Berry(String name, String description) {
		super(name, description);
		this.amount = 5;
	}
	/**
	 * <b>getAmount</b>
	 * <p>
	 * Gets the amount to change the Pokemon Defense to
	 * 
	 * @return int The amount to set new Pokemon Defense to
	 * 
	 * @author Philip Harlow
	 */
	public int getAmount() {
		return this.amount;
	}
}
