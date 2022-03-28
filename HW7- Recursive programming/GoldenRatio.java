/*
Assignment number : 7

File name : GoldenRatio.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class GoldenRatio {
	static double goldenRec(int n) {
		if (n < 0) {
			return 0.0;
		} else if (n == 0) {
			return 1.0;
		} else {
			return 1 + 1 / goldenRec(n - 1);
		}
	}

	static double goldenIter(int n) {
		if (n < 0) {
			return 0.0;
		} else if (n == 0) {
			return 1.0;
		}
		double goldenRatio = 1.0;
		for (int i = 1; i <= n; i++) {
			goldenRatio = 1 + (1 / goldenRatio);
		}
		return goldenRatio;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(goldenRec(n));
		System.out.println(goldenIter(n));
	}

}