package Solutions.aoc24;

import java.util.List;
import Solutions.utils.Coordinate;
import Solutions.utils.Direction;

public class Day4 {

	private char[][] grid;
	private int gridRowCount;
	private int gridColCount;
	private final char[] SEARCH_WORD = { 'X', 'M', 'A', 'S' };

	public Day4(List<String> input) {
		grid = new char[input.size()][];
		this.gridRowCount = input.size();
		this.gridColCount = input.getFirst().length();

		for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
			grid[lineIndex] = input.get(lineIndex).toCharArray();
		}
	}

	public int getResult() {
		int result = 0;

		for (int x = 0; x < this.gridRowCount; x++) {
			for (int y = 0; y < this.gridColCount; y++) {
				for (Direction direction : Direction.values()) {
					Coordinate curCoord = new Coordinate(x, y);
					if (searchInGrid(curCoord, direction, 1)
							|| searchInGrid(curCoord, direction, -1)) {
						result = result + 1;
					}
				}
			}
		}

		// Each word will be counted twice
		return result / 2;
	}

	public int getResultPart2() {
		int result = 0;
		for (int x = 1; x < this.gridRowCount - 1; x++) {
			for (int y = 1; y < this.gridColCount - 1; y++) {
				Coordinate curCoord = new Coordinate(x, y);
				if (grid[x][y] == 'A' && searchInGridPart2(curCoord)) {
					result = result + 1;
				}
			}
		}
		return result;
	}

	private boolean searchInGrid(Coordinate startCoords, Direction coorUpdateDirection, int wordUpdateDirection) {
		// Index for current char in SEACH_WORD
		int startIndex = 0;

		// Max index
		int endIndex = SEARCH_WORD.length;
		if (wordUpdateDirection == -1) {
			startIndex = SEARCH_WORD.length - 1;
			endIndex = -1;
		}

		for (; startIndex < endIndex || startIndex > endIndex; startIndex += wordUpdateDirection) {
			// Check if valid coordinate
			if (startCoords.x < 0 || startCoords.y < 0 || startCoords.x >= this.gridRowCount
					|| startCoords.y >= this.gridColCount) {
				return false;
			}
			// Check if char in word matches in grid
			if (grid[startCoords.x][startCoords.y] != SEARCH_WORD[startIndex]) {
				return false;
			}
			// update coordinate
			startCoords = startCoords.add(coorUpdateDirection.unitVector);
		}
		return true;
	}

	private boolean searchInGridPart2(Coordinate coor) {
		Coordinate NE = coor.add(Direction.NORTH_EAST.unitVector);
		Coordinate SW = coor.add(Direction.SOUTH_WEST.unitVector);

		Coordinate NW = coor.add(Direction.NORTH_WEST.unitVector);
		Coordinate SE = coor.add(Direction.SOUTH_EAST.unitVector);

		boolean flag1 = false;
		if ((grid[NE.x][NE.y] == 'M' && grid[SW.x][SW.y] == 'S')
				|| (grid[NE.x][NE.y] == 'S' && grid[SW.x][SW.y] == 'M')) {
			flag1 = true;
		}

		boolean flag2 = false;
		if ((grid[NW.x][NW.y] == 'M' && grid[SE.x][SE.y] == 'S')
				|| (grid[NW.x][NW.y] == 'S' && grid[SE.x][SE.y] == 'M')) {
			flag2 = true;
		}
		return flag1 & flag2;
	}
}
