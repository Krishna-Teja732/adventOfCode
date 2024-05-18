package Solutions.utils;

import java.util.List;
import java.util.ArrayList;

public class FormatInputHelper {

	public static List<Integer> extractNumbersFromString(String input) {
		List<Integer> result = new ArrayList<>();
		int index = 0;

		while (index < input.length()) {
			if (!Character.isDigit(input.charAt(index))) {
				index++;
				continue;
			}
			int number = 0;
			while (index < input.length() && Character.isDigit(input.charAt(index))) {
				number = number * 10 + input.charAt(index) - '0';
				index++;
			}
			result.add(number);
		}
		return result;
	}
}
