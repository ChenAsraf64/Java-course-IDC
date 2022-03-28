/*
Assignment number : 4

File name : BinOps.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/


/**
 * This class features various algebraic operations on binary values.0 Each
 * binary value is represented by an array of n integer values, each being 0 or
 * 1. We call such arrays "binary arrays". The power of index 0 is (n - 1). The
 * power of index (n - 1) is 0.
 */
public class BinOps {

	static final int n = 16;

	// Assumes that args[0] and args[2] are non-negative integers, and
	// that args[1] is either "add" or "mult".
	// Converts args[0] and args[1] to binary arrays, performs the add/mult
	// operation, and prints the decimal value of the resulting binary array.
	public static void main(String args[]) {
		tests();
	}

	// Function testers
	public static void tests() {
		int[] b1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1 }; // 13
		int[] b2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 }; // 6
		System.out.println(bin2String(b1)); // "0000000000001101"
		System.out.println(bin2String(b2)); // "0000000000000110"
		System.out.println(bin2String(add(b1, b2))); // "0000000000010011"
		System.out.println(bin2String(leftShift(b2))); // "0000000000001100"
		System.out.println(bin2String(mult(b1, b2))); // "0000000001001110"

		System.out.println("new case:");
		int[] a1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }; // 15
		int[] a2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }; // 5
		System.out.println(bin2String(a1)); // "0000000000001111"
		System.out.println(bin2String(a2)); // "0000000000000101"
		System.out.println(bin2String(add(a1, a2))); // "0000000000010100"
		System.out.println(bin2String(leftShift(a2))); // "0000000000001010"
		System.out.println(bin2String(mult(a1, a2))); // "0000000001001011"
	}

	/**
	 * Returns the sum of the two given binary arrays, as a binary array. Ignores
	 * the overflow bit, if there is one. Assumes array values are all 0 or 1.
	 * Example 1 (no overflow): if x = {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1} and y =
	 * {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0}, returns:
	 * {0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1}. Example 2 (overflow): if x =
	 * {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1} and y = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
	 * returns: {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0}.
	 */
	public static int[] add(int[] x, int[] y) {
		int[] temp = new int[16];
		for (int i = 0; i < temp.length; i++)
			temp[i] = x[i];

		int[] adding = new int[16];
		for (int i = 15; i >= 0; i--) {

			if (temp[i] == 1 && y[i] == 1) {
				if (i == 0) {
					adding[i] = 0;
				} else {
					adding[i] = 0;
					temp[i - 1] = temp[i - 1] + temp[i];
				}
			} else if (temp[i] == 2) {
				adding[i] = y[i];
				temp[i - 1] = temp[i - 1] + 1;
			} else {
				adding[i] = temp[i] + y[i];
			}

		}
		return adding;
	}

	/**
	 * Returns the product of the two given binary arrays, as a binary array.
	 * Ignores the overflow bit, if there is one. Assumes array values are all 0 or
	 * 1. Example: if x = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0} and y =
	 * {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1}, returns:
	 * {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0}.
	 */
	public static int[] mult(int[] x, int[] y) {
		int[] result = new int[16];
		int[] temp = new int[16];

		for (int i = 15; i >= 0; i--) {
			int count = 15;
			for (int j = 15; j >= 0; j--) {
				temp[count] = y[i] * x[j];
				count--;
			}

			for (int j = i; j < 15; j++) {
				temp = leftShift(temp);

			}

			result = add(temp, result);

		}

		return result;
	}

	/**
	 * Returns the binary array that represents the given int value. If the given
	 * int value exceeds the n-bit representation, ignores all the overflow bits.
	 * Assumes input value is a non-negative integer. Example: if x = 6, returns:
	 * {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0}.
	 */
	public static int[] int2bin(int x) {
		int[] bin = new int[16];
		for (int i = 15; i >= 0; i--) {
			if (x % 2 != 0) {
				bin[i] = 1;
			}
			x = x / 2;
		}

		return bin;
	}

	/**
	 * Returns the integer value of the given binary array. Assumes array values are
	 * all 0 or 1. For example: if x = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1}, returns:
	 * 9.
	 */
	public static int bin2int(int[] x) {
		int sum = 0;
		int power = 1;
		for (int i = x.length - 1; i >= 0; i--) {
			if (x[i] == 1) {
				sum += power;
			}
			power = power * 2;
		}
		return sum;
	}

	/**
	 * Returns a binary array which is the given binary array, shifted one position
	 * to the left. Ignores the overflow bit, if there is one. Assumes array values
	 * are all 0 or 1. Example 1 (no overflow): if x =
	 * {0,0,0,0,0,0,0,0,1,1,1,0,0,1,0,0}), returns:
	 * {0,0,0,0,0,0,0,1,1,1,0,0,1,0,0,0}). Example 2 (overflow): if x =
	 * {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1}), returns:
	 * {0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0}).
	 */
	public static int[] leftShift(int[] x) {
		int[] left = new int[x.length];
		for (int i = 0; i < x.length - 1; i++) {
			left[i] = x[i + 1];
		}
		return left;
	}

	/**
	 * Returns the given binary array, as a string. Useful for debugging purposes.
	 * Assumes array values are all 0 or 1. Example: if x =
	 * {0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,1}), returns: "0001100000000101".
	 */
	public static String bin2String(int[] x) {
		String binary = "";
		for (int i = 0; i < x.length; i++) {
			binary += x[i];
		}
		return binary;
	}
}
