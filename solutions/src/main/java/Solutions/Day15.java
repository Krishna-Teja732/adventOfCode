package Solutions;

import java.util.Arrays;
import java.util.List;

public class Day15 {

	private List<String> lines;
	private int[] asciiMap;

	public Day15(String input) {
		lines = Arrays.stream(input.split(",")).toList();
		asciiMap = new int[256];

		for (int index = 0; index < asciiMap.length; index++) {
			asciiMap[index] = (index * 17) % 256;
		}
	}

	public double getResult() {
		double result = 0;

		for (String line : lines) {
			result = result + getHash(line.toCharArray());
		}

		return result;
	}

	private int getHash(char[] line) {
		int hash = 0;

		for (char elem : line) {
			hash = (asciiMap[hash] + asciiMap[elem]) % 256;
		}

		return hash;
	}

}
