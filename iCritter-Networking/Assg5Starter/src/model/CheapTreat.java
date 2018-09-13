package model;

/**
 * Class describes a CheapTreat.
 * CheapTreat is an extension of the class Treat
 * {@link Treat}
 * @author John McGowan
 */
public class CheapTreat extends Treat{

	/**
	 * Constructs a new CheapTreat
	 * 
	 * @param desc	description of the treat
	 * 
	 * @author John McGowan
	 */
	public CheapTreat(String desc) {
		super(desc);
	}

	/**
	 * returns the cost of a CheapTreat (currently 1)
	 * @author John McGowan
	 */
	public Integer getCost() {
		return 1;
	}
	
	public String toString() {
		return "Cheap Treat";
	}

}
