package Solutions;

import Solutions.utils.ReadFileHelper;
import Solutions.aoc23.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/day14example.txt").getAllLines();
		System.out.println(new BigDecimal(new Day14(inputs).getResult()).toPlainString());
	}
}
