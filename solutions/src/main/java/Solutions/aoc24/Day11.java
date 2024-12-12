package Solutions.aoc24;

import java.util.LinkedList;
import java.util.List;

import Solutions.utils.FormatInputHelper;

public class Day11 {

	private LinkedList<Long> stones;

	public Day11(List<String> inputs) {
		stones = new LinkedList<>(FormatInputHelper.extractNumbersFromStringALongs(inputs.getFirst()));
	}

	public int getResult() {
		for (int iter = 0; iter < 75; iter++) {
			rearrange();
		}
		return stones.size();
	}

	private void rearrange() {
		LinkedList<Long> newList = new LinkedList<>();

		for (long stone : stones) {
			if (stone == 0) {
				newList.add(1L);
				continue;
			}
			int digitCount = getDigitCount(stone);
			if (digitCount % 2 != 0) {
				newList.add(stone * 2024);
			} else {
				long mask = (long) Math.pow(10, digitCount / 2);
				long msb = stone / mask;
				long lsb = stone - msb * mask;
				newList.add(msb);
				newList.add(lsb);
			}
		}

		stones.clear();
		stones.addAll(newList);
	}

	public int getDigitCount(long num) {
		int result = 0;
		while (num > 0) {
			num = num / 10;
			result++;
		}
		return result;
	}

	public int getResultPart2() {
		return 0;
	}

}
