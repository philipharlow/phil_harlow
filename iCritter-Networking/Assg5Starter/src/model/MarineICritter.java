package model;

/**
 * Defines a MarineICritter
 * Left abstract to prevent the ability to instantiate it
 */
public abstract class MarineICritter extends ICritter{

	public MarineICritter(String theName, Owner theOwner) {
		super(theName, theOwner);
	}
	
	/**
	 * <b>interact</b>
	 * <p>
	 * If this ICritter and the other ICritter have at least a 50% correlation in interests, this ICritter will befriend the other (or stalk it)
	 * 
	 * @parm other	The other ICritter to interact with
	 * 
	 * @author John McGowan
	 */
	public void interact(ICritter other){
		//doesn't add any other ICritters as friends
		try {
			double cor = this.interestCorrelation(other);
			if(cor >= .5) {
				addFriend(other);
			}
		}
		catch (NoInterestsException e) {
			
		}
	}
}
