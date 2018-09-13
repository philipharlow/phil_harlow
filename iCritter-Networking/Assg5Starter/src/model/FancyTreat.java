package model;

/**
 * Class describes a FancyTreat.
 * FancyTreat extends Treat.
 * 
 * {@link Treat}
 * @author John McGowan
 */
public class FancyTreat extends Treat {

	/**
	 * Creates a new FancyTreat object.
	 * 
	 * @param desc	the description of the treat
	 * 
	 * @author John McGowan
	 */
	public FancyTreat(String desc) {
		super(desc);
	}

	/**
	 * Returns the cost of a FancyTreat (currently 5)
	 * 
	 * @author John McGowan
	 */
	public Integer getCost() {
		return 5;
	}
	
	@Override
	public String toString() {
		return "Fancy Treat";
	}
	

}
