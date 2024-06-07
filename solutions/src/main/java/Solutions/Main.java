package Solutions;

import Solutions.utils.ReadFileHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/day9.txt").getAllLines();
		System.out.println(new BigDecimal(new Day9(inputs).getSumOfAllNthTerms()).toPlainString());
	}
}
