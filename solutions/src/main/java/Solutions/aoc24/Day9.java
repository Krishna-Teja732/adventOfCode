package Solutions.aoc24;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
	private ArrayList<Integer> fileIndex;
	private int maxFileID;

	public Day9(List<String> input) {
		char[] line = input.getFirst().toCharArray();
		this.fileIndex = new ArrayList<>();

		int startIndex = 0;
		this.maxFileID = -1;
		for (int index = 0; index < line.length; index++) {
			int endIndex = startIndex + line[index] - '0';
			int fillValue = -1;
			if (index % 2 == 0) {
				this.maxFileID++;
				fillValue = this.maxFileID;
			}
			for (int count = startIndex; count < endIndex; count++) {
				fileIndex.add(fillValue);
			}
			startIndex = startIndex + line[index] - '0';
		}
	}

	public long getResult() {
		long result = 0;
		compact();
		for (int index = 0; index < fileIndex.size(); index++) {
			if (fileIndex.get(index) == -1) {
				break;
			}
			result = result + fileIndex.get(index) * index;
		}
		return result;
	}

	private void compact() {
		int end = fileIndex.size() - 1;
		int start = 0;

		while (end > start) {
			while (end > start && fileIndex.get(end) == -1) {
				end--;
			}
			while (start < end && fileIndex.get(start) != -1) {
				start++;
			}
			if (end <= start) {
				break;
			}

			fileIndex.set(start, fileIndex.get(end));
			fileIndex.set(end, -1);
			start++;
			end--;
		}
	}

	public long getResultPart2() {
		// Compaction
		int currentFileID = this.maxFileID;
		int fileStartIndex = 0;
		int fileEndIndex = this.fileIndex.size();
		while (currentFileID > 0) {
			// Find the end index of the file
			while (this.fileIndex.get(fileEndIndex - 1) != currentFileID) {
				fileEndIndex--;
			}

			// Find the start index of the file
			fileStartIndex = fileEndIndex - 1;
			while (fileStartIndex - 1 > -1 && this.fileIndex.get(fileStartIndex - 1) == currentFileID) {
				fileStartIndex--;
			}

			compactV2(currentFileID, fileStartIndex, fileEndIndex);
			currentFileID--;
		}

		// Compute check sum
		long result = 0;
		for (int index = 0; index < fileIndex.size(); index++) {
			if (fileIndex.get(index) == -1) {
				continue;
			}
			result = result + fileIndex.get(index) * index;
		}
		return result;
	}

	private void compactV2(int fileID, int fileStartIndex, int fileEndIndex) {
		int fileLength = fileEndIndex - fileStartIndex;
		int spaceStartIndex = 0;
		int spaceLength = 0;
		// Find the leftmost space that is large enough to fit file
		while (spaceLength < fileLength && spaceStartIndex < fileStartIndex) {
			// find space starting from this index
			spaceStartIndex = spaceStartIndex + spaceLength;
			while (this.fileIndex.get(spaceStartIndex) != -1) {
				spaceStartIndex++;
			}
			spaceLength = 1;
			while (spaceStartIndex + spaceLength < this.fileIndex.size()
					&& this.fileIndex.get(spaceStartIndex + spaceLength) == -1) {
				spaceLength++;
			}
		}

		// No space found, do nothing
		if (spaceStartIndex > fileStartIndex) {
			return;
		}

		// Move file to empty space
		for (int index = spaceStartIndex; index < spaceStartIndex + fileLength; index++) {
			this.fileIndex.set(index, fileID);
		}
		for (int index = fileStartIndex; index < fileEndIndex; index++) {
			this.fileIndex.set(index, -1);
		}

	}

}
