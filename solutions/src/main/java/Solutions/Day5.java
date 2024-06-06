package Solutions;

import java.util.List;
import java.util.ArrayList;

class Day5 {

	private List<String> inputs;
	private List<Integer> seeds;

	public Day5(List<String> inputs) {
		this.inputs = inputs;
		formatInput();
	}

	private void formatInput() {
		int index = 0;

		this.seeds = new ArrayList<>();
		String[] seedValues = inputs.get(index++).split(":")[1].strip().split(" ");
		for (String value : seedValues) {
			seeds.add(Integer.parseInt(value));
		}

	}

	private int parseMapping(int index) {
		while (!inputs.get(index).isBlank()) {
		}
		return index;
	}
}
