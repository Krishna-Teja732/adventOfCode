package Solutions;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

	private List<char[]> grid;

	public Day14(List<String> inputs) {
		formatInput(inputs);
	}

	private void formatInput(List<String> inputs) {
		grid = new ArrayList<>();

		for (String line : inputs) {
			grid.add(line.toCharArray());
		}
	}

	public double getLoad() {
		double totalLoad = 0.0;

		for (int col = 0; col < grid.getFirst().length; col++) {
			totalLoad += getLoadPerColumn(col);
		}

		return totalLoad;
	}

	private double getLoadPerColumn(int colIndex) {
		double load = 0;
		int finalRockIndex = 0;

		for (int rowIndex = 0; rowIndex < grid.size(); rowIndex++) {
			char curChar = getCharFromGrid(rowIndex, colIndex);
			if (curChar == 'O') {
				load += grid.size() - finalRockIndex;
				finalRockIndex++;
			}
			if (curChar == '#') {
				finalRockIndex = rowIndex + 1;
			}
		}

		return load;
	}

	private char getCharFromGrid(int rowIndex, int colIndex) {
		return grid.get(rowIndex)[colIndex];
	}
}
