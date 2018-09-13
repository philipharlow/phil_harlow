package model;

import java.io.Serializable;

/**
 * This class represents a Treat that an ICritter can eat
 *   other types of treats will extend this treat
 *   
 * @Author: John McGowan
 */
public abstract class Treat implements Serializable{
	
	private String description;//The description of the treat (initialized by the constructor)
	
	/**
	 * @param: desc	a String representation of the description of this treat
	 * @author John McGowan
	 */
	public Treat(String desc){
		description = desc;
	}
	
	/**
	 * Returns the cost of this treat
	 */
	public abstract Integer getCost();
	
	/**
	 * Returns the string representation of the treat's description
	 * @author John McGowan
	 */
	public String getDescription(){
		return description;
	}
}
