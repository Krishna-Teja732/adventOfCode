package Solutions.utils;

public enum Direction {
	NORTH(-1, 0),
	EAST(0, 1),
	SOUTH(1, 0),
	WEST(0, -1),
	NORTH_EAST(-1, 1),
	NORTH_WEST(-1, -1),
	SOUTH_EAST(1, 1),
	SOUTH_WEST(1, -1);

	public final Coordinate unitVector;
	public final Coordinate zeroVector;

	private Direction(int x, int y) {
		this.unitVector = new Coordinate(x, y);
		this.zeroVector = new Coordinate(0, 0);
	}

	public static Direction getDirectionFromVector(Coordinate vector) {
		if (vector.x == 0) {
			if (vector.y > 0) {
				return EAST;
			}
			return WEST;
		}
		if (vector.y == 0) {
			if (vector.x > 0) {
				return SOUTH;
			}
			return NORTH;
		}
		if (vector.x == 1) {
			if (vector.y > 0) {
				return SOUTH_EAST;
			}
			return SOUTH_WEST;
		}
		if (vector.x == -1) {
			if (vector.y > 0) {
				return NORTH_EAST;
			}
			return NORTH_WEST;
		}
		return null;
	}

}
