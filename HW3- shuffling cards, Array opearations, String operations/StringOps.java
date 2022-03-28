/*
Assignment number : 3

File name : StringOps.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class StringOps {

	// Tests the functions of this class.
	public static void main(String[] args) {
		test_all();
	}

	private static void test_all() {
		boolean testResult = test_alpha() && test_reverse() && test_lowercase() && test_is_palindrome() && test_find();
		System.out.println("success: " + testResult);
	}

	private static boolean test_alpha() {
		return alpha("Crickets talking back and forth in rhyme.").equals("Cricketstalkingbackandforthinrhyme");
	}

	/**
	 * Returns a version of the given string in which all the non-alphabet
	 * characters are removed.
	 */
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

	private static boolean test_reverse() {
		return reverse("Blue river running slow and lazy").equals("yzal dna wols gninnur revir eulB");
	}

	/**
	 * Returns a reversed version of the given string.
	 */
	public static String reverse(String str) {

		String reversed = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			reversed += c;

		}
		return reversed;
	}

	private static boolean test_lowercase() {
		return lowercase("San Francisco, Ashtabula").equals("san francisco, ashtabula");
	}

	/**
	 * Returns a version of the given string in which all the uppercase alphabet
	 * characters are changed to lowercase. Other characters are not changed.
	 */
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

	private static boolean test_is_palindrome() {
		String s = "A man, a plan, a canal, Panama.";
		String s2 = "A boy in a bucket";
		return (is_palindrome(s) && !is_palindrome(s2));
	}

	/**
	 * Returns true if the given string is a palindrome, and false otherwise.
	 * Non-alphabetic characters and casing are ignored. For example, "Madam, in
	 * Eden, I’m Adam." is a palindrome.
	 */
	// Hint: This function can be implemented elegantly and simply by using other
	// functions in this class.
	public static boolean is_palindrome(String str) {
		String alphabet = alpha(str);
		String lowercase = lowercase(alphabet);
		String reversed = reverse(lowercase);

		if (lowercase.equals(reversed)) {
			return true;
		}
		return false;
	}

	private static boolean test_find() {
		String str = "Flowers on the hillside blooming crazy";
		String s = "hillside";
		boolean testResult = find(str, s) == 15;
		String s2 = "flowers";
		testResult = testResult && (find(str, s2) == -1);
		String s3 = "lonesome";
		testResult = testResult && (find(str, s3) == -1);

		return testResult;
	}

	/**
	 * Returns the index of the given substring in the given string, or -1 if there
	 * is no such substring. If the substring occurs more than once, returns the
	 * index of the first occurence. Casing matters, so "A" doesn't equal "a".
	 */
	public static int find(String str, String substr) {
		for (int i = 0; i < str.length() - substr.length(); i++) {
			char c = str.charAt(i);
			if (substr.charAt(0) == c) {
				int sumEq = 1;
				for (int j = 1; j < substr.length(); j++) {
					sumEq++;
					if (substr.charAt(j) != str.charAt(i + j)) {
						break;

					}
					if (sumEq == substr.length())
						return i;
				}
			}
		}

		return -1;
	}
}
