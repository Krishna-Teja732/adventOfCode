package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Solutions.utils.Coordinate;

class Day11 {

	List<Coordinate> galaxiesCoordinates;
	List<Double> rowCorrection;
	List<Double> colCorrection;

	public Day11(List<String> inputs) {
		rowCorrection = new ArrayList<>(Arrays.asList(0.0));
		colCorrection = new ArrayList<>(Arrays.asList(0.0));
		this.galaxiesCoordinates = new ArrayList<>();
		formatInput(inputs);
	}

	private void formatInput(List<String> inputs) {
		boolean[] rowHasGalaxy = new boolean[inputs.size()];
		boolean[] colHasGalaxy = new boolean[inputs.getFirst().length()];

		for (int x = 0; x < inputs.size(); x++) {
			char[] line = inputs.get(x).toCharArray();
			for (int y = 0; y < line.length; y++) {
				if (line[y] == '#') {
					rowHasGalaxy[x] = true;
					colHasGalaxy[y] = true;
					galaxiesCoordinates.add(new Coordinate(x, y));
				}
			}
		}

		for (boolean hasGalaxy : rowHasGalaxy) {
			if (!hasGalaxy) {
				rowCorrection.add(rowCorrection.getLast() + 999999);
			} else {
				rowCorrection.add(rowCorrection.getLast());
			}
		}

		for (boolean hasGalaxy : colHasGalaxy) {
			if (!hasGalaxy) {
				colCorrection.add(colCorrection.getLast() + 999999);
			} else {
				colCorrection.add(colCorrection.getLast());
			}
		}
	}

	public double getSumOfDistances() {
		double result = 0;
		for (int ind1 = 0; ind1 < galaxiesCoordinates.size() - 1; ind1++) {
			for (int ind2 = ind1 + 1; ind2 < galaxiesCoordinates.size(); ind2++) {
				Coordinate g1 = galaxiesCoordinates.get(ind1);
				Coordinate g2 = galaxiesCoordinates.get(ind2);
				result += g1.getManhattanDistance(g2) + distanceCorrection(g1, g2);
			}
		}
		return result;
	}

	private Double distanceCorrection(Coordinate c1, Coordinate c2) {
		int minX = Math.min(c1.x, c2.x) + 1;
		int minY = Math.min(c1.y, c2.y) + 1;

		int maxX = Math.max(c1.x, c2.x) + 1;
		int maxY = Math.max(c1.y, c2.y) + 1;

		return rowCorrection.get(maxX) - rowCorrection.get(minX - 1) + colCorrection.get(maxY)
				- colCorrection.get(minY - 1);
	}
}
