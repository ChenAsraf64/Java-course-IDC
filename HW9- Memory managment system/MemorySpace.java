/*
Assignment number : 9

File name : MemoeySpace.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/
package mms;

import linkedlist.LinkedList;
import linkedlist.LinkedListIterator;

//import java.util.ListIterator;

/**
 * Represents a managed memory space. The memory space is handled by managing a list 
 * of allocated memory blocks, and a list free memory blocks. Blocks move from one
 * list to the other as side effects of executing "malloc" and "free" function calls.
 */
public class MemorySpace {

	// A list representing all the memory blocks that are presently allocated
	private LinkedList allocatedList;
			//<MemoryBlock> allocatedList;

	// A list representing all the memory blocks that are presently free
	private LinkedList freeList;

	/**
	 * Constructs a new managed memory space of a given maximal size.
	 * Specifically, constructs an empty list of allocated blocks,
	 * and a free list containing a single block which represents the entire memory
	 * space. The base address of this single block is zero, and its length is the
	 * given memory size.
	 *
	 * @param maxSize the size of the memory space to be managed
	 */
	public MemorySpace(int maxSize) {
		allocatedList = new LinkedList<MemoryBlock>();
		freeList = new LinkedList<MemoryBlock>();
		freeList.addFirst(new MemoryBlock(0, maxSize));
	}

	/**
	 * Allocates a memory block of a requested length (in words). Returns the
	 * base address of the allocated block, or -1 if unable to allocate.
	 * <p>
	 * This implementation scans the freeList, looking for the first free memory block
	 * whose length equals at least the given length. If such a block is found, the method
	 * performs the following operations:
	 * <p>
	 * (1) A new memory block is constructed. The base address of the new block is set to
	 * the base address of the found free block. The length of the new block is set to the value
	 * of the method's length parameter.
	 * <p>
	 * (2) The new memory block is appended to the end of the allocatedList.
	 * <p>
	 * (3) The base address and the length of the found free block are updated, to reflect the allocation.
	 * For example, suppose that the requested block length is 17, and suppose that the base
	 * address and length of the the found free block are 250 and 20, respectively.
	 * In such a case, the base address and length of of the allocated block
	 * are set to 250 and 17, respectively, and the base address and length
	 * of the found free block are set to 267 and 3, respectively.
	 * <p>
	 * (4) The new memory block is returned (to the caller, which is typically a constructor).
	 * <p>
	 * If we are lucky to find a block whose length is EXCATLY that of the requested length,
	 * then the found block is removed from the freeList and appended to the allocatedList.
	 *
	 * @param length the length (in words) of the memory block that has to be allocated
	 * @return the base address of the allocated block, or -1 if unable to allocate
	 */
	public int malloc(int length) {
		LinkedListIterator<MemoryBlock> iter = freeList.iterator();
		MemoryBlock c;
		while (iter.hasNext()) {
			c = iter.next();
			if (c.length == length) {
				allocatedList.addLast(c);
				freeList.remove(c);
				return c.baseAddress;
			}
			if (c.length > length) {
				MemoryBlock block = new MemoryBlock(c.baseAddress, length);
				allocatedList.addLast(block);
				c.baseAddress = c.baseAddress + length;
				c.length = c.length - length;
				return block.baseAddress;
			}
		}
		return -1;
	}

	/**
	 * Frees the memory block whose base address equals the given address: deletes the block whose base address
	 * equals the given address from the allocatedList, and adds it at the end of the free list.
	 *
	 * @param address the starting address of the block to freeList
	 */
	public void free(int address) {
		LinkedListIterator<MemoryBlock> iter = allocatedList.iterator();
		MemoryBlock current;
		while (iter.hasNext()) {
			current = iter.next();
			if (current.baseAddress == address) {
				allocatedList.remove(current);
				freeList.addLast(current);
				break;
			}
		}
	}

	/**
	 * A textual representation of the current state of the free list and the allocated list,
	 * using some sensible and easy to read format.
	 */
	public String toString() {
		String list = "freeList is: " + this.freeList.toString() + " \n " +
		" allocatedList is: " + this.allocatedList.toString() + " \n ";

		return list;
	}
}
	


