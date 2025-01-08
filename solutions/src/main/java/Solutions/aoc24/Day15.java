package Solutions.aoc24;

import java.util.ArrayList;
import java.util.List;

import Solutions.utils.Coordinate;
import Solutions.utils.Direction;

public class Day15 {

	private char[][] grid;
	private int gridRowLength;
	private int gridColLength;

	private List<Direction> moves;
	private Coordinate startCoord;

	public Day15(List<String> inputs) {
		this.gridRowLength = 0;
		this.gridColLength = inputs.getFirst().length();
		while (!inputs.get(this.gridRowLength).isBlank()) {
			this.gridRowLength++;
		}

		this.grid = new char[gridRowLength][gridColLength];

		for (int index = 0; index < this.gridRowLength; index++) {
			this.grid[index] = inputs.get(index).toCharArray();

			if (inputs.get(index).indexOf('@') != -1) {
				startCoord = new Coordinate(index, inputs.get(index).indexOf('@'));
			}
		}

		moves = new ArrayList<>();
		for (int index = this.gridRowLength + 1; index < inputs.size(); index++) {
			for (char move : inputs.get(index).toCharArray()) {
				switch (move) {
					case 'v' -> moves.add(Direction.SOUTH);
					case '^' -> moves.add(Direction.NORTH);
					case '>' -> moves.add(Direction.EAST);
					case '<' -> moves.add(Direction.WEST);
				}
			}
		}
	}

	public long getResult() {
		long result = 0;
		simulatMoves();
		for (int rowInd = 0; rowInd < this.gridRowLength; rowInd++) {
			for (int colInd = 0; colInd < this.gridColLength; colInd++) {
				System.out.print(grid[rowInd][colInd]);
				if (grid[rowInd][colInd] != 'O') {
					continue;
				}
				result = result + 100 * rowInd + colInd;
			}
			System.out.println();
		}

		return result;
	}

	private void simulatMoves() {
		Coordinate curPos = startCoord;
		for (Direction move : moves) {
			// Check if the adjacent position is a wall
			Coordinate nextPos = curPos.add(move.unitVector);
			if (grid[nextPos.x][nextPos.y] == '#') {
				continue;
			}

			// Check if the boxes can be moved
			Coordinate lookAhead = curPos.add(move.unitVector);
			while (grid[lookAhead.x][lookAhead.y] == 'O') {
				lookAhead = lookAhead.add(move.unitVector);
			}
			// Boxes cannot be moved, do noting
			if (grid[lookAhead.x][lookAhead.y] == '#') {
				continue;
			}

			// Move robot to next pos
			grid[curPos.x][curPos.y] = '.';
			grid[nextPos.x][nextPos.y] = '@';
			curPos = nextPos;
			if (!lookAhead.equals(nextPos)) {
				grid[lookAhead.x][lookAhead.y] = 'O';
			}
		}
	}
}
