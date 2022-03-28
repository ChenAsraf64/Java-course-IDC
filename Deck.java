
/*
Assignment number : 3

File name : Deck.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class Deck {

	public static void main(String[] args) {
		String[] deck = createDeck();
		printDeck(deck);
		printDeck(shuffled(deck));
		printDeck(deck);
	}

	public static String[] createDeck() {

		String[] suit = { "\u2660", "\u2666", "\u2665", "\u2663" };
		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		int Nsuit = suit.length;
		int Nrank = rank.length;
		int N = Nsuit * Nrank;

		String[] deck = new String[N];
		for (int i = 0; i < Nrank; i++) {
			for (int j = 0; j < Nsuit; j++) {
				deck[Nsuit * i + j] = suit[j] + rank[i];
			}
		}
		return deck;
	}

	public static void printDeck(String[] deck) {

		int N = deck.length;
		for (int i = 0; i < N; i++)
			System.out.print(deck[i] + " ");
		System.out.println();

	}

	public static String[] shuffled(String[] deck) {

		int N = deck.length;
		String[] deckS = new String[N];

		for (int i = 0; i < N - 1; i++) {
			int r = i + (int) (Math.random() * (N - i));
			deckS[i] = deck[i];
			deckS[r] = deck[r];
			String temp = deckS[r];
			deckS[r] = deckS[i];
			deckS[i] = temp;

		}
		for (int i = 0; i < N; i++)

			System.out.print(deckS[i] + " ");

		return new String[0];

	}
}
