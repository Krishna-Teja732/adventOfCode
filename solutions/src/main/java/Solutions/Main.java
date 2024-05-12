package Solutions;

import Solutions.utils.ReadFileHelper;

import java.io.IOException;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<char[]> inputs = new ReadFileHelper("inputs/day3.txt").getAllLinesAsCharArray();
		System.out.println(new Day3().sumOfAllGears(inputs));
	}
}
