package Solutions.utils;

/**
 * Coordinate
 */
public class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getManhattanDistance(Coordinate coord) {
		return Math.abs(this.x - coord.x) + Math.abs(this.y - coord.y);
	}
}
