package Solutions.aoc23;

import java.util.ArrayList;
import java.util.List;

class Day3 {

	public Day3() {
	}

	public double getPartNumberFromLine(List<char[]> lines, int lineNumber, int startIndex, boolean replaceDigit) {
		if (lineNumber < 0 || lineNumber >= lines.size()) {
			return 0;
		}

		char[] line = lines.get(lineNumber);
		if (startIndex < 0 || startIndex >= line.length) {
			return 0;
		}
		if (!Character.isDigit(line[startIndex])) {
			return 0;
		}
		double result = 0;
		while (startIndex > -1) {
			if (!Character.isDigit(line[startIndex])) {
				break;
			}
			startIndex--;
		}

		while (++startIndex < line.length) {
			if (!Character.isDigit(line[startIndex])) {
				break;
			}
			result = result * 10 + line[startIndex] - '0';
			if (replaceDigit) {
				line[startIndex] = '.';
			}
		}

		return result;
	}

	public double sumOfAllPartNumbers(List<char[]> lines) {
		double result = 0;
		for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
			char[] line = lines.get(lineNumber);
			for (int charIndex = 0; charIndex < line.length; charIndex++) {
				if (line[charIndex] == '.' || Character.isDigit(line[charIndex])) {
					continue;
				}
				for (int checkLineNumber = lineNumber - 1; checkLineNumber <= lineNumber
						+ 1; checkLineNumber++) {
					for (int offset = -1; offset < 2; offset++) {
						result = result + getPartNumberFromLine(lines, checkLineNumber,
								charIndex + offset, true);
					}
				}
			}
		}
		return result;
	}

	public List<Integer> getNumberStartIndexes(List<char[]> lines, int lineNumber, int startInd) {
		List<Integer> result = new ArrayList<>();
		if (lineNumber < 0 || lineNumber >= lines.size()) {
			return result;
		}
		char[] line = lines.get(lineNumber);
		if (startInd < 0 || startInd >= line.length) {
			return result;
		}
		for (int index = Math.max(0, startInd); index < Math.min(startInd + 3, line.length); index++) {
			if (!Character.isDigit(line[index])) {
				continue;
			}
			int curIndex = index;
			while (curIndex > -1) {
				if (!Character.isDigit(line[curIndex])) {
					break;
				}
				curIndex--;
			}
			if (result.size() == 0) {
				result.add(++curIndex);
			} else if (result.getLast() < ++curIndex) {
				result.add(curIndex);
			}
		}

		return result;
	}

	private double getSumProductOfAllPairs(List<Double> numbers) {
		if (numbers.size() < 2) {
			return 0;
		}
		double result = 0;

		for (int cur = 0; cur < numbers.size() - 1; cur++) {
			for (int next = cur + 1; next < numbers.size(); next++) {
				result = result + numbers.get(cur) * numbers.get(next);
			}
		}

		return result;
	}

	public double sumOfAllGears(List<char[]> lines) {
		double result = 0;
		for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
			char[] line = lines.get(lineNumber);

			for (int charIndex = 0; charIndex < line.length; charIndex++) {
				if (line[charIndex] == '.' || line[charIndex] != '*'
						|| Character.isDigit(line[charIndex])) {
					continue;
				}

				List<Double> numbers = new ArrayList<>();
				for (int checkLineNumber = lineNumber - 1; checkLineNumber <= lineNumber
						+ 1; checkLineNumber++) {
					List<Integer> startInds = getNumberStartIndexes(lines, checkLineNumber,
							charIndex - 1);

					for (int startIndex : startInds) {
						numbers.add(getPartNumberFromLine(lines, checkLineNumber, startIndex,
								false));
					}
				}
				result = result + getSumProductOfAllPairs(numbers);
			}
		}
		return result;
	}
}
