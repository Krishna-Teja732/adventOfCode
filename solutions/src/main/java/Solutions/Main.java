package Solutions;

import Solutions.utils.ReadFileHelper;
import Solutions.utils.FormatInputHelper;

import java.io.IOException;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/day6.txt").getAllLines();
		System.out.println(new Day6().getProductOfPossibilities(
				FormatInputHelper.extractNumbersFromString(inputs.getFirst()),
				FormatInputHelper.extractNumbersFromString(inputs.getLast())));
	}
}
