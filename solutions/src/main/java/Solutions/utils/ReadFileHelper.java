package Solutions.utils;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileHelper {

	private BufferedReader reader;

	public ReadFileHelper(String fileName) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(fileName)));
	}

	public List<String> getAllLines() {
		return reader.lines().toList();
	}

	public List<char[]> getAllLinesAsCharArray() {
		return reader.lines().map(line -> line.toCharArray()).toList();
	}
}
