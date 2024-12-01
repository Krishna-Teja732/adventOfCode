package Solutions.aoc23;

import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;

import Solutions.utils.Coordinate;
import Solutions.utils.Pair;

public class Day16 {

	private char[][] grid;
	private char[][] visited;
	private final int MAX_X;
	private final int MAX_Y;
	private HashSet<Pair<Coordinate, Direction>> beams;

	public Day16(List<String> inputs) {
		MAX_X = inputs.size();
		MAX_Y = inputs.getFirst().length();
		grid = new char[MAX_X][MAX_Y];
		visited = new char[MAX_X][MAX_Y];
		beams = new HashSet<>();
		for (int r = 0; r < grid.length; r++) {
			System.arraycopy(inputs.get(r).toCharArray(), 0, grid[r], 0, grid[r].length);
		}
	}

	public double getResult() {
		dfs(new Coordinate(0, 0), Direction.EAST);
		return countVisited();
	}

	public double getPart2Result() {
		double result = 0;
		Coordinate init = new Coordinate(0, 0);

		for (int r = 0; r < MAX_X; r++) {
			beams.clear();
			visited = new char[MAX_X][MAX_Y];
			dfs(init.set(r, 0), Direction.EAST);
			result = Math.max(result, countVisited());
			beams.clear();
			visited = new char[MAX_X][MAX_Y];
			dfs(init.set(r, MAX_Y - 1), Direction.WEST);
			result = Math.max(result, countVisited());
		}

		for (int c = 0; c < MAX_Y; c++) {
			beams.clear();
			visited = new char[MAX_X][MAX_Y];
			dfs(init.set(0, c), Direction.SOUTH);
			result = Math.max(result, countVisited());
			beams.clear();
			visited = new char[MAX_X][MAX_Y];
			dfs(init.set(MAX_X - 1, c), Direction.NORTH);
			result = Math.max(result, countVisited());
		}

		return result;
	}

	private double countVisited() {
		double result = 0;
		for (char[] row : visited) {
			for (char ch : row) {
				if (ch == '#') {
					result++;
				}
			}
		}
		return result;
	}

	private void dfs(Coordinate c, Direction d) {
		setVisited(c);
		for (Pair<Coordinate, Direction> neighbor : getNeighbors(c, d)) {
			if (!isValidCoordinate(neighbor.key)) {
				return;
			}
			if (getValue(c) == '-' || getValue(c) == '|') {
				if (beams.contains(neighbor)) {
					continue;
				}
				beams.add(neighbor);
			}
			dfs(neighbor.key, neighbor.value);
		}
	}

	private List<Pair<Coordinate, Direction>> getNeighbors(Coordinate c, Direction d) {
		List<Pair<Coordinate, Direction>> result = new LinkedList<>();

		switch (getValue(c)) {
			case '.' -> {
				result.add(new Pair<>(c.add(d.vector[0], d.vector[1]), d));
			}
			case '/' -> {
				d = reflect(d.vector, -1);
				result.add(new Pair<>(c.add(d.vector[0], d.vector[1]), d));
			}
			case '\\' -> {
				d = reflect(d.vector, 1);
				result.add(new Pair<>(c.add(d.vector[0], d.vector[1]), d));
			}
			case '|' -> {
				if (d.vector[1] != 0) {
					result.add(new Pair<>(c.add(1, 0), Direction.SOUTH));
					result.add(new Pair<>(c.add(-1, 0), Direction.NORTH));
				} else {
					result.add(new Pair<>(c.add(d.vector[0], d.vector[1]), d));
				}
			}
			case '-' -> {
				if (d.vector[0] != 0) {
					result.add(new Pair<>(c.add(0, 1), Direction.EAST));
					result.add(new Pair<>(c.add(0, -1), Direction.WEST));
				} else {
					result.add(new Pair<>(c.add(d.vector[0], d.vector[1]), d));
				}
			}
		}
		return result;
	}

	private boolean isValidCoordinate(Coordinate c) {
		if (c.x < 0 || c.x >= MAX_X || c.y < 0 || c.y >= MAX_Y) {
			return false;
		}
		return true;
	}

	private char getValue(Coordinate c) {
		return grid[c.x][c.y];
	}

	private void setVisited(Coordinate c) {
		visited[c.x][c.y] = '#';
	}

	private Direction reflect(int[] directionVector, int magnitude) {
		int[] newDirectionVector = new int[2];
		newDirectionVector[0] = directionVector[1] * magnitude;
		newDirectionVector[1] = directionVector[0] * magnitude;
		return Direction.getDirectionFromVector(newDirectionVector);
	}

	public enum Direction {
		NORTH(new int[] { -1, 0 }),
		EAST(new int[] { 0, 1 }),
		SOUTH(new int[] { 1, 0 }),
		WEST(new int[] { 0, -1 });

		public final int[] vector;

		private Direction(int[] vector) {
			this.vector = vector;
		}

		public static Direction getDirectionFromVector(int[] vector) {
			if (vector[0] == 0) {
				if (vector[1] > 0) {
					return EAST;
				}
				return WEST;
			}
			if (vector[1] == 0) {
				if (vector[0] > 0) {
					return SOUTH;
				}
				return NORTH;
			}
			return null;
		}
	}

}
