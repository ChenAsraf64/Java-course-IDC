/*
Assignment number : 8

File name : Computer.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/
/**
 * Represents a Vic computer. It is assumed that users of this class are
 * familiar with the Vic computer, described in www.idc.ac.il/vic. <br/>
 * The Computer's hardware consists of the following components:
 * <UL>
 * <LI>Data register: a register.
 * <LI>Program counter: a register.
 * <LI>Memory: a sequence of registers.
 * <LI>Input unit: a stream of numbers. In this implementation, the input unit
 * is simulated by a text file. When the computer is instructed to execute a
 * READ command, it reads the next number from this file and puts it in the data
 * register.
 * <LI>Output unit: a stream of numbers. In this implementation, the output unit
 * is simulated by standard output (by default, the console). When the computer
 * is instructed to execute a WRITE command, it writes the current value of the
 * data register to the standard output.
 * <LI>Processor: In this implementation, the processor is emulated by the run
 * method of this class.
 * </UL>
 * The Computer's software is a program written in the numeric Vic machine
 * language. Such a program normally resides in a text file which is loaded into
 * the computer's memory. This is done by the loadProgram method of this class.
 */

public class Computer {

	/**
	 * This constant represents the size of the memory unit of this Computer (number
	 * of registers). The default value is 100.
	 */
	public final static int MEM_SIZE = 100;

	/**
	 * This constant represents the memory address at which the constant 0 is
	 * stored. The default value is MEM_SIZE - 2.
	 */
	public final static int LOCATION_OF_ZERO = MEM_SIZE - 2;

	/**
	 * This constant represents the memory address at which the number 1 is stored.
	 * The default value is MEM_SIZE - 1.
	 */
	public final static int LOCATION_OF_ONE = MEM_SIZE - 1;

	// The commands of the Vic machine langauge.

	// Put the rest of the op-code declarations here.
	private final static int STOP = 0;
	private final static int ADD = 1;
	private final static int SUB = 2;
	private final static int LOAD = 3;
	private final static int STORE = 4;
	private final static int GOTO = 5;
	private final static int GOTOZ = 6;
	private final static int GOTOP = 7;
	private final static int READ = 8;
	private final static int WRITE = 9;

	// Put the declarations of the member variables (fields) here.
	private Register data;
	private Register pc;
	private Memory memory;
	private String input;

	/**
	 * Constructs a Vic computer. Specifically: constructs a memory that has
	 * MEM_SIZE registers, a data register, and a program counter. Next, resets the
	 * computer (see the reset method API). Note: the loading of a program into
	 * memory, and the initialization of the input file, are not done by the
	 * constructor. This is done by the public methods setInput and loadProgram,
	 * respectively.
	 */
	public Computer() {
		this.memory = new Memory(MEM_SIZE);
		this.data = new Register();
		this.pc = new Register();
		this.reset();
	}

	/**
	 * Resets the computer. Specifically: Resets the memory, sets the memory
	 * registers at addresses LOCATION_OF_ZERO and LOCATION_OF_ONE to 0 and to 1,
	 * respectively, sets the data register and the program counter to 0.
	 */
	public void reset() {
		this.memory.reset();
		this.memory.setValue(LOCATION_OF_ZERO, 0);
		this.memory.setValue(LOCATION_OF_ONE, 1);
		data.setValue(0);
		pc.setValue(0);
	}

	/**
	 * Executes the program currently stored in memory. This is done by affecting
	 * the following fetch-execute cycle: Fetches from memory the current word
	 * (3-digit number), i.e. the contents of the memory register whose address is
	 * the current value of the program counter. Extracts from this word the op-code
	 * (left-most digit) and the address (next 2 digits). Next, executes the command
	 * mandated by the op-code, using the address if necessary. As a side-effect of
	 * executing this command, modifies the program counter. Next, loops to fetch
	 * the next word, and so on.
	 */
	public void run() {
		int addr;
		int number;
		int op = 1;
		while (!(op == 0)) {
			number = this.memory.getValue(pc.getValue());
			op = number / 100;
			addr = number % 100;

			if (op == ADD) {
				execADD(addr);
			} else if (op == SUB) {
				execSUB(addr);
			} else if (op == LOAD) {
				execLOAD(addr);
			} else if (op == STORE) {
				execSTORE(addr);
			} else if (op == GOTO) {
				execGOTO(addr);
			} else if (op == GOTOZ) {
				execGOTOZ(addr);
			} else if (op == GOTOP) {
				execGOTOP(addr);
			} else if (op == READ) {
				execREAD();
			} else if (op == WRITE) {
				execWRITE();
			}
		}
		System.out.println("Program terminated normally");
	}

	// Private execution routines, one for each Vic command

	private void execADD(int addr) {
		this.data.setValue(memory.getValue(addr) + this.data.getValue());
		pc.addOne();
	}

	private void execSUB(int addr) {
		this.data.setValue(this.data.getValue() - memory.getValue(addr));
		pc.addOne();
	}

	private void execLOAD(int addr) {
		data.setValue(memory.getValue(addr));
		pc.addOne();
	}

	private void execSTORE(int addr) {
		this.memory.setValue(addr, this.data.getValue());
		pc.addOne();
	}

	private void execGOTO(int addr) {
		pc.setValue(addr);
	}

	private void execGOTOZ(int addr) {
		if (data.getValue() == 0) {
			pc.setValue(addr);
		} else {
			pc.addOne();
		}
	}

	private void execGOTOP(int addr) {
		if (data.getValue() > 0) {
			pc.setValue(addr);
		} else {
			pc.addOne();
		}
	}

	private void execREAD() {
		if (StdIn.isEmpty()) {
			setInput(input);
		}
		this.data.setValue(StdIn.readInt());
		pc.addOne();
	}

	private void execWRITE() {
		System.out.println(this.data.getValue());
		pc.addOne();
	}

	// Put the other private methods here.

	/**
	 * Loads a program into memory, starting at address 0. The program is stored in
	 * the given text file.
	 * 
	 * @param fileName The name of the file from which the program is read. It is
	 *                 assumed that the file contains a stream of valid commands
	 *                 written in the numeric Vic machine language (the language
	 *                 specification is described in www.idc.ac.il/vic). The program
	 *                 is stored in the memory, one command per memory register,
	 *                 starting at address 0.
	 */
	public void loadProgram(String fileName) {
		StdIn.setInput(fileName);
		int[] o = StdIn.readAllInts();
		for (int i = 0; i < o.length; i++) {
			this.memory.setValue(i, o[i]);
		}
	}

	/**
	 * Initializes the input unit from the given text file. It is assumed that the
	 * file contains a stream of valid data values, each being an integer in the
	 * range -999 to 999. Each time the computer is instructed to execute a READ
	 * command, the next line from this file is read and placed in the data register
	 * (this READ logic is part of the run method implementation). Thus, the role of
	 * this method is to initialize the file in order to enable the execution of
	 * subsequent READ commands.
	 */
	public void setInput(String fileName) {
		input = fileName;
		StdIn.setInput(fileName);
	}

	/**
	 * This method is used for debugging purposes. It displays the value of the data
	 * register, the value of the program counter, and the values of the first and
	 * last 10 memory registers.
	 */
	public String toString() {
		String answer = "D = " + this.data + "\n" + "PC = " + this.pc + "\n" + "Memory state: " + "\n" + this.memory;
		return answer;
	}
}
