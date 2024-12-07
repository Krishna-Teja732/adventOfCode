package Solutions.aoc24;

import Solutions.utils.Coordinate;

import java.util.List;

public class Day6 {

	private char[][] grid;
	private Coordinate startPosition;
	private int gridRowLen;
	private int gridColLen;

	public Day6(List<String> input) {
		this.grid = new char[input.size()][];
		this.gridRowLen = input.size();
		this.gridColLen = input.getFirst().length();

		for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
			this.grid[lineIndex] = input.get(lineIndex).toCharArray();
			int charIndex = input.get(lineIndex).indexOf('^');
			if (charIndex != -1) {
				startPosition = new Coordinate(lineIndex, charIndex);
			}
		}
	}

	public int getResult() {
		int result = 0;
		traverseGrid(this.startPosition, new Coordinate(-1, 0));
		for (int x = 0; x < this.gridRowLen; x++) {
			for (int y = 0; y < this.gridColLen; y++) {
				if (this.grid[x][y] == 'x') {
					result++;
				}
			}
		}
		return result;
	}

	public int getResultPart2() {
		return -1;
	}

	// Mark all visited coordinates
	// If path is a loop return true, flase otherwise
	private boolean traverseGrid(Coordinate pos, Coordinate direction) {
		Coordinate startCoordinate = new Coordinate(pos);
		while (isValidCoordinate(pos)) {
			// Mark position as visited
			this.grid[pos.x][pos.y] = 'x';

			// Look ahead one position to get the direction to move
			Coordinate newDirection = udpateDirection(pos.add(direction), direction);

			// Move only if the direction remains unchanged
			// This condition prevents us from collinding with #
			if (newDirection == direction) {
				pos = pos.add(direction);
			}

			// Loop present
			if (startCoordinate.equals(pos) && newDirection.equals(direction)) {
				break;
			}

			direction = newDirection;
		}

		return startCoordinate.equals(pos);
	}

	private Coordinate udpateDirection(Coordinate coord, Coordinate curDirection) {
		// If path is clear, continue in same direction
		if (!isValidCoordinate(coord) || this.grid[coord.x][coord.y] != '#') {
			return curDirection;
		}

		// path is blocked, turn 90 deg right
		int temp = -1 * curDirection.x;
		curDirection.x = curDirection.y;
		curDirection.y = temp;
		return curDirection;
	}

	private boolean isValidCoordinate(Coordinate coord) {
		if (coord.x < 0 || coord.x >= this.gridRowLen || coord.y < 0 || coord.y >= this.gridColLen) {
			return false;
		}
		return true;
	}
}
