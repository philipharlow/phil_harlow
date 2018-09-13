package model;

public class ICritterSlug extends LandICritter{

	public ICritterSlug(String theName, Owner theOwner) {
		super(theName, theOwner);
	}
	
	@Override
	public void interact(ICritter other){
		try {
			this.interestCorrelation(other);
			
		}
		catch (NoInterestsException e) {
			this.addFriend(other);
		}
		//LandICritter doesn't add any other types of ICritters as friends, nothing else to do here 
	}

}
