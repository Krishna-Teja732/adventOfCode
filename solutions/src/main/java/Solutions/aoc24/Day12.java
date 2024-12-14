package Solutions.aoc24;

import java.util.Set;
import java.util.List;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;

import Solutions.utils.Coordinate;
import Solutions.utils.Direction;

public class Day12 {

	private char[][] grid;
	private boolean[][] visited;
	private int gridRowLength;
	private int gridColLength;
	private Set<Direction> validDirections;

	public Day12(List<String> inputs) {
		gridRowLength = inputs.size();
		gridColLength = inputs.getFirst().length();
		grid = new char[gridRowLength][gridColLength];
		visited = new boolean[gridRowLength][gridColLength];
		validDirections = EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

		for (int index = 0; index < gridRowLength; index++) {
			grid[index] = inputs.get(index).toCharArray();
		}
	}

	public long getResult() {
		int result = 0;

		for (int x = 0; x < gridRowLength; x++) {
			for (int y = 0; y < gridColLength; y++) {
				if (visited[x][y]) {
					continue;
				}
				result += getFencePrice(new Coordinate(x, y));
			}
		}

		return result;
	}

	private long getFencePrice(Coordinate startCoordinate) {
		LinkedList<Coordinate> frontier = new LinkedList<>();
		frontier.add(startCoordinate);
		long perimeter = 0;
		long boxCount = 0;

		while (!frontier.isEmpty()) {
			Coordinate coord = frontier.pollFirst();
			if (visited[coord.x][coord.y]) {
				continue;
			}

			visited[coord.x][coord.y] = true;
			boxCount++;
			for (Direction direction : validDirections) {
				Coordinate nextCoord = coord.add(direction.unitVector);
				if (!isValidCoordinate(nextCoord)
						|| grid[coord.x][coord.y] != grid[nextCoord.x][nextCoord.y]) {
					perimeter++;
					continue;
				}
				if (visited[nextCoord.x][nextCoord.y]) {
					continue;
				}
				frontier.add(nextCoord);
			}
		}

		return perimeter * boxCount;
	}

	private boolean isValidCoordinate(Coordinate c) {
		if (c.x < 0 || c.y < 0 || c.x >= gridRowLength || c.y >= gridColLength) {
			return false;
		}
		return true;
	}

	public long getResultPart2() {
		int result = 0;

		for (int x = 0; x < gridRowLength; x++) {
			for (int y = 0; y < gridColLength; y++) {
				if (visited[x][y]) {
					continue;
				}
				result += getFencePriceV2(new Coordinate(x, y));
			}
		}

		return result;

	}

	private long getFencePriceV2(Coordinate startCoordinate) {
		LinkedList<Coordinate> frontier = new LinkedList<>();
		frontier.add(startCoordinate);
		long boxCount = 0;
		HashMap<Direction, LinkedList<Coordinate>> borderPoints = new HashMap<>();

		for (Direction direction : validDirections) {
			borderPoints.put(direction, new LinkedList<>());
		}

		while (!frontier.isEmpty()) {
			Coordinate coord = frontier.pollFirst();
			if (visited[coord.x][coord.y]) {
				continue;
			}

			visited[coord.x][coord.y] = true;
			boxCount++;
			for (Direction direction : validDirections) {
				Coordinate nextCoord = coord.add(direction.unitVector);
				if (!isValidCoordinate(nextCoord)
						|| grid[coord.x][coord.y] != grid[nextCoord.x][nextCoord.y]) {
					borderPoints.get(direction).add(coord);
					continue;
				}
				if (visited[nextCoord.x][nextCoord.y]) {
					continue;
				}
				frontier.add(nextCoord);
			}
		}

		int sideCount = 0;
		for (Direction direction : validDirections) {
			sideCount = sideCount + findPerimeter(direction, borderPoints.get(direction));
		}

		return sideCount * boxCount;
	}

	private int findPerimeter(Direction borderDirection, LinkedList<Coordinate> borderPoints) {
		int sideCount = 0;
		Direction[] exploreDirections = new Direction[] { Direction.NORTH, Direction.SOUTH };
		if (borderDirection == Direction.NORTH || borderDirection == Direction.SOUTH) {
			exploreDirections = new Direction[] { Direction.EAST, Direction.WEST };
		}

		while (!borderPoints.isEmpty()) {
			Coordinate startCoordinate = borderPoints.peek();
			for (Direction direction : exploreDirections) {
				Coordinate coord = new Coordinate(startCoordinate);
				do {
					borderPoints.remove(coord);
					coord = coord.add(direction.unitVector);
				} while (isValidCoordinate(coord)
						&& borderPoints.contains(coord));
			}
			sideCount++;
		}
		return sideCount;
	}
}
