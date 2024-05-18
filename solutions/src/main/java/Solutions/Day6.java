package Solutions;

import java.util.List;

class Day6 {

	public Day6() {
	}

	private int getScoreForGame(int maxTime, int maxDistance) {
		int result = 0;

		int timeLeft = maxTime;
		for (int buttonPressTime = 0; buttonPressTime <= maxTime; buttonPressTime++, timeLeft--) {
			if (buttonPressTime * timeLeft > maxDistance) {
				result++;
			}
		}

		return result;
	}

	public long getProductOfPossibilities(List<Integer> time, List<Integer> distances) {
		long result = 1;

		for (int index = 0; index < time.size(); index++) {
			result *= getScoreForGame(time.get(index), distances.get(index));
		}

		return result;
	}

	// For part 2 use a calculator to solve the quadratic
	// - t^2 - maxtime*t + distance = 0
	// Let t1, t2 be the zeros of the quadratic equation, where t1 > t2
	// the result will be ceil(t1) - ceil(t2)
}
