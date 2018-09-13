package model;

/**
 * Defines a LandICritter
 * 
 * Left abstract so we can not instantiate a regular LandICritter
 */
public abstract class LandICritter extends ICritter {

	public LandICritter(String theName, Owner theOwner) {
		super(theName, theOwner);
	}
	
	/**
	 * <b>interact</b>
	 * The LandICritter always adds other LandICritters, but never MarineICritters.
	 * 
	 * @param other	The other ICritter to interact with
	 * 
	 * @author John McGowan
	 */
	public void interact(ICritter other){
		try {
			this.interestCorrelation(other);
			if(other instanceof LandICritter){
				addFriend(other);//add them as a friend since they are a LandICritter
			}
		}
		catch (NoInterestsException e) {
			
		}
		//LandICritter doesn't add any other types of ICritters as friends, nothing else to do here 
	}
}
