package Solutions.aoc24;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Day3 {
	private ArrayList<String> input;

	public Day3(ArrayList<String> input) {
		this.input = input;
	}

	public int getResult() {
		int result = 0;

		Pattern mulOp = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

		for (String line : input) {
			Matcher program = mulOp.matcher(line);
			int index = 0;
			while (!program.hitEnd() && program.find(index)) {
				index = program.end();
				result += Integer.parseInt(program.group(1)) * Integer.parseInt(program.group(2));
			}
		}

		return result;
	}

	public int getResultPart2() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < input.size(); index++) {
			builder.append(input.get(index));
		}

		int startInd = builder.indexOf("don't()");
		while (startInd != -1) {
			int endInd = builder.indexOf("do()", startInd + 1);
			if (endInd == -1) {
				endInd = builder.length();
			}

			builder.replace(startInd, endInd, "");
			startInd = builder.indexOf("don't()");
		}

		this.input.clear();
		this.input.add(builder.toString());

		return getResult();
	}
}
