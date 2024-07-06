package Solutions;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Day14 {

	private char[][] grid;

	public Day14(List<String> inputs) {
		formatInput(inputs);
	}

	private void formatInput(List<String> inputs) {
		grid = new char[inputs.size()][];
		for (int rInd = 0; rInd < grid.length; rInd++) {
			grid[rInd] = inputs.get(rInd).toCharArray();
		}
	}

	public double getResult() {
		performTilt();
		return getLoad();
	}

	public double getResultPart2() {
		for (int iter = 0; iter < 1000000000; iter++) {
			performTilt();
			grid = rotateGridRight();
		}
		printGrid();
		return getLoad();
	}

	private double getLoad() {
		double load = 0;
		for (int colIndex = 0; colIndex < grid.length; colIndex++) {
			for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
				char curChar = getCharFromGrid(rowIndex, colIndex);
				if (curChar == 'O') {
					load += grid.length - rowIndex;
				}
			}
		}
		return load;
	}

	private void performTilt() {
		for (int colIndex = 0; colIndex < grid.length; colIndex++) {
			int finalRockIndex = 0;
			for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
				char curChar = getCharFromGrid(rowIndex, colIndex);
				if (curChar == 'O') {
					setCharInGrid(rowIndex, colIndex, '.');
					setCharInGrid(finalRockIndex, colIndex, 'O');
					finalRockIndex++;
				} else if (curChar == '#') {
					finalRockIndex = rowIndex + 1;
				}
			}
		}
	}

	private char[][] rotateGridRight() {
		char[][] res = new char[grid.length][grid.length];
		for (int colIndex = 0; colIndex < grid.length; colIndex++) {
			for (int rowIndex = grid.length - 1; rowIndex > -1; rowIndex--) {
				res[colIndex][grid.length - 1 - rowIndex] = grid[rowIndex][colIndex];
			}
		}
		return res;
	}

	private char getCharFromGrid(int rowIndex, int colIndex) {
		return grid[rowIndex][colIndex];
	}

	private char setCharInGrid(int rowIndex, int colIndex, char val) {
		char result = getCharFromGrid(rowIndex, colIndex);
		grid[rowIndex][colIndex] = val;
		return result;
	}

	private void printGrid() {
		for (char[] row : grid) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}
}
