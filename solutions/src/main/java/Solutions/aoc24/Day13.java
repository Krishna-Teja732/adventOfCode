package Solutions.aoc24;

import java.util.List;
import java.util.ArrayList;

import Solutions.utils.FormatInputHelper;
import Solutions.utils.Pair;

public class Day13 {

	ArrayList<Pair<Line, Line>> linearEquations;

	public Day13(List<String> inputs) {
		linearEquations = new ArrayList<>();

		int index = 0;

		while (index < inputs.size()) {
			List<Long> coef1 = FormatInputHelper.extractNumbersFromStringALongs(inputs.get(index++));
			List<Long> coef2 = FormatInputHelper.extractNumbersFromStringALongs(inputs.get(index++));
			List<Long> rhs = FormatInputHelper.extractNumbersFromStringALongs(inputs.get(index++));
			index++;

			Line line1 = new Line(coef1.getFirst(), coef1.getLast(), rhs.getFirst());
			Line line2 = new Line(coef2.getFirst(), coef2.getLast(), rhs.getLast());

			linearEquations.add(new Pair<>(line1, line2));
		}
	}

	public long getResult() {
		long result = 0;

		for (Pair<Line, Line> eqs : linearEquations) {
			Line l1 = eqs.key;
			Line l2 = eqs.value;

			long numerator = ((l1.c * l2.b) - (l2.a * l2.c));
			long denominator = ((l1.a * l2.b) - (l2.a * l1.b));
			// Equationcannot be solved
			if (numerator % denominator != 0) {
				continue;
			}
			long countA_ButtonPress = numerator / denominator;

			long countB_ButtonPress = (l2.c - countA_ButtonPress * l1.b);
			if (countB_ButtonPress % l2.b != 0) {
				continue;
			}
			countB_ButtonPress = countB_ButtonPress / l2.b;

			// Condition givien in question
			if (countB_ButtonPress > 100 || countA_ButtonPress > 100 || countA_ButtonPress < 0
					|| countB_ButtonPress < 0) {
				continue;
			}
			result = result + 3 * countA_ButtonPress + countB_ButtonPress;
		}

		return result;
	}

	record Line(Long a, Long b, Long c) {
	}
}
