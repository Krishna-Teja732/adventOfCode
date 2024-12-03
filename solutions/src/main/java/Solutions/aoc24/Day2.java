package Solutions.aoc24;

import java.util.List;
import Solutions.utils.FormatInputHelper;

public class Day2 {

	List<String> inputs;

	public Day2(List<String> inputs) {
		this.inputs = inputs;
	}

	public int getResult() {
		int result = 0;

		for (String line : inputs) {
			List<Integer> arr = FormatInputHelper.extractNumbersFromString(line);

			if (isStrictlyIncreasing(arr) || isStrictlyDecreasing(arr)) {
				result++;
			}
		}

		return result;
	}

	public int getResultPart2() {
		int result = 0;

		for (String line : inputs) {
			List<Integer> arr = FormatInputHelper.extractNumbersFromString(line);

			if (isStrictlyIncreasingPart2(arr) || isStrictlyDecreasingPart2(arr)) {
				result++;
			}
		}

		return result;
	}

	private boolean isStrictlyIncreasing(List<Integer> arr) {
		for (int index = 1; index < arr.size(); index++) {
			if (arr.get(index) - arr.get(index - 1) > 3 || arr.get(index) - arr.get(index - 1) <= 0) {
				return false;
			}
		}

		return true;
	}

	private boolean isStrictlyDecreasing(List<Integer> arr) {
		for (int index = 1; index < arr.size(); index++) {
			if (arr.get(index - 1) - arr.get(index) > 3 || arr.get(index - 1) - arr.get(index) <= 0) {
				return false;
			}
		}

		return true;
	}

	private boolean isStrictlyIncreasingPart2(List<Integer> arr) {
		int skipFlag = 0;
		for (int index = 1; index < arr.size(); index++) {
			if (arr.get(index) - arr.get(index - 1) > 3 || arr.get(index) - arr.get(index - 1) <= 0) {
				if (skipFlag == 1) {
					return false;
				}
				skipFlag = 1;
				if (index + 1 < arr.size() && (arr.get(index - 1) - arr.get(index + 1) > 3)) {
					return false;
				}
				index++;
			}
		}

		return true;
	}

	private boolean isStrictlyDecreasingPart2(List<Integer> arr) {
		int skipFlag = 0;
		for (int index = 1; index < arr.size(); index++) {
			if (arr.get(index - 1) - arr.get(index) > 3 || arr.get(index - 1) - arr.get(index) <= 0) {
				if (skipFlag == 1) {
					return false;
				}
				skipFlag = 1;
				if (index + 1 < arr.size() && (arr.get(index - 1) - arr.get(index + 1) > 3)) {
					return false;
				}
			}
		}

		return true;
	}
}
