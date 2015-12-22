package game.war;

public class Player {
	private int numHandsWon;
	private int numWarsWon;
	private Deck currDeck;

	Player() {
		currDeck = new Deck();
		numHandsWon = 0;
		numWarsWon = 0;
	}

	public void takeCard(Card toTake) {
		currDeck.addCard(toTake);
	}

	public int getNumCards() {
		return currDeck.getNumCards();
	}

	public Card giveCard() {
		if (getNumCards() <= 0) {
			return new Card(1);
		}
		return currDeck.removeCard();
	}

	public Card seeCard() {
		if (getNumCards() <= 0) {
			return new Card(1);
		}
		return currDeck.seeCard();
	}

	public int getNumHandsWon() {
		return numHandsWon;
	}

	public void incrementNumHandsWon() {
		this.numHandsWon++;
	}

	public int getNumWarsWon() {
		return numWarsWon;
	}

	public void incrementNumWarsWon() {
		this.numWarsWon++;
	}

}
