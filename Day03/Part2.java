package Day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Part2 {

	public static void main(String[] args) {
		long total = 0l;
		String[] lines = readFile("\n");
		int height = lines.length;
		int width = lines[0].length();
		char[][] schematicMap = new char[height][width];

		for (int i = 0; i < lines.length; i++) {
			String actualLine = lines[i];
			for (int j = 0; j < actualLine.length(); j++) {
				schematicMap[i][j] = actualLine.charAt(j);
			}
		}

		printMap(schematicMap);

		for (int i = 0; i < schematicMap.length; i++) {
			for (int j = 0; j < schematicMap[0].length; j++) {
				if (isGear(schematicMap, i, j)) {
					long newGear = gearRatio(schematicMap, i, j);
					System.out.println(newGear);
					total += newGear;
				}
			}
		}

		System.out.println(total);
		
	}

	public static long gearRatio(char[][] schematicMap, int height, int width) {
		long totalGearRatio = 0l;
		int[] partNumbers = new int[0];
		int[][] coordinates = { { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 }, { 0, +1 }, { +1, -1 }, { +1, 0 },
				{ +1, +1 } };

		for (int i = 0; i < coordinates.length; i++) {
			int actualY = height + coordinates[i][0];
			int actualX = width + coordinates[i][1];
			if (inBounds(schematicMap, actualY, actualX)) {
				char actualCharacter = schematicMap[actualY][actualX];
				if (Character.isDigit(actualCharacter)) {
					String start = readStart(schematicMap, actualY, actualX);
					String end = readEnd(schematicMap, actualY, actualX);
					String actualNumber = start + "" + end;
					int newNumber = Integer.parseInt(actualNumber);
					partNumbers = addPart(partNumbers,newNumber);
				}
			}
		}
		if (partNumbers.length == 2) {
			totalGearRatio = partNumbers[0] * partNumbers[1];
		}		
		return totalGearRatio;
	}

	public static int[] addPart(int[] partNumbers, int newPart) {
		boolean yetInList = false;
		for (int i = 0; i < partNumbers.length; i++) {
			if (partNumbers[i] == newPart)
				yetInList = true;
		}
		if (!yetInList) {
			partNumbers = Arrays.copyOf(partNumbers, partNumbers.length + 1);
			partNumbers[partNumbers.length - 1] = newPart;
		}
		return partNumbers;
	}

	public static boolean isGear(char[][] schematicMap, int height, int width) {
		boolean isGear = false;
		if (inBounds(schematicMap, height, width)) {
			if (schematicMap[height][width] == '*')
				isGear = true;
		}
		return isGear;
	}

	public static String readEnd(char[][] schematicMap, int i, int j) {
		String end = "";
		while (Character.isDigit(schematicMap[i][j])) {
			end += schematicMap[i][j];
			if (inBounds(schematicMap, i, j + 1))
				j++;
			else {
				break;
			}
		}
		return end;
	}

	public static String readStart(char[][] schematicMap, int i, int j) {
		String start = "";
		String original = "";
		while (Character.isDigit(schematicMap[i][j])) {
			original += schematicMap[i][j];
			if (inBounds(schematicMap, i, j - 1)) {
				j--;
			} else {
				break;
			}
		}
		for (int k = original.length() - 1; k > 0; k--) {
			start += original.charAt(k);
		}
		return start;
	}

	public static boolean inBounds(char[][] schematicMap, int height, int width) {
		boolean inBounds = true;
		int realHeight = schematicMap.length;
		int realWidth = schematicMap[0].length;
		if ((height < 0 || height >= realHeight) || (width < 0 || width >= realWidth))
			inBounds = false;
		return inBounds;
	}

	public static void printMap(char[][] schematicMap) {
		for (int i = 0; i < schematicMap.length; i++) {
			for (int j = 0; j < schematicMap[0].length; j++) {
				System.out.print(schematicMap[i][j]);
			}
			System.out.println();
		}
	}

	public static String[] readFile(String splitChara) {
		String[] linesArray = null;
		try {
			String content = Files.readString(Path.of("./input.txt"));
			content = content.replace("\r", "");
			linesArray = content.split(splitChara);
		} catch (Exception e) {
		}
		return linesArray;
	}

}
