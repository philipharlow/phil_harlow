package model.Item;

import java.io.Serializable;

public class Candy extends Item implements Serializable{
	
	private int amount;
	
	/**
	 * <b>Candy</b>
	 * <p>
	 * Constructs a new Candy item. 
	 * 
	 * @param name The name of the Candy
	 * 
	 * @param descripton A description of the Candy item
	 * 
	 * @author Philip Harlow
	 */
	public Candy(String name, String description) {
		super(name, description);
		this.amount = 5;
	}

	/**
	 * <b>getAmount</b>
	 * <p>
	 * Gets the amount to change the Pokemon Attack to
	 * 
	 * @return int The amount to set new Pokemon Attack to
	 * 
	 * @author Philip Harlow
	 */
	public int getAmount() {
		return amount;
	}

}
