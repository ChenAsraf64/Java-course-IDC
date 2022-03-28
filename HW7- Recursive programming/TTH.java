/*
Assignment number : 7

File name : TTH.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class TTH {

	private static double calcP(int n) {
		if (n <= 2) {
			return 0.0;
		} else {
			return 0.5 * calcP(n - 1) + 0.25 * calcP(n - 2) + Math.pow(0.5, n);
		}
	}

	private static double calcPmem(int n, double[] memory) {
		if (n <= 2)
			return 0.0;

		if (memory[n - 2] == 0)
			memory[n - 2] = calcPmem(n - 2, memory);
		if (memory[n - 1] == 0)
			memory[n - 1] = calcPmem(n - 1, memory);

		return 0.5 * memory[n - 1] + 0.25 * memory[n - 2] + Math.pow(0.5, n);
	}

	// this main function is provided
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double duration = 0;
		double durationMem = 0;
		int reps = 1000;
		double startTime, endTime;

		for (int i = 0; i < reps; i++) {
			startTime = System.nanoTime();
			calcP(n);
			endTime = System.nanoTime();
			duration += (endTime - startTime) / reps;

			double[] memory = new double[n + 1];
			startTime = System.nanoTime();
			calcPmem(n, memory);
			endTime = System.nanoTime();
			durationMem += (endTime - startTime) / reps;
		}
		StdOut.println("P(" + n + ") = " + calcP(n));
		StdOut.println("no memoization: " + duration / 100);
		StdOut.println("with memoization: " + durationMem / 100);
	}
}
