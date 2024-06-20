package Solutions;

import java.util.List;
import java.util.LinkedList;

import Solutions.utils.Pair;

public class Day5Part2 {

	private List<String> inputs;
	private List<Pair<Double, Double>> seeds;
	private int inputIndex;

	public Day5Part2(List<String> inputs) {
		this.inputIndex = 0;
		this.inputs = inputs;
		this.seeds = new LinkedList<>();
		formatInput();
	}

	private void formatInput() {
		String[] seedValues = inputs.get(inputIndex++).split(":")[1].strip().split(" ");
		for (int index = 0; index + 1 < seedValues.length; index += 2) {
			Double min = Double.parseDouble(seedValues[index]);
			Double max = min + Double.parseDouble(seedValues[index + 1]) - 1;
			seeds.add(new Pair<>(min, max));
		}
	}

	public Double getResult() {
		while ((inputIndex = parseMapping(inputIndex)) < inputs.size())
			;

		double min = Double.MAX_VALUE;

		for (Pair<Double, Double> range : seeds) {
			min = Math.min(range.key, min);
		}

		return min;
	}

	private int parseMapping(int index) {
		String line;
		List<Pair<Double, Double>> updatedValues = new LinkedList<>();
		while (index < inputs.size() && !(line = inputs.get(index++)).isBlank()) {
			double[] mapping = getNumbers(line);
			updateSeeds(mapping[1], mapping[2], mapping[0] - mapping[1], updatedValues);
		}
		seeds.addAll(updatedValues);
		return index + 1;
	}

	private void updateSeeds(double min, double max, double delta, List<Pair<Double, Double>> updatedValues) {
		int index = 0;
		int maxIndex = seeds.size();
		while (index < maxIndex) {
			Pair<Double, Double> range = seeds.get(index);
			if (min > range.value || max < range.key) {
				index++;
				continue;
			}
			seeds.remove(index);
			maxIndex = maxIndex - 1;
			Pair<Double, Double> updatedRange = new Pair<>(range.key, range.value);
			if (min > range.key) {
				updatedRange.key = min;
				seeds.add(new Pair<>(range.key, min - 1));
			}
			if (max < range.value) {
				updatedRange.value = max;
				seeds.add(new Pair<>(max + 1, range.value));
			}
			updatedRange.key += delta;
			updatedRange.value += delta;
			updatedValues.add(updatedRange);
		}
	}

	private double[] getNumbers(String line) {
		double[] res = new double[3];
		int numIndex = 0;

		for (char ch : line.toCharArray()) {
			switch (ch) {
				case ' ' -> numIndex++;
				default -> res[numIndex] = res[numIndex] * 10 + ch - '0';
			}
		}
		res[2] += res[1] - 1;

		return res;
	}
}
