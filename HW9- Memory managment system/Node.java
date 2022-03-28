/*
Assignment number : 9

File name : Node.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/
package linkedlist;

/**
 * Represents a node in a doubly linked list. Each node points to a MemoryBlock object. 
 */
public class Node<T> {

	// The memory block that this node points at
	T data;
	// The next node in the list
	Node<T> next = null;

	/**
	 * Constructs a new node, pointing to the given memory block.
	 * 
	 * //@param block
	 *        the given memory block
	 */
	public Node(T t) {
		this.data = t;
		this.next=null;
	}
	
	/**
	 * A textual representation of this node, useful for debugging.
	 * The node's contents, which is a memory block, should appear within
	 * curly brackets. For example: {(208,10)}. 
	 */
	public String toString() {
		return "{" + this.data + "}";
	}
}