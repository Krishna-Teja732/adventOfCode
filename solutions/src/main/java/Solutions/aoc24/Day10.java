package Solutions.aoc24;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;

import Solutions.utils.Coordinate;
import Solutions.utils.Direction;

public class Day10 {

	private int[][] grid;
	private int gridRowLength;
	private int gridColLength;
	private ArrayList<Coordinate> startPositions;
	private Set<Direction> validDirections;
	private int validTrailsCount;

	public Day10(List<String> inputs) {
		this.gridRowLength = inputs.size();
		this.gridColLength = inputs.getFirst().length();
		this.grid = new int[this.gridRowLength][gridColLength];
		this.startPositions = new ArrayList<>();
		this.validDirections = EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
		this.validTrailsCount = 0;

		for (int lineIndex = 0; lineIndex < gridRowLength; lineIndex++) {
			char[] line = inputs.get(lineIndex).toCharArray();
			for (int charIndex = 0; charIndex < gridColLength; charIndex++) {
				grid[lineIndex][charIndex] = line[charIndex] - '0';
				if (line[charIndex] == '0') {
					startPositions.add(new Coordinate(lineIndex, charIndex));
				}
			}
		}
	}

	public long getResult() {
		for (Coordinate startPosition : startPositions) {
			HashSet<Coordinate> visitedTrailEnds = new HashSet<>();
			findTrails(startPosition, visitedTrailEnds);
		}
		return validTrailsCount;
	}

	public void findTrails(Coordinate currentPosition, HashSet<Coordinate> visitedTrailEnds) {
		if (grid[currentPosition.x][currentPosition.y] == 9) {
			if (!visitedTrailEnds.contains(currentPosition)) {
				visitedTrailEnds.add(currentPosition);
				this.validTrailsCount++;
			}
			return;
		}
		for (Direction direction : this.validDirections) {
			Coordinate nextCoordinate = currentPosition.add(direction.unitVector);
			if (!isValidCoordinate(nextCoordinate)
					|| grid[nextCoordinate.x][nextCoordinate.y] != grid[currentPosition.x][currentPosition.y]
							+ 1) {
				continue;
			}
			findTrails(nextCoordinate, visitedTrailEnds);
		}
	}

	public boolean isValidCoordinate(Coordinate coord) {
		if (coord.x < 0 || coord.y < 0 || coord.x >= gridRowLength || coord.y >= gridColLength) {
			return false;
		}
		return true;
	}

	public long getResultPart2() {
		for (Coordinate startPosition : startPositions) {
			findTrails(startPosition);
		}
		return validTrailsCount;
	}

	public void findTrails(Coordinate currentPosition) {
		if (grid[currentPosition.x][currentPosition.y] == 9) {
			this.validTrailsCount++;
			return;
		}
		for (Direction direction : this.validDirections) {
			Coordinate nextCoordinate = currentPosition.add(direction.unitVector);
			if (!isValidCoordinate(nextCoordinate)
					|| grid[nextCoordinate.x][nextCoordinate.y] != grid[currentPosition.x][currentPosition.y]
							+ 1) {
				continue;
			}
			findTrails(nextCoordinate);
		}
	}
}
