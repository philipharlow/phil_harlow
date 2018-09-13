package model.Item;

import java.io.Serializable;

public class EscapeRope extends Item implements Serializable{
	/**
	 * <b>EscapeRope</b>
	 * <p>
	 * Constructs a new EscapeRope item. 
	 * 
	 * @param name The name of the EscapeRope
	 * 
	 * @param descripton A description of what the EscapeRope item does
	 *
	 * @author Marcos Ayon
	 */
	public EscapeRope(String name, String description) {
		super(name, description);
	}
}
