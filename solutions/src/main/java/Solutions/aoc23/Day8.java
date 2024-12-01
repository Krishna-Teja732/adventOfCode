package Solutions.aoc23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Day8 {
	private List<Node> startingNodes;
	private char[] path;

	public Day8(List<String> inputs) {
		formatInput(inputs);
	}

	public double findStepsToDestination() {
		double[] counts = new double[startingNodes.size()];
		int index = 0;

		for (Node node : startingNodes) {
			counts[index++] = findStepsToDestination(node);
		}

		double lcm = counts[0];

		for (index = 1; index < startingNodes.size(); index++) {
			lcm = counts[index] * lcm / computeGCD(counts[index], lcm);
		}
		return lcm;
	}

	public double computeGCD(double a, double b) {
		if (b == 0) {
			return a;
		}
		return computeGCD(b, a % b);
	}

	private int findStepsToDestination(Node root) {
		int pathIndex = 0;
		int steps = 0;

		while (!root.isDestination) {
			switch (path[pathIndex]) {
				case 'R' -> root = root.right;
				default -> root = root.left;
			}
			pathIndex = (pathIndex + 1) % path.length;
			steps = steps + 1;
		}

		return steps;
	}

	private Node getNodeFromName(String name, HashMap<String, Node> nameToNodeMap) {
		if (!nameToNodeMap.containsKey(name)) {
			nameToNodeMap.put(name, new Node(name));
		}
		return nameToNodeMap.get(name);
	}

	private void formatInput(List<String> inputs) {
		startingNodes = new ArrayList<>();
		HashMap<String, Node> nameToNodeMap = new HashMap<>();

		path = inputs.getFirst().toCharArray();

		for (int index = 2; index < inputs.size(); index++) {
			String input = inputs.get(index);

			Node curNode = getNodeFromName(input.substring(0, 3), nameToNodeMap);

			if (curNode.value.endsWith("A")) {
				this.startingNodes.add(curNode);
			} else if (curNode.value.endsWith("Z")) {
				curNode.isDestination = true;
			}

			curNode.left = getNodeFromName(input.substring(7, 10), nameToNodeMap);
			curNode.right = getNodeFromName(input.substring(12, 15), nameToNodeMap);
		}
	}

	class Node {
		String value;

		Node right;
		Node left;

		public boolean isDestination;

		public Node(String value) {
			this.value = value;
			isDestination = false;
		}

		public Node(String value, Node right, Node left) {
			this.value = value;
			this.right = right;
			this.left = left;
			isDestination = false;
		}

		public String toString() {
			return value;
		}
	}
}
