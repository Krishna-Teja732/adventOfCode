package Solutions;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Solutions.utils.Pair;

public class Day15Part2 {

	private List<String> lines;
	private int[] asciiMap;
	private HashMap<String, Pair<Lens, Integer>> lenses;
	private List<LinkedList<Lens>> boxes;

	public Day15Part2(String input) {
		lines = Arrays.stream(input.split(",")).toList();
		asciiMap = new int[256];
		boxes = new ArrayList<>();
		lenses = new HashMap<>();

		for (int index = 0; index < asciiMap.length; index++) {
			asciiMap[index] = (index * 17) % 256;
			boxes.add(new LinkedList<>());
		}
	}

	public double getResult() {
		double result = 0;

		arrangeLenses();

		for (int boxNum = 0; boxNum < boxes.size(); boxNum++) {
			int lensNum = 1;
			for (Lens lens : boxes.get(boxNum)) {
				result = result + (boxNum + 1) * lensNum * lens.focalLen;
				lensNum++;
			}
		}

		return result;
	}

	private void arrangeLenses() {
		String label;
		int focalLen;
		for (String line : lines) {
			switch (line.charAt(line.length() - 1)) {
				case '-':
					label = line.substring(0, line.length() - 1);
					deleteLens(label);
					break;
				default:
					label = line.substring(0, line.length() - 2);
					focalLen = line.charAt(line.length() - 1) - '0';
					addLens(label, focalLen);
					break;
			}
		}

	}

	private void deleteLens(String label) {
		Pair<Lens, Integer> record = lenses.get(label);
		if (record != null) {
			boxes.get(record.value).remove(record.key);
			lenses.remove(label);
		}
	}

	private void addLens(String label, int focalLen) {
		Pair<Lens, Integer> record = lenses.get(label);
		if (record == null) {
			int boxIndex = getHash(
					label.toCharArray());
			Lens lens = new Lens(label, focalLen);
			boxes.get(boxIndex).add(lens);
			lenses.put(label, new Pair<>(lens, boxIndex));

		} else {
			record.key.focalLen = focalLen;
		}
	}

	private int getHash(char[] line) {
		int hash = 0;
		for (char elem : line) {
			hash = (asciiMap[hash] + asciiMap[elem]) % 256;
		}
		return hash;
	}

	private class Lens {
		String label;
		Integer focalLen;

		public Lens(String label, int focalLen) {
			this.label = label;
			this.focalLen = focalLen;
		}

		public String toString() {
			return label + " " + focalLen;
		}
	}

}
