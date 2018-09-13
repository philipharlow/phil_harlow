package model;

import java.io.Serializable;

/**
 * Describes a reaction an ICritter had.
 * 
 * @author John McGowan
 */
public class ICritterReaction implements Serializable{

	private Integer moodModifier;//how did this reaction affect it's mood?
	
	/**
	 * Creates a new ICritterReaction
	 * 
	 * @param moodMod	how was the mood modified? (positive = happier, negative = grumpier)
	 * 
	 * @author John McGowan
	 */
	public ICritterReaction(Integer moodMod){
		moodModifier = moodMod;
	}
	
	/**
	 * Returns the moodModifier to signify how this reaction affected the ICritter
	 * 
	 * @return Integer
	 * 
	 * @author John McGowan
	 */
	public Integer getMoodModifier(){
		return moodModifier;
	}
}
