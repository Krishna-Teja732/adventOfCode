package Solutions.aoc24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Solutions.utils.Coordinate;
import Solutions.utils.FormatInputHelper;

public class Day14 {

	private ArrayList<Coordinate[]> robots;
	private int gridRowLength = 103;
	private int gridColLength = 101;

	public Day14(List<String> inputs) {
		robots = new ArrayList<>();

		for (String line : inputs) {
			List<Long> position = FormatInputHelper.extractNumbersFromStringALongs(line);
			Coordinate[] robot = new Coordinate[2];
			robot[0] = new Coordinate(position.get(1).intValue(), position.get(0).intValue());
			robot[1] = new Coordinate(position.get(3).intValue(), position.get(2).intValue());
			robots.add(robot);
		}
	}

	public long getResult() {
		long[] quad = new long[] { 0, 0, 0, 0 };
		int iter = 100;
		for (Coordinate[] robot : robots) {
			int quadIndex = findFinalCoordinate(robot[0], robot[1], iter);
			if (quadIndex == -1) {
				continue;
			}
			quad[quadIndex]++;
		}
		long result = quad[0];
		for (int index = 1; index < 4; index++) {
			result = result * quad[index];
		}
		return result;
	}

	public int findFinalCoordinate(Coordinate startCoordinate, Coordinate jumpDirection, int iter) {
		int newX = (startCoordinate.x + jumpDirection.x * iter) % gridRowLength;
		int newY = (startCoordinate.y + jumpDirection.y * iter) % gridColLength;
		newX = newX < 0 ? gridRowLength - (Math.abs(newX) % gridRowLength) : newX % gridRowLength;
		newY = newY < 0 ? gridColLength - (Math.abs(newY) % gridColLength) : newY % gridColLength;

		// find the quadrant
		if (newX < gridRowLength / 2) {
			if (newY > gridColLength / 2) {
				return 0;
			} else if (newY < gridColLength / 2) {
				return 3;
			}

		} else if (newX > gridRowLength / 2) {
			if (newY > gridColLength / 2) {
				return 1;
			} else if (newY < gridColLength / 2) {
				return 2;
			}
		}

		return -1;
	}

}
