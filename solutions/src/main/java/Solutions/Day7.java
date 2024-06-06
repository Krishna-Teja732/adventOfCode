package Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Solutions.utils.Pair;

class Day7 {
	private List<Pair<CardSet, Integer>> cardsAndBets;

	public Day7(List<String> inputs) {
		CardSet.initializeCardToValueMap();
		formatInput(inputs);
	}

	public double getTotalValue() {
		double result = 0;
		int rank = 1;

		cardsAndBets.sort((pair1, pair2) -> pair1.key.compareTo(pair2.key));

		for (Pair<CardSet, Integer> pair : cardsAndBets) {
			result = result + pair.value * rank;
			rank++;
		}

		return result;
	}

	private void formatInput(List<String> inputs) {
		cardsAndBets = new ArrayList<>();

		for (String input : inputs) {
			if (input.isBlank()) {
				continue;
			}
			String[] cardAndBet = input.split(" ");
			this.cardsAndBets.add(new Pair<CardSet, Integer>(new CardSet(cardAndBet[0].toCharArray()),
					Integer.parseInt(cardAndBet[1])));
		}
	}

	private class CardSet implements Comparable<CardSet> {
		private static HashMap<Character, Integer> cardToValueMap = new HashMap<>();

		public int[] cardSet;
		public CardSetType cardSetType;

		public CardSet(char[] charCardSet) {
			this.cardSet = new int[charCardSet.length];
			for (int index = 0; index < cardSet.length; index++) {
				this.cardSet[index] = cardToValueMap.get(charCardSet[index]);
			}
			assignCardType();
		}

		@Override
		public int compareTo(CardSet cardSet) {
			if (this.cardSetType.value != cardSet.cardSetType.value) {
				return this.cardSetType.value - cardSet.cardSetType.value;
			}
			for (int cardNumber = 0; cardNumber < 5; cardNumber++) {
				if (this.cardSet[cardNumber] == cardSet.cardSet[cardNumber]) {
					continue;
				}
				return this.cardSet[cardNumber] - cardSet.cardSet[cardNumber];
			}
			return 0;
		}

		private void assignCardType() {
			int largestCount = 1;
			int secondLargestCount = 1;

			int[] cardCounts = new int[15];
			for (int card : cardSet) {
				cardCounts[card]++;
			}

			for (int count : cardCounts) {
				if (count > largestCount) {
					secondLargestCount = largestCount;
					largestCount = count;
				} else if (count > secondLargestCount) {
					secondLargestCount = count;
				}
			}

			switch (largestCount) {
				case 5 -> this.cardSetType = CardSetType.FiveOfAKind;
				case 4 -> this.cardSetType = CardSetType.FourOfAKind;
				case 3 -> this.cardSetType = secondLargestCount == 2 ? CardSetType.FullHouse
						: CardSetType.ThreeOfAKind;
				case 2 -> this.cardSetType = secondLargestCount == 2 ? CardSetType.TwoPair
						: CardSetType.OnePair;
				default -> this.cardSetType = CardSetType.HighCard;
			}
		}

		public static void initializeCardToValueMap() {
			for (int card = 2; card < 10; card++) {
				cardToValueMap.put((char) (card + '0'), card);
			}

			cardToValueMap.put('T', 10);
			cardToValueMap.put('J', 11);
			cardToValueMap.put('Q', 12);
			cardToValueMap.put('K', 13);
			cardToValueMap.put('A', 14);
		}

		private enum CardSetType {
			FiveOfAKind(10),
			FourOfAKind(9),
			FullHouse(8),
			ThreeOfAKind(7),
			TwoPair(6),
			OnePair(5),
			HighCard(4);

			public final int value;

			private CardSetType(int value) {
				this.value = value;
			}
		}
	}
}
