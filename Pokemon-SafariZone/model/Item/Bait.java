package model.Item;

import java.io.Serializable;

public class Bait extends Item implements Serializable{
	/**
	 * <b>Bait</b>
	 * <p>
	 * Constructs a new Bait item. 
	 * 
	 * @param name The name of the Bait item
	 * 
	 * @param descripton A description of the Bait item
	 *
	 * @author Marcos Ayon
	 */
	public Bait(String name, String description) {
		super(name, description);
	}
}
