package Solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day1 {

	private HashMap<String, Integer> validWords;
	private HashMap<String, Integer> validWordsReversed;

	public Day1() {
		List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight",
				"nine");
		validWords = new HashMap<>();
		validWordsReversed = new HashMap<>();
		for (int index = 0; index < words.size(); index++) {
			validWords.put(words.get(index), index + 1);
			validWordsReversed.put(new StringBuilder(words.get(index)).reverse().toString(), index + 1);
		}
	}

	public int processValue(String value) {
		int result = 0;

		int index = 0;
		while (index < value.length()) {
			if (Character.isDigit(value.charAt(index))) {
				break;
			}
			index++;
		}
		if (index < value.length()) {
			result = value.charAt(index) - '0';
		} else {
			return 0;
		}

		index = value.length() - 1;
		while (index > -1) {
			if (Character.isDigit(value.charAt(index))) {
				break;
			}
			index--;
		}
		result = result * 10 + value.charAt(index) - '0';
		return result;
	}

	public int sumOfCalibrationValues(List<String> values) {
		int result = 0;

		for (String value : values) {
			result = result + processValue(value);
		}

		return result;
	}

	public int processValueIIHelper(String value, HashMap<String, Integer> validWords) {
		int startIndex = 0;
		for (; startIndex <= value.length() - 3; startIndex++) {
			if (Character.isDigit(value.charAt(startIndex))) {
				return value.charAt(startIndex) - '0';
			}
			for (int subStrLen = 3; subStrLen < Math.min(6, value.length() - startIndex + 1); subStrLen++) {
				String subStr = value.substring(startIndex, startIndex + subStrLen);
				if (validWords.containsKey(subStr)) {
					return validWords.get(subStr);
				}
			}
		}
		for (; startIndex < value.length(); startIndex++) {
			if (Character.isDigit(value.charAt(startIndex))) {
				return value.charAt(startIndex) - '0';
			}
		}
		return 0;
	}

	public int processValueII(String value) {
		int result = processValueIIHelper(value, validWords);
		result = result * 10 + processValueIIHelper(new StringBuilder(value).reverse().toString(),
				validWordsReversed);
		return result;
	}

	public int sumOfCalibrationValuesII(List<String> values) {
		int result = 0;

		for (String value : values) {
			result = result + processValueII(value);
		}

		return result;
	}

}
