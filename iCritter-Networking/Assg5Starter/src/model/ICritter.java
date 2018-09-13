package model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;




/**
 * Class describes an ICritter.
 * An ICritter has memories and will have friends and can interact with other ICritters.
 * 
 * @author John McGowan
 */
public abstract class ICritter extends Observable implements Serializable{
	
	private String name;//the name of the ICritter
	private List<ICritterMemoryEvent> memories;//a list of memories the ICritter has
	private Owner owner;//the ICritter's owner
	private KeywordCollection interests;//the ICritter's interests
	private List<ICritter> friends;//the ICritter's friends
	
	/**
	 * Creates a new ICritter
	 * 
	 * @param theName	Defines the name of the ICritter
	 * 
	 * @param theOwner	A reference to the owner of this ICritter
	 * 
	 * @author John McGowan
	 */
	public ICritter(String theName, Owner theOwner){
		name = theName;
		owner = theOwner;
		memories = new LinkedList<ICritterMemoryEvent>();//initialize our memory list as a linked list
		interests = new KeywordCollection();
		friends = new LinkedList<ICritter>();
	}
	
	/**
	 * ICritter will receive the treat and consume it.
	 * Once the treat is consumed, the ICritter will create a new reaction to the treat,
	 * add that reaction to it's memory, then return that reaction to the caller.
	 * 
	 * @param theTreat	The treat that the ICritter is to consume
	 * 
	 * @return ICritterReaction
	 * 
	 * {@link ICritterReaction}
	 * 
	 * @author John McGowan
	 */
	public ICritterReaction receiveTreat(Treat theTreat){
		//first the ICritter eats the treat (yum, yum), this will produce a reaction to the treat
		ICritterReaction react = consumeTreat(theTreat);
		
		//next we add this reaction to our memory
		addMemoryEvent(theTreat,react);
		//now return our reaction
		setChanged();
		notifyObservers();
		return react;
	}
	
	/**
	 * <b>getMemories</b>
	 * <p>
	 * Returns a list of the memories this ICritter has.
	 * 
	 * @return List<ICritterMemoryEvent>
	 * {@link ICritterMemoryEvent}
	 * 
	 * @author John McGowan
	 */
	public List<ICritterMemoryEvent> getMemories(){
		return memories;
	}
	
	/**
	 * <b>getOwner</b>
	 * <p>
	 * Returns the Owner of this ICritter.
	 * The owner was given to the ICritter upon creation.
	 * 
	 * @return Owner
	 * {@link Owner}
	 * 
	 * @author John McGowan
	 */
	public Owner getOwner(){return owner;}
	
	/**
	 * <b>getName</>
	 * <p>
	 * Returns the name of this ICritter.
	 * The ICritter's name was given upon creation.
	 * 
	 * @return String
	 * 
	 * @author John McGowan
	 */
	public String getName(){return name;}
	
	/**
	 * <b>addMemoryEvent</b>
	 * <p>
	 * Given a Treat and a Reaction, this method creates a memory event and adds it to the ICritter's memory.
	 * 
	 * @param theTreat	The treat that is associated with this memory
	 * 
	 * @param theReact	The reaction that is associated with this memory
	 * 
	 * @author John McGowan
	 */
	public void addMemoryEvent(Treat theTreat, ICritterReaction theReact){
		//create the memory event
		ICritterMemoryEvent theEvent = new ICritterMemoryEvent(theTreat,theReact);
		
		memories.add(theEvent);//add it to our memories
		
		setChanged();//let our observers know we changed
		notifyObservers();
	}
	
	/**
	 * <b>consumeTreat</b>
	 * <p>
	 * Will determine a reaction to the treat and return this reaction to the caller.
	 * 
	 * @param theTreat	The treat to calculate a reaction to.
	 * 
	 * @return ICritterReaction
	 * {@link ICritterReaction}
	 * 
	 * @author John McGowan
	 */
	public ICritterReaction consumeTreat(Treat theTreat){
		//TODO: change reaction based on the type of treat
		ICritterReaction react = new ICritterReaction(1);//make it always positive for now
		setChanged();
		notifyObservers();
		return react;
	}
	
	/**
	 * <b>getFriends</b>
	 * <p>
	 * This method returns a list of all the ICritters that this ICritter may be friends with.
	 * Note: The critters in this list, may not consider themselves friends of this ICritter.
	 * 
	 * @return List<{@link ICritter}>
	 * 
	 * @author John McGowan
	 */
	public List<ICritter> getFriends(){
		return friends;
	}
	
	/**
	 * <b>addFriend</b>
	 * <p>
	 * Adds a friend to this ICritter's friends list.
	 * Returns true if <b>toAdd</b> is not already a friend, false if they are.
	 * This method does not allow duplicate friends
	 * 
	 * @param toAdd	The ICritter to add as a friend
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean addFriend(ICritter toAdd){
		if(friends.contains(toAdd))
			return false;//they are already friends
		else{
			friends.add(toAdd);
			setChanged();
			notifyObservers();
			return true;//successfully added
		}
			
	}
	
	/**
	 * <b>removeFriend</b>
	 * <p>
	 * Will remove a friend from the friends list.
	 * If toRemove was not already a friend, then this method will return false; otherwise, the friend will be removed and true will be returned.
	 * 
	 * @param toRemove	The ICritter to be removed from the friends list
	 * 
	 * @return boolean	true if successful, false if not
	 * 
	 * @author John McGowan
	 */
	public boolean removeFriend(ICritter toRemove){
		boolean removed = friends.remove(toRemove);//the remove method of a list matches the requirements of this method
		if(removed){//so we only update our observers if something really did change
			setChanged();
			notifyObservers();
		}
		return removed;
	}
	
	/**
	 * <b>listInterests</b>
	 * <p>
	 * Will return a set of this ICritter's interests.
	 * 
	 * @return Set<String>	Represents all the interests of this ICritter
	 * 
	 * @author John McGowan
	 */
	public Set<String> listInterests(){return interests.listKeywords();}
	
	/**
	 * <b><i>interact</b></i>
	 * <p>
	 * This method interacts with another ICritter, it does not modify the other ICritter; however, it will add a new friend if the ICritter decides to do so.
	 * 
	 * @param theCrit	The other ICritter
	 * 
	 * @author John McGowan
	 */
	public abstract void interact(ICritter theCrit);
	
	/**
	 * <b>getHappiness</b>
	 * <p>
	 * Will return the happiness of the ICritter as a double.
	 * For this part of the assignment, the happiness is determined by the function
	 * -4 + numFancyTreats.  numFancyTreats is the number of Fancy Treats this ICritter has
	 * had counting up to the past 8 Treats the Critter has had.
	 * 
	 * @return double	representing the happiness of the ICritter
	 * 
	 * @author John McGowan
	 */
	public double getHappiness(){
		double curHappy = -4;//his current calculated happiness
		for(int i=0; i < getMemories().size()&&i <8; i++){
			//we pull memories from the end of the list since we add memories to the end of the list
			//we then check the remembered treat and see if it was a FancyTreat
			if(getMemories().get(getMemories().size()-i-1).getRememberedTreat() instanceof FancyTreat)
				curHappy++;
		}
		return curHappy;
	}
	
	/**
	 * <b>addInterest</b>
	 * <p>
	 * Adds the given interest to this ICritter's interests.
	 * 
	 * @parm keyword	The interest to add
	 * 
	 * @return boolean	true if the keyword didn't already exist, false if it did
	 * 
	 * @author John McGowan
	 */
	public boolean addInterest(String keyword){
		boolean added = interests.addKeyword(keyword);
		if(added){//only update observers if something changed
			setChanged();
			notifyObservers();
		}
		return added;
	}
	
	/**
	 * <b>containsInterest</b>
	 * <p>
	 * Returns true if the given keyword is in the ICritter's interests, false otherwise.
	 * 
	 * @parm keyword	The interest to look for
	 * 
	 * @return boolean	see description
	 * 
	 * @author John McGowan
	 */
	public boolean containsInterest(String keyword){
		return interests.listKeywords().contains(keyword);
	}
	
	/**
	 * <b>removeInterest</b>
	 * <p>
	 * Removes the given keyword from this ICritter's interests
	 * 
	 * @parm keyword	The interest to remove
	 * 
	 * @return boolean	true if the interest was successfully removed, false if it didn't exist
	 * 
	 * @author John McGowan
	 */
	public boolean removeInterest(String keyword){
		boolean removed = interests.removeKeyword(keyword);
		if(removed){//only update observers if something was actually changed
			setChanged();
			notifyObservers();
		}
		return removed;
	}
	
	/**
	 * <b>interestCorrelation</b>
	 * <p>
	 * Returns the correlation between this ICritter's interests and the provided ICritter's interests
	 * 
	 * @parm other	The other ICritter to test against
	 * 
	 * @return double	Representation of the correlation of the two ICritter's interests
	 * 
	 * @author John McGowan
	 */
	public double interestCorrelation(ICritter other){
		return interests.correlation(other.interests);
	}
}
