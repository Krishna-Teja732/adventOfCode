package Solutions.aoc23;

import java.util.List;

import Solutions.utils.Coordinate;
import Solutions.utils.Pair;

class Day10 {

	private char[][] grid;
	private Coordinate startCoordinate;
	private int maxSteps;

	public Day10(List<String> inputs) {
		maxSteps = 0;
		grid = new char[inputs.size()][];
		formatInput(inputs);
	}

	private void formatInput(List<String> inputs) {
		for (int index = 0; index < inputs.size(); index++) {
			char[] line = inputs.get(index).toCharArray();
			grid[index] = new char[line.length];
			for (int charInd = 0; charInd < line.length; charInd++) {
				if (line[charInd] == 'S') {
					startCoordinate = new Coordinate(index, charInd);
				}
				grid[index][charInd] = line[charInd];
			}
		}
	}

	public int getResult() {
		for (Direction direction : Direction.values()) {
			dfs(new Pair<>(this.startCoordinate.add(direction.unitVector), direction), 1);
		}
		return this.maxSteps / 2;
	}

	private void dfs(Pair<Coordinate, Direction> vector, int steps) {
		while (vector != null && isValidCoordinate(vector.key) && !vector.key.equals(this.startCoordinate)) {
			steps++;
			vector = getValidNeighbor(vector.key, vector.value);
		}
		if (vector != null && isValidCoordinate(vector.key) && vector.key.equals(this.startCoordinate)) {
			maxSteps = Math.max(maxSteps, steps);
			return;
		}
	}

	private Pair<Coordinate, Direction> getValidNeighbor(Coordinate c, Direction direction) {
		Direction d = null;
		switch (grid[c.x][c.y]) {
			case '-' -> {
				if (direction.equals(Direction.EAST) || direction.equals(Direction.WEST)) {
					d = direction;
				}
			}
			case '|' -> {
				if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
					d = direction;
				}
			}
			case 'F' -> {
				if (direction.equals(Direction.WEST) || direction.equals(Direction.NORTH)) {
					d = reflect(direction.unitVector, -1);
				}
			}
			case 'L' -> {
				if (direction.equals(Direction.WEST) || direction.equals(Direction.SOUTH)) {
					d = reflect(direction.unitVector, 1);
				}
			}
			case 'J' -> {
				if (direction.equals(Direction.EAST) || direction.equals(Direction.SOUTH)) {
					d = reflect(direction.unitVector, -1);
				}
			}
			case '7' -> {
				if (direction.equals(Direction.EAST) || direction.equals(Direction.NORTH)) {
					d = reflect(direction.unitVector, 1);
				}
			}
		}
		return d == null ? null : new Pair<Coordinate, Day10.Direction>(c.add(d.unitVector), d);
	}

	private boolean isValidCoordinate(Coordinate c) {
		if (c.x >= grid.length || c.x < 0 || c.y >= grid[0].length || c.y < 0) {
			return false;
		}
		if (grid[c.x][c.y] == '.') {
			return false;
		}
		return true;
	}

	private Direction reflect(Coordinate unitVector, int magnitude) {
		Coordinate newUnitVector = new Coordinate(unitVector.y * magnitude, unitVector.x * magnitude);
		return Direction.getDirectionFromVector(newUnitVector);
	}

	public enum Direction {
		NORTH(-1, 0),
		EAST(0, 1),
		SOUTH(1, 0),
		WEST(0, -1);

		public final Coordinate unitVector;
		public final Coordinate zeroVector;

		private Direction(int x, int y) {
			this.unitVector = new Coordinate(x, y);
			this.zeroVector = new Coordinate(0, 0);
		}

		public static Direction getDirectionFromVector(Coordinate vector) {
			if (vector.x == 0) {
				if (vector.y > 0) {
					return EAST;
				}
				return WEST;
			}
			if (vector.y == 0) {
				if (vector.x > 0) {
					return SOUTH;
				}
				return NORTH;
			}
			return null;
		}

	}
}
