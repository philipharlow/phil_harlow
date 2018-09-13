package model.Item;

import java.io.Serializable;

public class SafariBall extends Item implements Serializable{

	/**
	 * <b>SafariBalls</b>
	 * <p>
	 * Constructs a new SafariBalls item. 
	 * 
	 * @param name The name of the SafariBalls item
	 * 
	 * @param descripton A description of the SafariBalls item
	 *
	 * @author Marcos Ayon
	 */
	public SafariBall(String name, String description) {
		super(name, description);
	}

	@Override
	public int getAmount() {
		return 0;
	}

}
