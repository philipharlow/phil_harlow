package model.Item;

import java.io.Serializable;

public abstract class Item implements Serializable{
	
	private String name;
	private String description;
	private int amount;
	
	/**
	 * <b>Item</b>
	 * <p>
	 * Creates a new Item
	 * 
	 * @param name The name of the Item
	 * 
	 * @param descripton A description of the Item
	 * 
	 * @author Philip Harlow
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.amount = 0;
	}
	
	/**
	 * <b>getName</b>
	 * <p>
	 * 
	 * Returns the name of the Item
	 * 
	 * @return String The name of the item
	 * 
	 * @author Philip Harlow
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * <b>getDescription</b>
	 * <p>
	 * 
	 * Returns the description of the Item
	 * 
	 * @return String The description of the item
	 * 
	 * @author Philip Harlow
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * <b>getAmount</b>
	 * <p>
	 * 
	 * Returns the amount of the Item
	 * 
	 * @return int The amount of the item
	 * 
	 * @author Philip Harlow
	 */
	public int getAmount() {
		return amount;
	}
}
