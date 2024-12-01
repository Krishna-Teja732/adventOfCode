package Solutions.aoc23;

import java.util.List;
import java.util.ArrayList;
import Solutions.utils.FormatInputHelper;

class Day9 {

	List<List<Double>> sequences;

	public Day9(List<String> input) {
		sequences = new ArrayList<>();
		for (String line : input) {
			sequences.add(FormatInputHelper.extractNumbersFromStringADoubles(line));
		}
	}

	public Double getSumOfAllNthTerms() {
		Double result = 0.0;
		for (List<Double> sequence : sequences) {
			result = result + findNextTerm(sequence);
		}
		return result;
	}

	private Double isArithemeticProgression(List<Double> sequence) {
		Double diff = (double) sequence.getLast() - sequence.getFirst();

		if (diff % (sequence.size() - 1) != 0) {
			return Double.NaN;
		}

		diff = diff / (sequence.size() - 1);

		if (sequence.size() == 2) {
			return diff;
		}

		for (int index = 1; index < sequence.size(); index++) {
			if (sequence.get(index) - sequence.get(index - 1) != diff) {
				return Double.NaN;
			}
		}

		return diff;
	}

	private Double findNextTerm(List<Double> sequence) {
		Double diff = isArithemeticProgression(sequence);
		if (!diff.isNaN()) {
			return sequence.getFirst() - diff.intValue();
		}

		Double firstTerm = sequence.getFirst();

		for (int index = 1; index < sequence.size(); index++) {
			sequence.set(index - 1, sequence.get(index) - sequence.get(index - 1));
		}

		sequence.removeLast();

		return firstTerm - findNextTerm(sequence);
	}

}
