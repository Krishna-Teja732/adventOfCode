package Solutions.utils;

import java.util.Objects;

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

	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}

	public int getManhattanDistance(Coordinate c) {
		return Math.abs(this.x - c.x) + Math.abs(this.y - c.y);
	}

	public Coordinate add(Coordinate c) {
		return new Coordinate(this.x + c.x, this.y + c.y);
	}

	public Coordinate add(int x, int y) {
		return new Coordinate(this.x + x, this.y + y);
	}

	public String toString() {
		return this.x + " " + this.y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			return this.x == ((Coordinate) obj).x && this.y == ((Coordinate) obj).y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
