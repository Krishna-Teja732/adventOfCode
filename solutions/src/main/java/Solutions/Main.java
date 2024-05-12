package Solutions;

import Solutions.utils.ReadFileHelper;

import java.io.IOException;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		List<String> inputs = new ReadFileHelper("inputs/day2.txt").getAllLines();
		Day2 obj = new Day2(new int[] { 12, 13, 14 });
		System.out.println(obj.totalPossibleGamesII(obj.formatInput(inputs)));
	}
}
