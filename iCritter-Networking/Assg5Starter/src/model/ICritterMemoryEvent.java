package model;

import java.io.Serializable;

/**
 * Class describes an ICritterMemoryEvent.
 * A memory event is used to store how an ICritter reacted to something.
 * ICritterMemoryEvents are stored in an ICritter.
 * 
 * @author John McGowan
 */
public class ICritterMemoryEvent implements Serializable {
	private Treat rememberedTreat;//the treat to remember for this event
	private ICritterReaction rememberedReaction;//the reaction to remember for this event
	
	/**
	 * Will create a new ICritterMemoryEvent.
	 * 
	 * @param theTreat		The treat to remember for this reaction
	 * 
	 * @param theReaction	How the critter reacted to theTreat.
	 * 
	 * @author John McGowan
	 */
	public ICritterMemoryEvent(Treat theTreat, ICritterReaction theReaction){
		rememberedTreat = theTreat;
		rememberedReaction = theReaction;
	}
	
	/**
	 * Returns the rememberedTreat
	 * 
	 * @return Treat
	 * {@link Treat}
	 * @author John McGowan
	 */
	public Treat getRememberedTreat(){
		return rememberedTreat;
	}
	
	/**
	 * Returns the rememberedReaction
	 * 
	 * @return ICritterReaction
	 * {@link ICritterReaction}
	 */
	public ICritterReaction getRememberedReaction(){
		return rememberedReaction;
	}
	
	public String toString() {
		return rememberedTreat.toString() + "(Reaction was " + rememberedReaction.getMoodModifier() + ")"; 
	}
	
}
