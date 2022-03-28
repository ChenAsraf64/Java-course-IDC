/*
Assignment number : 8

File name : Computer.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/
public class VicSimulator {
	public static void main(String[] args) {
		try {
			String programFileName = (args[0]);
			String inputFileName = (args[1]);
			Computer vic = new Computer();
			vic.loadProgram(programFileName);
			vic.setInput(inputFileName);
			vic.run();
			System.out.print(vic);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			String programFileName = (args[0]);
			Computer vic = new Computer();
			vic.loadProgram(programFileName);
			vic.run();
			System.out.print(vic);
		}
	}
}