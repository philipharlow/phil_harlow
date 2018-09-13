package model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;



/**
 * Describes an Owner.
 * An owner owns an ICritter and has money to buy Treats for his/her/it's ICritter
 * 
 * @author John McGowan
 */
public class Owner extends Observable implements Serializable{
	private ICritter iCritter;//the critter
	private List<Treat> treats;//a list of all the treats owned by this owner
	private Integer credits;//the number of credits this owner has
	private String name;//the name of the Owner
	
	/**
	 * <b>Owner</b>
	 * <p>
	 * Constructs a new Owner.  During construction, the owner will create an ICritter to own as well.
	 * 
	 * @param oName	The name of the Owner
	 * @param cName	The name of the Owner's ICritter
	 *  @author John McGowan
	 */
	public Owner(String oName, String cName){
		name = oName;
		//create our new ICritter with it's name and this Owner as the Owner.
		iCritter = new ICritterPenguin(cName, this);
		credits = 30;//owner starts with 10 credits
		//initialize treats to an empty linkedList.
		treats = new LinkedList<Treat>();
	}
	
	/**
	 * <b>giveTreat</b>
	 * <p>
	 * Gives a treat to this owner's ICritter and returns the ICritter's reaction.
	 * If the owner does not own the treat, this method will return null.
	 * Once finished, this owner will no longer own the treat passed in.
	 * 
	 * @param theTreat	The treat to give to the ICritter.
	 * {@link Treat}
	 * 
	 * @return ICritterReaction
	 * {@link ICritterReaction}
	 * 
	 * @author John McGowan
	 */
	public ICritterReaction giveTreat(Treat theTreat){
		if(treats.contains(theTreat)){//does the owner own the treat?
			treats.remove(theTreat);//remove the treat from our treats list
			System.out.println("Current Treats:" + treats);
			ICritterReaction react = iCritter.receiveTreat(theTreat);
			setChanged();//let our observers know we have changed
			notifyObservers();//return the ICritter's reaction
			return react;
		}
		else
			return null;//owner does not own the treat
	}
	
	/**
	 * <b>getName</b>
	 * <p>
	 * Returns a string representation of this Owner's name.
	 * 
	 * @return String	the Owner's name
	 * 
	 * @author John McGowan
	 */
	public String getName(){return name;}

	/**
	 * <b>buyCheapTreat</b>
	 * <p>
	 * Buys a cheap treat with the given description and adds it to the owner's treats list.
	 * If the Owner does not have enough credits, the treat will not be bought and the treat will not be added, this method will also return null.
	 * If the Owner does have enough credits, the treat will be bought and the treat returned.
	 * 
	 * @param desc	The description of the treat requested to be bought.
	 * 
	 * @return {@link CheapTreat}
	 * 
	 * @author John McGowan
	 */
	public CheapTreat buyCheapTreat(String desc){
		//create the cheapTreat
		CheapTreat theTreat = new CheapTreat(desc);
		
		try {
			adjustCredits(-theTreat.getCost());//subtract the credits
			addTreat(theTreat);//add our treat to our treat stockpile
			return theTreat;//return the treat
		}
		catch (NotEnoughCreditsException e){
			return null;
		}
	}
	
	/**
	 * <b>buyFancyTreat</b>
	 * <p>
	 * Buys a fancy treat with the given description and adds it to the owner's treats list.
	 * If the Owner does not have enough credits, the treat will not be bought and the treat will not be added, this method will also return null.
	 * If the Owner does have enough credits, the treat will be bought and the treat returned.
	 * 
	 * @param desc	The description of the treat requested to be bought.
	 * 
	 * @return {@link FancyTreat}
	 * 
	 * @author John McGowan
	 */
	public FancyTreat buyFancyTreat(String desc){
		//create the cheapTreat
		FancyTreat theTreat = new FancyTreat(desc);
		
		try {
			adjustCredits(-theTreat.getCost());
			addTreat(theTreat);//add our treat to our treat stockpile
			return theTreat;//return the treat
		}
		catch (NotEnoughCreditsException e)	{
			return null;
		}
	}
	
	/**
	 * <b>listTreats</b>
	 * <p>
	 * This will return a list of treats owned by the owner.
	 * This list may be empty.
	 * 
	 * @return List<Treat>
	 * {@link Treat}
	 * 
	 * @author John McGowan
	 */
	public List<Treat> listTreats(){
		return treats;//returns our list of treats
	}
	
	/**
	 * <b>getCritter</b>
	 * <p>
	 * Returns a reference to the critter owned by this Owner.
	 * 
	 * @return {@link ICritter}
	 * 
	 * @author John McGowan
	 */
	public ICritter getCritter(){
		return iCritter;
	}
	
	/**
	 * <b>getCredits</b>
	 * <p>
	 * Returns the number of credits this owner has.
	 * 
	 * @return Integer
	 * 
	 * @author John McGowan
	 */
	public Integer getCredits(){return credits;}
	
	/**
	 * <b>adjustCredits</b>
	 * <p>
	 * Adds the given amount to the owners number of credits.
	 * 
	 * @param amount	Amount to adjust the credits by
	 * 
	 * @author John McGowan
	 */
	private void adjustCredits(Integer amount) throws NotEnoughCreditsException{
		if( (credits+amount) < 0 ) {
			throw new NotEnoughCreditsException(amount, this.credits);
		}
		else {
			credits += amount;
			setChanged();//let our observers know we have changed
			notifyObservers(); 
		}
	}
	
	/**
	 * <b>addTreat</b>
	 * <p>
	 * adds a treat to the owner's stockpile of treats.
	 * 
	 * @param theTreat	The treat to be added
	 * 
	 * @author John McGowan
	 */
	public void addTreat(Treat theTreat){
		treats.add(theTreat);
		setChanged();//let our observers know we have changed
		notifyObservers();
	}
}
