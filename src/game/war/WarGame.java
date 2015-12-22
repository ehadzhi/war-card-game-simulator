package game.war;

public class WarGame {
	private static final double WAIT_SECONDS = 1d;
	private static final int NUM_PLAYERS = 2;
	private static final int INITIAL_WAR_BET = 3;
	private static final int SECONDARY_WAR_BET = 1;
	private Player[] players;

	public WarGame() {
		SetInitial();
	}

	private void SetInitial() {
		players = new Player[NUM_PLAYERS];
		for (int index = 0; index < NUM_PLAYERS; index++) {
			players[index] = new Player();
		}
		StandartDeck initialDeck = new StandartDeck();
		initialDeck.shuffle();
		dealingCards(initialDeck);
	}

	public void playGame() {
		Player player1 = players[0];
		Player player2 = players[1];
		Deck emptyBuffer = new Deck();
		int currentWinner;

		while (checkForEnd() == false) {
			wait(WAIT_SECONDS);
			System.out.println("\nPl 1 = " + player1.getNumCards() 
			+ " Pl 2 = " + player2.getNumCards() + " current hand = " 
					+ (player1.getNumHandsWon() + player2.getNumHandsWon()) );
			currentWinner = player1.seeCard().compare(player2.seeCard());
			determineExchange(player1, player2, currentWinner, emptyBuffer);
		}
		outputStats();
	}

	private void dealingCards(StandartDeck initialDeck) {
		while (initialDeck.getNumCards() > 0) {
			players[0].takeCard(initialDeck.removeCard());
			if (initialDeck.getNumCards() <= 0) {
				break;
			}
			players[1].takeCard(initialDeck.removeCard());
		}
	}

	private void determineExchange(Player player1, Player player2,
			int currentWinner, Deck bufferDeck) {
		printNextCards(player1, player2);

		if (currentWinner > 0) {
			player1.incrementNumHandsWon();
			if( bufferDeck.getNumCards() > 0 ){
				player1.incrementNumWarsWon();
			}
			exchangeCards(player1, player2, bufferDeck);
			System.out.println("Player 1 won ");
			return;
		}
		if (currentWinner < 0) {
			player2.incrementNumHandsWon();
			if( bufferDeck.getNumCards() > 0 ){
				player2.incrementNumWarsWon();
			}
			exchangeCards(player2, player1, bufferDeck);
			System.out.println("Player 2 won ");
			return;
		}
		if (currentWinner == 0) {
			if (bufferDeck.getNumCards() <= 0) {
				System.out.println("\n        -= WAR =- ");
				addToBufferDeck(INITIAL_WAR_BET, bufferDeck, player1, player2);
			} else {
				addToBufferDeck(SECONDARY_WAR_BET, bufferDeck, player1, player2);
			}
			currentWinner = player1.seeCard().compare(player2.seeCard());
			determineExchange(player1, player2, currentWinner, bufferDeck);
		}
	}

	private void addToBufferDeck(int numCards, Deck temp, Player player1, Player player2) {
		for (int i = 0; i < numCards; i++) {
			if (player1.getNumCards() < numCards || player2.getNumCards() < numCards) {
				break;
			}
			printNextCards(player1, player2);
			temp.addCard(player1.giveCard());
			temp.addCard(player2.giveCard());
			wait(WAIT_SECONDS);
		}
	}

	private void exchangeCards(Player winner, Player loser, Deck temp) {
		while (temp.getNumCards() > 0) {
			winner.takeCard(temp.removeCard());
		}
		winner.takeCard(loser.giveCard());
		winner.takeCard(winner.giveCard());
	}

	private void outputStats() {
		System.out.println("Player 1 won " + players[0].getNumHandsWon() + " hands and "
				+ players[0].getNumWarsWon() + " wars.");
		System.out.println("Player 2 won " + players[1].getNumHandsWon() + " hands and "
				+ players[1].getNumWarsWon() + " wars.");
	}

	private boolean checkForEnd() {
		return (players[0].getNumCards() <= 0 || players[1].getNumCards() <= 0);
	}

	private void printNextCards(Player pl1, Player pl2) {
		System.out.print("Player 1 gives ");
		pl1.seeCard().print();
		System.out.print("Player 2 gives ");
		pl2.seeCard().print();
		System.out.println();
	}

	private static void wait(double seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
