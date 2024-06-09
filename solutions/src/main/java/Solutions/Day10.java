package Solutions;

import java.util.List;
import java.util.ArrayList;

class Day10 {

	private List<List<Character>> grid;
	private int startX;
	private int startY;
	private int maxSteps;

	public Day10(List<String> inputs) {
		maxSteps = 0;
		formatInput(inputs);
	}

	private void formatInput(List<String> inputs) {
		grid = new ArrayList<>();

		for (int index = 0; index < inputs.size(); index++) {
			List<Character> gridRow = new ArrayList<>();
			char[] line = inputs.get(index).toCharArray();
			for (int charInd = 0; charInd < line.length; charInd++) {
				if (line[charInd] == 'S') {
					startX = index;
					startY = charInd;
				}
				gridRow.add(line[charInd]);
			}
			grid.add(gridRow);
		}
	}

	public int getBiggestLoop() {
		dfs(startX, startY, 0);
		return this.maxSteps / 2;
	}

	private void dfs(int x, int y, int steps) {
		if (x == startX && y == startY) {
			maxSteps = Math.max(maxSteps, steps);
			return;
		}

		for (int[] positions : getValidNeighbors(x, y)) {
			dfs(positions[0], positions[1], steps + 1);
		}
	}

	private List<int[]> getValidNeighbors(int x, int y) {
		// #TODO
		return new ArrayList<>();
	}
}
