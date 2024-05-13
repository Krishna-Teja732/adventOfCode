package Solutions;

import java.util.HashSet;

class Day4 {

	private HashSet<Integer> winningCardsDeck;
	private String myCards;

	public Day4(String winningCards, String myCards) {
		winningCardsDeck = new HashSet<>();
		this.myCards = myCards.strip();
		for (String card : winningCards.strip().split(" ")) {
			if (card.isBlank()) {
				continue;
			}
			winningCardsDeck.add(Integer.parseInt(card.strip()));
		}
	}

	public int getScore() {
		int scoreMultiplier = -1;

		for (String card : myCards.split(" ")) {
			if (card.isBlank()) {
				continue;
			}
			if (winningCardsDeck.contains(Integer.parseInt(card))) {
				scoreMultiplier++;
			}
		}

		return (int) Math.pow(2, scoreMultiplier);
	}
}
