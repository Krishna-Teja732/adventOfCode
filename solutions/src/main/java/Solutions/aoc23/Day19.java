package Solutions.aoc23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.Arrays;

public class Day19 {

	List<Part> parts;
	HashMap<String, Workflow> workflows;

	public Day19(List<String> inputs) {
		parts = new ArrayList<>();
		workflows = new HashMap<>();

		int index = 0;
		while (index < inputs.size() && !inputs.get(index).isBlank()) {
			parseMapping(inputs.get(index++));
		}
		index++;
		while (index < inputs.size()) {
			parsePart(inputs.get(index++));
		}
	}

	public double getResult() {
		double result = 0;
		for (Part part : parts) {
			result = result + checkIfPartAccepted(part);
		}
		return result;
	}

	private double checkIfPartAccepted(Part part) {
		String workflowName = "in";
		while (!workflowName.equals("A") && !workflowName.equals("R")) {
			workflowName = workflows.get(workflowName).getNextWorkflow(part);
		}
		return workflowName.equals("A") ? part.getSumOfRatings() : 0.0;
	}

	private void parseMapping(String strWorkflow) {
		int seperatorIndex = strWorkflow.indexOf("{");
		String workflowName = strWorkflow.substring(0, seperatorIndex);
		List<Rule> rules = new ArrayList<>();

		strWorkflow = strWorkflow.substring(seperatorIndex + 1, strWorkflow.length() - 1);
		for (String stringRule : strWorkflow.split(",")) {
			if (!stringRule.contains(":")) {
				rules.add(new Rule(stringRule));
				continue;
			}
			String attribute = String.valueOf(stringRule.charAt(0));
			char operator = stringRule.charAt(1);
			String nextWorkflowName = stringRule.substring(stringRule.indexOf(":") + 1);
			Double ratingThreshold = Double.parseDouble(stringRule.substring(2, stringRule.indexOf(":")));

			switch (operator) {
				case '>' -> {
					rules.add(new Rule(nextWorkflowName,
							(part) -> part.getRating(attribute) > ratingThreshold));
				}
				case '<' -> {
					rules.add(new Rule(nextWorkflowName,
							(part) -> part.getRating(attribute) < ratingThreshold));
				}
			}
		}

		workflows.put(workflowName, new Workflow(rules));
	}

	private void parsePart(String part) {
		part = part.substring(1, part.length() - 1);
		double[] ratings = Arrays.stream(part.split(","))
				.mapToDouble((strRating) -> Double.parseDouble(strRating.substring(2)))
				.toArray();
		parts.add(new Part(ratings));
	}

	private class Part {
		private HashMap<String, Double> ratings;

		private Double sumOfRatings;

		public Part(double[] partRatings) {
			this.ratings = new HashMap<>();
			sumOfRatings = 0.0;

			ratings.put("x", partRatings[0]);
			ratings.put("m", partRatings[1]);
			ratings.put("a", partRatings[2]);
			ratings.put("s", partRatings[3]);

			for (Double rating : partRatings) {
				sumOfRatings = sumOfRatings + rating;
			}
		}

		public Double getSumOfRatings() {
			return this.sumOfRatings;
		}

		public Double getRating(String attribute) {
			return this.ratings.get(attribute);
		}

		@Override
		public String toString() {
			return ratings.toString();
		}
	}

	private class Rule {
		public String nextWorkflow;
		public Function<Part, Boolean> condition;

		public Rule(String nextWorkflow, Function<Part, Boolean> condition) {
			this.nextWorkflow = nextWorkflow;
			this.condition = condition;
		}

		public Rule(String nextWorkflow) {
			this.nextWorkflow = nextWorkflow;
		}

		public Optional<String> applyRule(Part part) {
			if (condition == null || condition.apply(part)) {
				return Optional.of(this.nextWorkflow);
			}
			return Optional.empty();
		}

		@Override
		public String toString() {
			return nextWorkflow;
		}
	}

	private class Workflow {
		private List<Rule> rules;

		public Workflow(List<Rule> rules) {
			this.rules = rules;
		}

		public String getNextWorkflow(Part part) {
			for (Rule rule : this.rules) {
				Optional<String> result = rule.applyRule(part);
				if (result.isPresent()) {
					return result.get();
				}
			}
			throw new NullPointerException("The part did not match to any rules");
		}

		@Override
		public String toString() {
			return rules.toString();
		}
	}

}
