package Solutions.aoc24;

import Solutions.utils.Coordinate;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day8 {

	private HashMap<Character, ArrayList<Coordinate>> antennas;
	private int gridRowLen;
	private int gridColLen;

	public Day8(List<String> input) {
		this.antennas = new HashMap<>();
		this.gridRowLen = input.size();
		this.gridColLen = input.getFirst().length();

		for (int lineInd = 0; lineInd < this.gridRowLen; lineInd++) {
			char[] line = input.get(lineInd).toCharArray();
			for (int charInd = 0; charInd < this.gridColLen; charInd++) {
				if (line[charInd] == '.') {
					continue;
				}
				if (!antennas.containsKey(line[charInd])) {
					antennas.put(line[charInd], new ArrayList<>());
				}
				antennas.get(line[charInd]).add(new Coordinate(lineInd, charInd));
			}
		}
	}

	public int getResult() {
		HashSet<Coordinate> antiNodes = new HashSet<>();

		for (char antennaType : this.antennas.keySet()) {
			getAnitNodes(this.antennas.get(antennaType), antiNodes);
		}

		return antiNodes.size();
	}

	private boolean isValidCoordinate(Coordinate coord) {
		if (coord.x < 0 || coord.y < 0 || coord.x >= this.gridRowLen || coord.y >= this.gridColLen) {
			return false;
		}
		return true;
	}

	private void getAnitNodes(ArrayList<Coordinate> antenas, HashSet<Coordinate> antiNodes) {
		for (Coordinate c1 : antenas) {
			for (Coordinate c2 : antenas) {
				if (c1 == c2) {
					continue;
				}
				Coordinate node = new Coordinate(2 * c1.x - c2.x, 2 * c1.y - c2.y);
				if (isValidCoordinate(node)) {
					antiNodes.add(node);
				}
			}
		}
	}

	public int getResultPart2() {
		HashSet<Coordinate> antiNodes = new HashSet<>();

		for (char antennaType : this.antennas.keySet()) {
			getAnitNodesV2(this.antennas.get(antennaType), antiNodes);
		}

		return antiNodes.size();
	}

	private void getAnitNodesV2(ArrayList<Coordinate> antenas, HashSet<Coordinate> antiNodes) {
		for (Coordinate c1 : antenas) {
			for (Coordinate c2 : antenas) {
				if (c1 == c2) {
					continue;
				}
				Coordinate delta = new Coordinate(c1.x - c2.x, c1.y - c2.y);
				Coordinate node = new Coordinate(c1.x, c1.y);
				while (isValidCoordinate(node)) {
					antiNodes.add(node);
					node = node.add(delta);
				}
			}
		}
	}

}
