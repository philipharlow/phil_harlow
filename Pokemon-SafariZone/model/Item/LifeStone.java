package model.Item;

import java.io.Serializable;

public class LifeStone extends Item implements Serializable{

	/**
	 * <b>LifeStone</b>
	 * <p>
	 * Constructs a new LifeStone item. 
	 * 
	 * @param name The name of the LifeStone item
	 * 
	 * @param descripton A description of the LifeStone item
	 * 
	 * @author Philip Harlow
	 */
	public LifeStone(String name, String description) {
		super(name, description);
	}
	
	@Override
	public int getAmount() {
		return 0;
	}
}
