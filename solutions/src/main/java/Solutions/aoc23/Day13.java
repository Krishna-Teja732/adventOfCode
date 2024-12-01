package Solutions.aoc23;

import Solutions.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
	private List<Pair<List<String>, List<String>>> grids;

	public Day13(List<String> inputs) {
		grids = new ArrayList<>();
		formatInputs(inputs);
	}

	private void formatInputs(List<String> inputs) {
		int index = 0;
		String line;
		while (index < inputs.size()) {
			List<String> rowMajorGrid = new ArrayList<>();
			while (index < inputs.size() && !(line = inputs.get(index++)).isBlank()) {
				rowMajorGrid.add(line);
			}
			grids.add(new Pair<>(rowMajorGrid, getTranspose(rowMajorGrid)));
		}
	}

	private List<String> getTranspose(List<String> grid) {
		List<String> transpose = new ArrayList<>();
		for (int y = 0; y < grid.getFirst().length(); y++) {
			StringBuilder builder = new StringBuilder();
			for (int x = 0; x < grid.size(); x++) {
				builder.append(String.valueOf(grid.get(x).charAt(y)));
			}
			transpose.add(builder.toString());
		}
		return transpose;
	}

	public double getResult() {
		double result = 0;
		for (Pair<List<String>, List<String>> record : grids) {
			int rows;
			if ((rows = getRowsAboveMirror(record.key)) != -1) {
				result = result + rows * 100;
			} else if ((rows = getRowsAboveMirror(record.value)) != -1) {
				result = result + rows;
			}
		}
		return result;
	}

	public int getRowsAboveMirror(List<String> grid) {
		int index = 0;
		while (index < grid.size() - 1) {
			if (mirrorExists(grid, index, index + 1)) {
				return index + 1;
			}
			index++;
		}
		return -1;
	}

	public boolean mirrorExists(List<String> grid, int startInd, int endInd) {
		double diff = 0;
		while (startInd > -1 && endInd < grid.size()) {
			diff += countDifferentChars(grid.get(startInd).toCharArray(),
					grid.get(endInd).toCharArray());
			startInd--;
			endInd++;
		}
		return diff == 1;
	}

	public double countDifferentChars(char[] s1, char[] s2) {
		double count = 0;
		for (int ind = 0; ind < s1.length; ind++) {
			if (s1[ind] != s2[ind]) {
				count++;
			}
		}
		return count;
	}
}
