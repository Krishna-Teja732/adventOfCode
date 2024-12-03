package Solutions;

import Solutions.utils.ReadFileHelper;
import Solutions.aoc24.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/2024/day3.txt").getAllLines();
		System.out.println(new BigDecimal(new Day3(new ArrayList<>(inputs)).getResultPart2()).toPlainString());
	}
}
