package Solutions.aoc24;

import Solutions.utils.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
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

	// Mark all visited coordinates
	// If path is a loop return true, flase otherwise
	private void traverseGrid(Coordinate pos, Coordinate direction) {
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

			direction = newDirection;
		}
	}

	public int getResultPart2() {
		int result = 0;
		for (int x = 0; x < this.gridRowLen; x++) {
			for (int y = 0; y < this.gridColLen; y++) {
				if (this.grid[x][y] == '#'
						|| (this.startPosition.x == x && this.startPosition.y == y)) {
					continue;
				}

				this.grid[x][y] = '#';
				if (hasLoop()) {
					result++;
				}
				this.grid[x][y] = '.';
			}
		}
		return result;
	}

	private boolean hasLoop() {
		Coordinate pos = new Coordinate(this.startPosition);
		Coordinate direction = new Coordinate(-1, 0);
		HashMap<Coordinate, HashSet<Coordinate>> posDirMap = new HashMap<>();

		while (isValidCoordinate(pos)) {
			if (posDirMap.containsKey(pos) && posDirMap.get(pos).contains(direction)) {
				return true;
			}
			if (!posDirMap.containsKey(pos)) {
				posDirMap.put(pos, new HashSet<>());
			}
			posDirMap.get(pos).add(direction);

			// Look ahead one position to get the direction to move
			Coordinate newDirection = udpateDirection(pos.add(direction), direction);

			// Move only if the direction remains unchanged
			// This condition prevents us from collinding with #
			if (newDirection == direction) {
				pos = pos.add(direction);
			}
			direction = newDirection;
		}

		return false;
	}

	private Coordinate udpateDirection(Coordinate coord, Coordinate curDirection) {
		// If path is clear, continue in same direction
		if (!isValidCoordinate(coord) || this.grid[coord.x][coord.y] != '#') {
			return curDirection;
		}

		// path is blocked, turn 90 deg right
		return new Coordinate(curDirection.y, curDirection.x * -1);
	}

	private boolean isValidCoordinate(Coordinate coord) {
		if (coord.x < 0 || coord.x >= this.gridRowLen || coord.y < 0 || coord.y >= this.gridColLen) {
			return false;
		}
		return true;
	}
}
