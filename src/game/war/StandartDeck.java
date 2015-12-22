package game.war;

public class StandartDeck extends Deck {

	public StandartDeck() {
		super();

		for (int currentCardIndex = 0; currentCardIndex < 52; currentCardIndex++) {
			cards.add(new Card(currentCardIndex));
		}
	}

}
