package Solutions.utils;

import java.util.Objects;

public class Pair<U, V> {
	public U key;
	public V value;

	public Pair(U key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair<?, ?>) {
			if (((Pair<?, ?>) obj).key.equals(this.key) && ((Pair<?, ?>) obj).value.equals(this.value)) {
				return true;
			}
		}
		return false;
	}
}
