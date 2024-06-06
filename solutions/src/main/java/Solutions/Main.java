package Solutions;

import Solutions.utils.ReadFileHelper;

import java.io.IOException;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/day7.txt").getAllLines();
		System.out.println(new Day7(inputs).getTotalValue());
	}
}
