package Solutions.aoc24;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import Solutions.utils.FormatInputHelper;

public class Day5 {

	private HashMap<Integer, HashSet<Integer>> updateOrder;
	private ArrayList<HashMap<Integer, Integer>> updates;

	public Day5(List<String> input) {
		updateOrder = new HashMap<>();
		int index = 0;
		for (; index < input.size(); index++) {
			if (input.get(index).equals("")) {
				index++;
				break;
			}
			List<Integer> entry = FormatInputHelper.extractNumbersFromString(input.get(index));
			if (!updateOrder.containsKey(entry.getFirst())) {
				updateOrder.put(entry.getFirst(), new HashSet<>());
			}
			updateOrder.get(entry.getFirst()).add(entry.getLast());
		}

		updates = new ArrayList<>();
		for (; index < input.size(); index++) {
			int updateIndex = 0;
			HashMap<Integer, Integer> update = new HashMap<>();
			for (int num : FormatInputHelper.extractNumbersFromString(input.get(index))) {
				update.put(num, updateIndex);
				updateIndex++;
			}
			updates.add(update);
		}
	}

	public int getResult() {
		int result = 0;
		for (HashMap<Integer, Integer> update : updates) {
			int updateRes = isValidUpdate(update);
			if (updateRes != -1) {
				result = result + updateRes;
			}
		}
		return result;
	}

	private int isValidUpdate(HashMap<Integer, Integer> update) {
		int midIndex = update.size() / 2;
		int midElement = -1;
		for (int updateKey : update.keySet()) {
			int updateKeyIndex = update.get(updateKey);
			if (updateKeyIndex == midIndex) {
				midElement = updateKey;
			}
			for (Integer prereq : this.updateOrder.getOrDefault(updateKey, new HashSet<>())) {
				if (updateKeyIndex > update.getOrDefault(prereq, updateKeyIndex + 1)) {
					return -1;
				}
			}
		}
		return midElement;
	}

	private int makeValidUpdate(HashMap<Integer, Integer> update) {
		int midIndex = update.size() / 2;
		HashMap<Integer, Integer> keyCount = new HashMap<>();
		for (int updateKey : update.keySet()) {
			for (Integer prereq : this.updateOrder.getOrDefault(updateKey, new HashSet<>())) {
				if (!update.containsKey(prereq)) {
					continue;
				}
				keyCount.put(updateKey, keyCount.getOrDefault(updateKey, 0) + 1);
			}
		}

		for (int key : keyCount.keySet()) {
			if (keyCount.get(key) == midIndex) {
				return key;
			}
		}
		return -1;
	}

	public int getResultPart2() {
		int result = 0;
		for (HashMap<Integer, Integer> update : updates) {
			int updateRes = isValidUpdate(update);
			if (updateRes == -1) {
				result = result + makeValidUpdate(update);
			}
		}
		return result;
	}
}
