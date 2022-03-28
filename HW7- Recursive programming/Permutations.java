/*
Assignment number : 7

File name : Permutations.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

/******************************************************************************
 * Compilation: javac Permutations.java Execution: java Permutations n
 *
 * Enumerates all permutations on n elements. Two different approaches are
 * included.
 *
 * % java Permutations 3 abc acb bac bca cab cba
 *
 ******************************************************************************/

public class Permutations {

	private static void permutations(String elements, String prefix) {
		if (elements.length() == 0)
			System.out.print(prefix + " ");

		else {
			// String s, rest;
			// char ch;
			for (int i = 0; i < elements.length(); i++) {
				String ch = "" + elements.charAt(i);
				String s = prefix + ch;
				String rest = elements.substring(0, i) + elements.substring(i + 1);
				permutations(rest, s);
			}
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		String elements = "abcdefghijklmnopqrstuvwxyz";
		String elementSub = elements.substring(0, n - 1);
		permutations(elementSub, "");

	}

}
