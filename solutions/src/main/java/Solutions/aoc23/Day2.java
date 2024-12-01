package Solutions.aoc23;

import java.util.ArrayList;
import java.util.List;

class Day2 {

	int[] maxRGB;

	public Day2(int[] maxRGB) {
		this.maxRGB = maxRGB;
	}

	public boolean isGamePossible(int[] rgb) {
		for (int index = 0; index < 3; index++) {
			if (maxRGB[index] < rgb[index]) {
				return false;
			}
		}
		return true;
	}

	public int totalPossibleGames(List<List<int[]>> games) {
		int result = 0;

		for (int index = 0; index < games.size(); index++) {
			boolean gameIsPossible = true;
			for (int[] rgb : games.get(index)) {
				gameIsPossible = gameIsPossible && isGamePossible(rgb);
				if (!gameIsPossible) {
					break;
				}
			}
			if (gameIsPossible) {
				result = result + index + 1;
			}
		}

		return result;
	}

	public double totalPossibleGamesII(List<List<int[]>> games) {
		double result = 0;
		for (int index = 0; index < games.size(); index++) {
			int[] curRGBvalues = new int[3];
			for (int[] rgb : games.get(index)) {
				for (int color = 0; color < 3; color++) {
					curRGBvalues[color] = Math.max(curRGBvalues[color], rgb[color]);
				}
			}
			result = result + curRGBvalues[0] * curRGBvalues[1] * curRGBvalues[2];

		}

		return result;
	}

	public List<int[]> formatLine(String line) {
		List<int[]> result = new ArrayList<>();
		String[] rounds = line.substring(line.indexOf(":") + 1).split(";");
		for (String round : rounds) {
			int[] rgb = new int[3];
			for (String turn : round.split(",")) {
				turn = turn.strip();
				switch (turn.substring(turn.length() - 3, turn.length())) {
					case "red" ->
						rgb[0] = Integer.parseInt(turn.substring(0, turn.length() - 3).strip());
					case "een" ->
						rgb[1] = Integer.parseInt(turn.substring(0, turn.length() - 5).strip());
					case "lue" ->
						rgb[2] = Integer.parseInt(turn.substring(0, turn.length() - 4).strip());
				}
			}
			result.add(rgb);
		}
		return result;
	}

	public List<List<int[]>> formatInput(List<String> inputLines) {
		List<List<int[]>> result = new ArrayList<>();
		for (String line : inputLines) {
			result.add(formatLine(line));
		}
		return result;
	}

}
