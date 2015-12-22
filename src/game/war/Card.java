package game.war;

public class Card {
	private static final int SPECIAL_CARDS_OFFSET = 11;
	private static final char[] SPECIAL_CARDS = { 'J', 'Q', 'K', 'A' };
	private static final String[] COLORS = { "Clubs", "Diamonds", "Hearts", "Spades" };
	private int value;
	private int color;

	public Card(int cardNumber) {
		if (cardNumber > 52 || cardNumber < 0) {
			return;
		}
		this.value = 2 + (cardNumber / 4);
		this.color = cardNumber % 4;
	}

	public Card(int value, int color) {
		if (value <= 2 || value >= 14) { // 11 = J, 12 = Q, 13 = K,14 = A
			return;
		}
		if (color < 0 || color > 0) {
			return;
		}
		this.value = value;
		this.color = color;
	}

	public void print() {
		if (value > 10) {
			System.out.print(SPECIAL_CARDS[value - SPECIAL_CARDS_OFFSET] + " ");
		} else {
			System.out.print(this.value + " ");
		}
		System.out.println("of " + COLORS[this.color]);
	}

	public int compare(Card otherCard) {
		if (this.value > otherCard.value) {
			return 1;
		} else {
			if (this.value < otherCard.value) {
				return -1;
			}
		}
		return 0;
	}

}
