/*
Assignment number : 4

File name : Primes.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/


public class Primes {
	public static void main(String[] args) {
		// TODO: Print results according to assignment guidelines
		int N = Integer.parseInt(args[0]);
		boolean[] res = sieveOfSundaram(N);
		int nNew = ((N - 2) / 2) + 1;
		if (N >= 2) {
			System.out.print(2 + " ");
		}
		for (int i = 1; i < nNew; i++) {
			if (res[i] == false) {
				System.out.print(2 * i + 1 + " ");
			}

		}
	}

	/*
	 * Returns a boolean array of length N in which the element at index i is true
	 * if i is a prime and false otherwise.
	 */
	private static boolean[] sieveOfSundaram(int N) {
		// TODO: Implement
		boolean[] marked = new boolean[N];
		int nNew = (N - 2) / 2;
		for (int i = 1; i <= nNew; i++) {
			for (int j = i; (i + j + 2 * i * j) <= nNew; j++) {
				marked[i + j + 2 * i * j] = true;
			}
		}

		return marked;

	}
}