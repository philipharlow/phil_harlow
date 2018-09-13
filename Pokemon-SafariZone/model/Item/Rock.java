package model.Item;

import java.io.Serializable;

public class Rock extends Item implements Serializable{

	/**
	 * <b>Rock</b>
	 * <p>
	 * Constructs a new Rock item. 
	 * 
	 * @param name The name of the Rock item
	 * 
	 * @param descripton A description of the Rock item
	 *
	 * @author Marcos Ayon
	 */
	public Rock(String name, String description) {
		super(name, description);
	}
}
