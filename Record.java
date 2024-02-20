/*this is the record class. This class represents the records that will be stored in the hash table, implemented in the Dictionary.java class
 * AUTHOR : SARA MEHRAVAR
 * STUDENT ID : 221185394
 */ 
public class Record {
	// making some variables
	private String key;
	private int score;
	private int level;
	
	// the constructor variable
	public Record(String key, int score, int level){ 
		this.key = key;
		this.score = score;
		this.level = level;
	}
	// making a get key method
	//as its a get method, it has to return something which is key
	public String getKey(){
		return key;
	}
	//making a get score method
	//returns score
    public int getScore() {
    	return score;
    }
    //making a get level method
    //returns level
    public int getLevel() {
    	return level;
    }
}

