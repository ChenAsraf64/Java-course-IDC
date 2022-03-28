/*
Assignment number : 9

File name : LinkedList.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/
package linkedlist;

/**
 * Represents a list of Nodes. 
 */
public class LinkedList<T> {
	
	private Node<T> first = null; // pointer to the first (dummy) node of this list
	private Node<T> last = null;  //  pointer to the last node of this list
	private int size = 0;      // number of elements in this list */
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = new Node<T>(null);
		last = first;
	}
	
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last
	 * node in this list.
	 *
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param insert
	 *        the object to be inserted into the list
	 * @param index
	 *        the index before which the object should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, T insert) {
		Node<T> nodeToInsert = new Node<T>(insert);
		Node<T> first1 = this.first;
		if (index > this.size || index < 0) {
			throw new IllegalArgumentException("index is negative or greater than the list's size");
		}
		if (index == 0) {
			nodeToInsert.next = first1.next;
			first1.next = nodeToInsert;
		}
		for (int i = 1; i <= size; i++) {
			if (index == size) {
				this.last.next = nodeToInsert;
				this.last = nodeToInsert;
			}
			if (index == i) {
				nodeToInsert.next = first1.next;
				first1.next = nodeToInsert;
			} else {
				first1 = first1.next;
			}
		}
		this.size++;
	}


	
	/**
	 * Creates a new node with a reference to the given object, and appends it to the end of this list
	 * (the node will become the list's last node).
	 * 
	 * @param insert
	 *        the given object
	 */
	public void addLast(T insert) {
		Node<T> newNode = new Node<T>(insert);
		this.last.next = newNode;
		this.last = newNode;
		this.size++;
	}

	/**
	 * Creates a new node with a reference to the given object, and inserts it at the beginning of this list
	 * (the node will become the list's first node).
	 * 
	 * @param insert
	 *        the given object
	 */
	public void addFirst(T insert) {
		Node<T> newNode = new Node<T>(insert);
		newNode.next = first.next;
		first.next = newNode;
		this.size++;
		if (this.first.next.next == null){
			last = first.next;
		}
	}

	/**
	 * Gets the object located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved object
	 * @return the object at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public T get(int index) {
		Node<T> node = this.first.next;
		if (index > this.size || index < 0){
			throw new IllegalArgumentException("index is negative or greater than or equal to size");
		}
		for(int i = 0; i <= index ; i++) {
			node = node.next;
		}
		return node.data;
	}	

	/**
	 * Gets the index of the node pointing to the given object.
	 * 
	 * @param t
	 *        the given object
	 * @return the index of the first occurrence of the object, or -1 if the object is not in this list
	 */
	public int indexOf(T t) {
		Node<T> node = this.first.next;
		for (int i = 0; i < this.size; i++){
			if (node.data == t){
				return i;
			} else {
				node = node.next;
			}
		}
		return -1;
	}



	/**
	 * Removes from this list the first occurrence of a node pointing to the given object.
	 * 
	 * @param remove the object that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(T remove) {
		int x = indexOf(remove);
		Node<T> node = this.first;
		for (int i = 0; i <= x ; i++){
			if (node.next.data == remove){
				node.next = node.next.next;
				this.size--;
				return;
			} else {
				node = node.next;
			}
		}
		if (node == null){
			throw new IllegalArgumentException("the given memory block is not in this list");
		}
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public LinkedListIterator<T> iterator(){
		LinkedListIterator <T> iteratorNew = new LinkedListIterator(this.first.next);
		return iteratorNew;
	}
	
	/**
	 * A textual representation of this list, useful for debugging.
	 */
	public String toString() {
		String ans = "";
		Node<T> node = this.first;
		LinkedListIterator <T> iter = this.iterator();
		while (iter.hasNext()){
			ans += iter.next().toString() + " ";
		}
		return ans;
	}
}