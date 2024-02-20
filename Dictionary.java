/* this class is Dictionary. 
 * This class implements a dictionary using a hash table in which collisions are resolved using separate chaining
 * AUTHOR: SARA MEHRAVAR
 * STUDENT ID: 251185394
 */
public class Dictionary implements DictionaryADT {
	
	//making some instant variables
	private Node[] table;
	private int tableSize;
	private int elements;
	//constructor method
	public Dictionary(int size) {
		tableSize = size;
		table = new Node[size];
	}
	
	// HASHKEY ITERATOR
	private int hashFunction(String key) {
		int HKey = 0;
		for (int i = 0; i < key.length(); i++) {
			HKey = (HKey * 43 ) % tableSize;
			// x=43
			HKey += (int) key.charAt(i);
		}
		return HKey % tableSize;
	}
	//Inserts the given Record object referenced by rec in the dictionary
	public int put(Record record) throws DuplicatedKeyException {
		int hashKey = hashFunction(record.getKey());
		if(get(record.getKey()) != null) {
			throw new DuplicatedKeyException("already in the table");
		}
		// Key is empty, create a new node
		if (table[hashKey] == null){
			table[hashKey] = new Node(record);
			return 0;
		}
		else{
		// Get the Front of the Linked List
			Node currentRecord = table[hashKey];
			while (currentRecord.getNext() != null) {
		// Iterate until end
				currentRecord = currentRecord.getNext();
			}
			currentRecord.setNext(new Node(record));
			this.elements = numRecords() + 1;
		}
		return 1;
	}
	 //Removes the Record object containing string key from the dictionary
	public void remove(String key) throws InexistentKeyException {
		int hashKey = hashFunction(key);
		Node currentNode = table[hashKey];
		if (currentNode == null) {
			throw new InexistentKeyException("not found in the table");
		}
		// ITERATES UNTILL CURRENT NODE IS NOT NULL
		while (currentNode != null) {
			//IF IT FINDS THE CURRENT NODE THA EQUALS KEY:
			if (currentNode.getRecord().getKey().equals(key)) {
				table[hashKey] = currentNode.getNext();
				break;
			} else {
				//ELSE: CURRENT NODE IS GONNA BE CONVERTED TO THE NEXT NODE 
				currentNode = currentNode.getNext();
			}
		}
	}
	/*A method which returns the Record object stored in the
	*hash table containing the given key value, or null if no Record object stored in the hash table
	*contains the given key value.
	*/
	public Record get(String key) {
		int hashKey = hashFunction(key);
		Node currentNode = table[hashKey];
		while (currentNode != null) {
			//IF IT FINDS THE CURRENT NODE THA EQUALS KEY:
			if (currentNode.getRecord().getKey().equals(key)) {
				return currentNode.getRecord();
			} else {
				//ELSE: CURRENT NODE IS GONNA BE CONVERTED TO THE NEXT NODE 
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}
	//Returns the number of Record objects stored in the hash table
	public int numRecords() {
		return elements;
	}
}
