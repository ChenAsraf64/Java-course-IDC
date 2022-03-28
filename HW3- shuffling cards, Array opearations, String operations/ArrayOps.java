/*
Assignment number : 3

File name : ArrayOps.java

Name (First Last) : Chen Asraf

Student ID : 204693022

Email : chen.asraf@post.idc.ac.il

*/

/**
 * A library of various array processing functions.
 */
public class ArrayOps {
	// Tests the functions of this class.
	public static void main(String[] args) {
		test_all();
	}

	private static void test_all() {
		boolean testResult = test_equals() && test_sum() && test_maxSub();
		System.out.println("success: " + testResult);
	}

	private static boolean test_equals() {
		int[] a = { 2, 1, 9 };
		int[] b = { 0, 8, 11 };
		int[] c = { 0, 8, 11 };
		boolean testResult = !(equals(a, b)) && equals(b, c);
		return testResult;
	}

	/**
	 * Returns true if the two arrays have the same elements, false otherwise.
	 */
	public static boolean equals(int[] a, int[] b) {

		for (int i = 0; i < a.length; i++) {

			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}

	private static boolean test_sum() {
		int[] a = { 2, 1, 9 };
		int[] b = { -1, 11, 2 };
		int[] c = { 2, 2, 3, 4, -1 };
		int sumArr_a = sum(a, 1, 3);
		int sumArr_b = sum(b, 0, 2);
		int sumArr_c = sum(c, 0, c.length);
		boolean testResult = (sumArr_a == sumArr_b) && (sumArr_b == sumArr_c);
		return testResult;
	}

	/**
	 * Returns the sum of all the values in the given array, from index start to
	 * index stop-1.
	 */
	public static int sum(int[] arr, int start, int stop) {
		int sum = 0;
		for (int i = start; i <= stop - 1; i++)
			sum += arr[i];

		return sum;
	}

	private static boolean test_maxSub() {
		int[] a = { 2, 1, 9, 3 };
		int[] b = { 10, -1, 1, 9, 3 };
		int[] c = { 2, 2, 3, 4, -1 };
		int[] subA3 = maxSub(a, 3);
		int[] subB3 = maxSub(b, 3);
		int[] subC3 = maxSub(c, 3);
		boolean testResult = equals(subA3, subB3) && !(equals(subB3, subC3));
		return testResult;
	}

	/**
	 * Goes through all the subarrays of length m in the given array, and returns
	 * the subarray that has the largest sum of array values.
	 */
	public static int[] maxSub(int[] arr, int m) {
		int max[] = new int[m];

		for (int i = 0; i < m; i++) {
			max[i] = arr[i];
		}
		for (int i = 1; i <= arr.length - m; i++) {
			if (sum(arr, i, i + m) > sum(max, 0, m)) {
				for (int j = i, k = 0; j < i + m; j++, k++) {
					max[k] = arr[j];
				}
			}
		}

		return max;
	}
}