package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent the world the ICritters live in.
 * 
 * @author John McGowan
 */
public class ICritterWorld {
	
	private List<ICritter> iCritters;//the list of ICritters
	
	/**
	 * <b>ICritterWord</b>
	 * <p>
	 * Creates a new ICritterWord and gets it ready to receive new ICritters
	 */
	public ICritterWorld(){
		iCritters = new LinkedList<ICritter>();//initialize our list
	}
	
	/**
	 * <b>listICritters</b>
	 * <p>
	 * Returns a list of the ICritters currently in the world.
	 * 
	 * @return List<{@link ICritter}>	The list of ICritters
	 * 
	 * @author John McGowan
	 */
	public List<ICritter> listICritters(){
		return iCritters;
	}
	
	/**
	 * <b>addICritter</b>
	 * <p>
	 * Adds an ICritter to the world.
	 * If the ICritter already exists this method returns false and makes no changes; otherwise, it adds the ICritter to the world and returns true.
	 * 
	 * @parm toAdd	The ICritter to add to the world
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean addICritter(ICritter toAdd){
		if(iCritters.contains(toAdd))
			return false;
		else{
			iCritters.add(toAdd);
			return true;
		}
	}
	
	/**
	 * <b>removeICritter</b>
	 * <p>
	 * Removes the ICritter from the world.
	 * If the ICritter doesn't exist, this method returns false; otherwise, true and removes the ICritter.
	 * 
	 * @parm toRemove	The ICritter to remove from the world
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean removeICritter(ICritter toRemove){
		return iCritters.remove(toRemove);//the remove method follows the conventions required by this method
	}
	
	/**
	 * <b>runJamboree</b>
	 * <p>
	 * Iterates over all the ICritters in a speudo-random order and has them call <i>interact</i> on all the other ICritters (not including itself)
	 * 
	 * @author John McGowan
	 */
	public void runJamboree(){
		Collections.shuffle(iCritters);//shuffle the list of ICritters
		for(ICritter current : iCritters){//iterate over all the ICritters
			for(ICritter interactOn : iCritters){//iterate over all of them again and interact with each one (except for itself)
				if(!current.equals(interactOn))//if it's not the same one
					current.interact(interactOn);//interact with them
			}
		}
	}
}
