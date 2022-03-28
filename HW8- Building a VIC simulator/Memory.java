
/*
Assignment number : 8

File name : Computer.java

Name (First Last) : Chen Asraf

Student ID : 204693022

Email : chen.asraf@post.idc.ac.il

*/
import javax.imageio.spi.RegisterableService;

/**
 * Represents a memory unit, which is an indexed sequence of registers. Enables
 * reading from, or writing to, any individual register according to a given
 * index. The index is typically called "address". The addresses run from 0 to
 * the memory's size, minus 1.
 */
public class Memory {

	private Register[] m; // an array of Register objects

	/**
	 * Constructs a memory of size registers, and sets all the register values to 0.
	 */
	public Memory(int size) {
		this.m = new Register[size];
		for (int i = 0; i < size; i++) {
			this.m[i] = new Register();
		}
	}

	/** Sets the values of all the registers in this memory to 0. */
	public void reset() {
		for (int i = 0; i < m.length; i++) {
			this.m[i].setValue(0);
		}
	}

	/** Returns the value of the register whose address is the given address. */
	public int getValue(int address) {
		return this.m[address].getValue();
	}

	/** Sets the register in the given address to the given value. */
	public void setValue(int address, int value) {
		this.m[address].setValue(value);
	}

	/**
	 * Returns a subset of the memory's contents, as a formated string.
	 * Specifically: Returns the first 10 registers (where the top of the program
	 * normally resides) and the last 10 registers (where the variables normally
	 * reside). For each register, returns the register's address, and value.
	 */
	public String toString() {
		String m = "";
		if (this.m.length < 21) {
			for (int i = 0; i < this.m.length; i++) {
				m += i + " " + this.m[i].getValue() + "\n";
			}
		} else {
			for (int i = 0; i < 10; i++) {
				m += i + " " + this.m[i].getValue() + "\n";
			}
			m += "...\n";
			for (int i = this.m.length - 11; i < this.m.length; i++) {
				m += i + " " + this.m[i].getValue() + "\n";
			}
		}
		return m;
	}
}
