package Solutions.aoc24;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Day1 {

	List<String> inputs;

	public Day1(List<String> inputs) {
		this.inputs = inputs;
	}

	// This function returns the wrong result
	public double getResultV2() throws Exception {
		long sum1 = 0;
		long sum2 = 0;

		for (String line : this.inputs) {
			int splitIndex = line.indexOf(" ");
			long val1 = (Long.parseUnsignedLong(line.substring(0, splitIndex).strip()));
			long val2 = (Long.parseUnsignedLong(line.substring(splitIndex + 1).strip()));

			sum1 = sum1 + val1;
			sum2 = sum2 + val2;
		}
		return Math.abs(sum1 - sum2);
	}

	public double getResult() {
		double result = 0;

		PriorityQueue<Integer> arr1 = new PriorityQueue<>((num1, num2) -> num1 - num2);
		PriorityQueue<Integer> arr2 = new PriorityQueue<>((num1, num2) -> num1 - num2);
		for (String line : this.inputs) {
			int splitIndex = line.indexOf(" ");
			arr1.add(Integer.parseInt(line.substring(0, splitIndex).strip()));
			arr2.add(Integer.parseInt(line.substring(splitIndex + 1).strip()));
		}

		while (!arr1.isEmpty()) {
			result += Math.abs(arr1.remove() - arr2.remove());
		}

		return result;
	}

	public double getResultPart2() {
		double result = 0;

		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		for (String line : this.inputs) {
			int splitIndex = line.indexOf(" ");
			int key1 = Integer.parseInt(line.substring(0, splitIndex).strip());
			int key2 = Integer.parseInt(line.substring(splitIndex + 1).strip());

			map1.put(key1, map1.getOrDefault(key1, 0) + 1);
			map2.put(key2, map2.getOrDefault(key2, 0) + 1);
		}

		for (int key : map1.keySet()) {
			result += key * map2.getOrDefault(key, 0) * map1.get(key);
		}
		return result;
	}

}
