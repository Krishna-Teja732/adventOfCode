package Solutions;

import java.util.List;
import java.util.ArrayList;

class Day5 {

	private List<String> inputs;
	private ArrayList<Double> seeds;
	private int inputIndex;

	public Day5(List<String> inputs) {
		this.inputIndex = 0;
		this.inputs = inputs;
		this.seeds = new ArrayList<>();
		formatInput();
	}

	private void formatInput() {
		String[] seedValues = inputs.get(inputIndex++).split(":")[1].strip().split(" ");
		for (String value : seedValues) {
			seeds.add(Double.parseDouble(value));
		}
	}

	public Double getResult() {
		while ((inputIndex = parseMapping(inputIndex)) < inputs.size())
			;

		double min = seeds.getFirst();

		for (double val : seeds) {
			min = Math.min(val, min);
		}

		return min;
	}

	private int parseMapping(int index) {
		boolean[] visited = new boolean[seeds.size()];
		String line;
		while (index < inputs.size() && !(line = inputs.get(index++)).isBlank()) {
			double[] mapping = getNumbers(line);
			updateSeeds(mapping[1], mapping[2], mapping[0] - mapping[1], visited);
		}
		return index + 1;
	}

	private void updateSeeds(double min, double max, double delta, boolean[] visited) {
		for (int index = 0; index < seeds.size(); index++) {
			double val = seeds.get(index);
			if (min <= val && val <= max && !visited[index]) {
				seeds.set(index, val + delta);
				visited[index] = true;
			}
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
