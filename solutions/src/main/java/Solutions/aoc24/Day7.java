package Solutions.aoc24;

import java.util.List;
import java.util.ArrayList;

import Solutions.utils.FormatInputHelper;

public class Day7 {

	private ArrayList<Long> testResults;
	private ArrayList<ArrayList<Long>> testInputs;

	public Day7(List<String> inputs) {
		testResults = new ArrayList<>();
		testInputs = new ArrayList<>();

		for (String line : inputs) {
			ArrayList<Long> testCase = new ArrayList<>(
					FormatInputHelper.extractNumbersFromStringALongs(line));

			testResults.add(testCase.remove(0));
			testInputs.add(testCase);
		}
	}

	public double getResult() {
		long result = 0;
		for (int index = 0; index < this.testInputs.size(); index++) {
			long target = this.testResults.get(index);
			long testResult = this.testInputs.get(index).getFirst();
			if (eval(this.testInputs.get(index), target, testResult, 1)) {
				result += this.testResults.get(index);
			}
		}
		return result;
	}

	private boolean eval(ArrayList<Long> testInput, long target, long testResult, int inputIndex) {
		if (testResult > target) {
			return false;
		}
		if (inputIndex == testInput.size()) {
			if (testResult == target) {
				return true;
			}
			return false;
		}

		return eval(testInput, target, testResult * testInput.get(inputIndex), inputIndex + 1)
				|| eval(testInput, target, testResult + testInput.get(inputIndex), inputIndex + 1);
	}

	public long getResultPart2() {
		long result = 0;
		for (int index = 0; index < this.testInputs.size(); index++) {
			long target = this.testResults.get(index);
			long testResult = this.testInputs.get(index).getFirst();
			if (evalV2(this.testInputs.get(index), target, testResult, 1)) {
				result += this.testResults.get(index);
			}
		}
		return result;
	}

	private boolean evalV2(ArrayList<Long> testInput, long target, long testResult, int inputIndex) {
		if (testResult > target) {
			return false;
		}
		if (inputIndex == testInput.size()) {
			if (testResult == target) {
				return true;
			}
			return false;
		}

		return evalV2(testInput, target, testResult * testInput.get(inputIndex), inputIndex + 1)
				|| evalV2(testInput, target, testResult + testInput.get(inputIndex), inputIndex + 1)
				|| evalV2(testInput, target, concatNumbers(testResult, testInput.get(inputIndex)),
						inputIndex + 1);

	}

	public long concatNumbers(long prefix, long suffix) {
		int digitCount = 0;
		long suffixCopy = suffix;
		while (suffixCopy > 0) {
			suffixCopy = suffixCopy / 10;
			digitCount++;
		}

		return (long) Math.pow(10, digitCount) * prefix + suffix;
	}
}
