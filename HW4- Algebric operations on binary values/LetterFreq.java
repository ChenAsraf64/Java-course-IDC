/*
Assignment number : 4

File name : LetterFreq.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/


public class LetterFreq {
	static double[] en = { 0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
			0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
			0.0236, 0.0015, 0.01974, 0.00074 };
	static double[] de = { 0.06516, 0.01886, 0.02732, 0.05076, 0.16396, 0.01656, 0.03009, 0.04577, 0.0655, 0.00268,
			0.01417, 0.03437, 0.02534, 0.09776, 0.02594, 0.0067, 0.00018, 0.07003, 0.0727, 0.06154, 0.04166, 0.00846,
			0.01921, 0.00034, 0.00039, 0.01134 };
	static double[] fr = { 0.07636, 0.00901, 0.0326, 0.03669, 0.14715, 0.01066, 0.00866, 0.00737, 0.07529, 0.00613,
			0.00074, 0.05456, 0.02968, 0.07095, 0.05796, 0.02521, 0.01362, 0.06693, 0.07948, 0.07244, 0.06311, 0.01838,
			0.00049, 0.00427, 0.00128, 0.00326 };

	public static void main(String[] args) {
		String text = StdIn.readAll();
		if (text != null) {
			System.out.println("en" + score(en, frequencies(text)));
			System.out.println("de" + score(de, frequencies(text)));
			System.out.println("fr" + score(fr, frequencies(text)));
		}
	}

	/**
	 * Computes the frequencies of the letters a-z in the given string. Frequencies
	 * are between 0 and 1 and sum to 1. The function is case-insensitive: 'a' and
	 * 'A' are treated and counted the same way. All the non-alphabetic characters
	 * are ignored.
	 *
	 * @param text The text to analyze
	 * @return An array of letter frequencies: element at index i is the frequency
	 *         of letter i in the text.
	 */
	public static double[] frequencies(String text) {
		String str = alpha(text);
		str = lowercase(str);
		int[] numbersOfLetters = new int[26];
		double[] q = new double[26];
		double sizeOfText = str.length();
		for (int i = 0; i < sizeOfText; i++) {
			numbersOfLetters[str.charAt(i) - 97]++;
		}
		for (int j = 0; j < 26; j++) {
			q[j] = numbersOfLetters[j] / sizeOfText;
		}

		return q;
	}

	public static double score(double[] p, double[] q) {
		double score = 0;
		for (int i = 0; i < 26; i++) {
			score += Math.pow(p[i] - q[i], 2);
		}
		return score;
	}

	public static String alpha(String str) {

		String alphabet = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) {
				alphabet += c;
			}
		}

		return alphabet;
	}

	public static String lowercase(String str) {

		String lowerCase = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 65 && c <= 90) {
				c += 32;
			}
			lowerCase += c;
		}
		return lowerCase;
	}

}
