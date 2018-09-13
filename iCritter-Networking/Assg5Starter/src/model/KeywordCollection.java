package model;



import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A collection of keywords that can be correlated to other keywordcollections to
 * determine how close of a match they are.
 * 
 * @author John McGowan
 */
public class KeywordCollection implements Serializable {
	static transient Map<String, Integer> keywordTable = new HashMap<String,Integer>();
	private Set<String> returnValue;
	private long keywordBitmap;
	
	/**
	 * <b>KeywordCollection</b>
	 * <p>
	 * Creates a new keywordcollection class and initializes the set of keywords.
	 * 
	 * @author John McGowan
	 */
	public KeywordCollection(){
		keywordBitmap = 0;
	}
	
	/**
	 * <b>listKeywords</b>
	 * <p>
	 * Returns a set of the keywords
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	public Set<String> listKeywords(){
		Set<String> keywords = new HashSet<String>();
		returnValue = new HashSet<String>();
		
		keywords = keywordTable.keySet();
		Iterator<String> tempArray = keywords.iterator();
		while(tempArray.hasNext()) {
			String tempString = tempArray.next();
			int pos = keywordTable.get(tempString);
			if(getBitSet(pos)) {
				returnValue.add(tempString);
			}
		}
		return returnValue;
		
		
	}
	
	/**
	 * <b>addKeyword</b>
	 * <p>
	 * If the given keyword already exists, then return false, otherwise
	 * 		add the keyword to our set and return true.
	 * 
	 * @parm theWord	The word to add to our set.
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean addKeyword(String theWord){
		
		if(isKnownKeyword(theWord)) { //do we have this keyword already?
			Integer temp = keywordTable.get(theWord.toLowerCase());
			long tempBit = 1;
			tempBit = tempBit << temp;
			keywordBitmap = keywordBitmap | tempBit;
			return false;
		}
		else{
			Integer rand = 0 + (int)(Math.random() * ((63 - 0) + 1));
			while(keywordTable.containsValue(rand)) {
				rand = 0 + (int)(Math.random() * ((63 - 0) + 1));	//Generate a random number between 0-63 inclusive
			}
			keywordTable.put(theWord.toLowerCase(), rand);//add the word to our hashMap
			long tempBit2 = 1;
			tempBit2 = tempBit2 << rand;
			keywordBitmap = keywordBitmap | tempBit2;
			return true;
		}
	}
	
	/**
	 * <b>removeKeyword</b>
	 * <p>
	 * Removes the given keyword from the set.
	 * If the keyword exists, returns true; otherwise, false.
	 * 
	 * @parm theWord	The word to remove from the set
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean removeKeyword(String theWord){
		if(keywordTable.containsKey(theWord.toLowerCase())) {
			Integer temp = keywordTable.get(theWord.toLowerCase());
			keywordBitmap &= ~(1 << temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * <b>isKnownKeyword</b>
	 * <p>
	 * determine if a given keyword is known (e.g. determine if it is present in the keyword table)
	 * if the keyword exists, returns true; otherwise, false.
	 * 
	 * @parm theWord	The word to remove from the set
	 * 
	 * @return boolean
	 * 
	 * @author Philip Harlow
	 * 
	 */
	public static boolean isKnownKeyword(String theWord) {
		return keywordTable.containsKey(theWord.toLowerCase());
	}
	
	
	/**
	 * <b>getBitSet</b>
	 * <p>
	 * determine if the target bit is set or not. returns true if the bit is on, else it returns false.
	 * 
	 * @parm bitPos	The position in our keywordBitmap to check
	 * 
	 * @return boolean
	 * 
	 * @author Philip Harlow
	 * 
	 */
	private boolean getBitSet(Integer bitPos) {
		long temp = keywordBitmap;
		temp = (temp >>> (bitPos)) & 1;
		if(temp == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * <b>correlation</b>
	 * <p>
	 * Calculates the correlation between two sets of keys.
	 * Determines calculation by taking the cardinality of the intersetion of both sets and dividing it by
	 * the cardinality of the union of both sets.
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return double
	 * 
	 * @author John McGowan
	 */
	public double correlation(KeywordCollection oColl) throws NoInterestsException{
		long union = union(oColl);
		if(union == 0) {
			throw new NoInterestsException();
		}
		long intersect = intersection(oColl);
		int countUnion = 0;
		int countIntersect = 0;
		int counter = 0;
		while(counter < 64) {
			countUnion += union & 1;
			union >>= 1;
			counter++;
		}
		counter = 0;
		while(counter < 64) {
			countIntersect += intersect & 1;
			intersect >>= 1;
			counter++;
		}
		return countIntersect/countUnion;
	}
	
	
	
	/**
	 * <b>union</b>
	 * <p>
	 * Takes another KeywordCollection and returns a Set that is a union of the two.
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	private long union(KeywordCollection oColl){
		long temp = oColl.keywordBitmap | this.keywordBitmap;
		return temp;
	}
	
	/**
	 * <b>intersection</b>
	 * <p>
	 * Returns the intersection of this set of keywords and the provided KeywordCollection's set of keywords
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	private long intersection(KeywordCollection oColl){
		long temp = oColl.keywordBitmap & this.keywordBitmap;
		return temp;
		
	}
}
