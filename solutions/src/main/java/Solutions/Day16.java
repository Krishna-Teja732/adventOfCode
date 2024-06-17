package Solutions;

import java.util.List;
import java.util.LinkedList;

import Solutions.utils.Coordinate;
import Solutions.utils.Pair;

public class Day16 {

	public char[][] grid;
	public char[][] visited;
	private final int MAX_X;
	private final int MAX_Y;

	public Day16(List<char[]> inputs) {
		MAX_X = inputs.size();
		MAX_Y = inputs.getFirst().length;
		grid = new char[MAX_X][MAX_Y];
		visited = new char[MAX_X][MAX_Y];
		for (int r = 0; r < grid.length; r++) {
			System.arraycopy(inputs.get(r), 0, grid[r], 0, grid[r].length);
		}
	}

	public double getResult() {
		dfs(new Coordinate(0, 0), new int[] { 0, 1 });
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

	private void dfs(Coordinate c, int[] direction) {
		if (visited[c.x][c.y] == '#' && getValue(c) == '.') {
			return;
		}
		setVisited(c);
		for (Pair<Coordinate, int[]> neighbor : getNeighbors(c, direction)) {
			if (!isValidCoordinate(neighbor.key)) {
				continue;
			}
			dfs(neighbor.key, neighbor.value);
		}
	}

	private List<Pair<Coordinate, int[]>> getNeighbors(Coordinate c, int[] direction) {
		List<Pair<Coordinate, int[]>> result = new LinkedList<>();

		switch (getValue(c)) {
			case '.' -> {
				result.add(new Pair<>(c.add(direction[0], direction[1]), direction));
			}
			case '/' -> {
				reflect(direction, -1);
				result.add(new Pair<>(c.add(direction[0], direction[1]), direction));
			}
			case '\\' -> {
				reflect(direction, 1);
				result.add(new Pair<>(c.add(direction[0], direction[1]), direction));
			}
			case '|' -> {
				if (direction[1] != 0) {
					result.add(new Pair<>(c.add(1, 0), new int[] { 1, 0 }));
					result.add(new Pair<>(c.add(-1, 0), new int[] { -1, 0 }));
				} else {
					result.add(new Pair<>(c.add(direction[0], direction[1]), direction));
				}
			}
			case '-' -> {
				if (direction[0] != 0) {
					result.add(new Pair<>(c.add(0, 1), new int[] { 0, 1 }));
					result.add(new Pair<>(c.add(0, -1), new int[] { 0, -1 }));
				} else {
					result.add(new Pair<>(c.add(direction[0], direction[1]), direction));
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

	private void reflect(int[] direction, int magnitude) {
		int temp = direction[0];
		direction[0] = direction[1] * magnitude;
		direction[1] = temp * magnitude;
	}

}
