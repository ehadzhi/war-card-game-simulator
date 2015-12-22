package game.war;

import java.util.LinkedList;
import java.util.Random;

public class Deck {
	private static final int TIMES_TO_SHUFFLE = 300;
	protected LinkedList<Card> cards;

	Deck() {
		cards = new LinkedList<Card>();
	}

	public void addCard(Card toAdd) {
		this.cards.add(toAdd);
	}

	public Card removeCard() {
		if (cards.size() == 0) {
			return new Card(1);
		}
		return cards.poll();
	}

	public Card seeCard() {
		if (cards.size() == 0) {
			return new Card(1);
		}
		return cards.peek();
	}

	public int getNumCards() {
		return cards.size();
	}

	public void shuffle() {
		Random rand = new Random();
		Card temp;
		int index;
		for (int i = 0; i < TIMES_TO_SHUFFLE; i++) {
			index = rand.nextInt(getNumCards());
			temp = cards.remove(index);
			cards.add(temp);
		}
	}
}
