/*this is node method which was not mandatory to make/use but i made it
 * STUDENT ID : 251185394
 * AUTHOR : SARA MEHRAVAR
 */
public class Node{
	
	private Record record; 
	private Node nextNode; // the next connected node
	//constructor method
	
	public Node(Record record){
		this.record = record;
		//setting next node to null
		this.nextNode = null;
	}
	//get node method returns next node
	public Node getNext() {
		return nextNode;
	}
	//set node method, sets next node to some data.
	public void setNext(Node next){
		this.nextNode = next;
	}
	//method get record returns record
	public Record getRecord() {
		return record;
	}
	//method set record, sets record to some data
	public void setRecord(Record newRecord) {
		this.record = newRecord;
	}
}
