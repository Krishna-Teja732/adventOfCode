package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Day4Part2 {

	private List<String> winningCards;
	private List<String> myCards;
	private int[] cardCount;

	public Day4Part2(List<String> games) {
		winningCards = new ArrayList<>();
		myCards = new ArrayList<>();

		cardCount = new int[games.size()];
		Arrays.fill(cardCount, 1);

		for (String game : games) {
			game = game.substring(game.indexOf(":") + 1);
			int splitIndex = game.indexOf("|");
			winningCards.add(game.substring(0, splitIndex).strip());
			myCards.add(game.substring(splitIndex + 1).strip());
		}
	}

	private int getScoreForGame(String winningCardNumbers, String myCardNumbers) {
		HashSet<String> winningSet = new HashSet<>();
		for (String cardNo : winningCardNumbers.split(" ")) {
			if (cardNo.isBlank()) {
				continue;
			}
			winningSet.add(cardNo.strip());
		}

		int count = 0;
		for (String cardNo : myCardNumbers.split(" ")) {
			if (cardNo.isBlank()) {
				continue;
			}
			if (winningSet.contains(cardNo)) {
				count++;
			}
		}
		return count;
	}

	private void updateCardCount(int gameNo, int offset) {
		for (int index = gameNo + 1; index < gameNo + offset + 1; index++) {
			cardCount[gameNo] += cardCount[index];
		}
	}

	public int getScore() {
		for (int gameNo = this.cardCount.length - 1; gameNo > -1; gameNo--) {
			updateCardCount(gameNo, getScoreForGame(winningCards.get(gameNo), myCards.get(gameNo)));
		}
		int score = 0;
		for (int count : cardCount) {
			score += count;
		}
		return score;
	}
}
