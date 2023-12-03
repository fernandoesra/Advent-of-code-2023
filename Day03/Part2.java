package Day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Part2 {

	public static void main(String[] args) {
		long total = 0l;
		String[] lines = readFile("./input.txt", "\n");
		char[][] schematicMap = createSchematic(lines);
		for (int i = 0; i < schematicMap.length; i++) {
			for (int j = 0; j < schematicMap[0].length; j++) {
				if (isGear(schematicMap, i, j)) {
					long newGear = gearRatio(schematicMap, i, j);
					total += newGear;
				}
			}
		}
		System.out.println(total);

	}

	public static int getPartNumber(char[][] schematicMap, int mapY, int mapX) {
		String actualNumber = readBeginning(schematicMap, mapY, mapX) + readEnd(schematicMap, mapY, mapX);
		return Integer.parseInt(actualNumber);
	}

	public static long gearRatio(char[][] schematicMap, int height, int width) {
		long totalGearRatio = 0l;
		int[] partsSerials = new int[0];
		int[][] coordinates = { { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 }, { 0, +1 }, { +1, -1 }, { +1, 0 },
				{ +1, +1 } };
		for (int i = 0; i < coordinates.length; i++) {
			int actualY = height + coordinates[i][0];
			int actualX = width + coordinates[i][1];
			if (inBounds(schematicMap, actualY, actualX)) {
				if (Character.isDigit(schematicMap[actualY][actualX]))
					partsSerials = addPart(partsSerials, getPartNumber(schematicMap, actualY, actualX));
			}
		}
		if (partsSerials.length == 2) {
			totalGearRatio = partsSerials[0] * partsSerials[1];
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

	public static boolean isGear(char[][] schematicMap, int mapY, int mapX) {
		boolean isGear = false;
		if (inBounds(schematicMap, mapY, mapX)) {
			if (schematicMap[mapY][mapX] == '*')
				isGear = true;
		}
		return isGear;
	}

	public static String readEnd(char[][] schematicMap, int mapY, int mapX) {
		String end = "";
		while (Character.isDigit(schematicMap[mapY][mapX])) {
			end += schematicMap[mapY][mapX];
			if (inBounds(schematicMap, mapY, mapX + 1))
				mapX++;
			else {
				break;
			}
		}
		return end;
	}

	public static String readBeginning(char[][] schematicMap, int mapY, int mapX) {
		String reverse = "";
		String original = "";
		while (Character.isDigit(schematicMap[mapY][mapX])) {
			original += schematicMap[mapY][mapX];
			if (inBounds(schematicMap, mapY, mapX - 1)) {
				mapX--;
			} else {
				break;
			}
		}
		for (int i = original.length() - 1; i > 0; i--) {
			reverse += original.charAt(i);
		}
		return reverse;
	}

	public static boolean inBounds(char[][] schematicMap, int mapY, int mapX) {
		boolean inBounds = true;
		int realHeight = schematicMap.length;
		int realWidth = schematicMap[0].length;
		if ((mapY < 0 || mapY >= realHeight) || (mapX < 0 || mapX >= realWidth))
			inBounds = false;
		return inBounds;
	}

	public static String[] readFile(String path, String splitChara) {
		String[] linesArray = null;
		try {
			String content = Files.readString(Path.of(path));
			content = content.replace("\r", "");
			linesArray = content.split(splitChara);
		} catch (Exception e) {
		}
		return linesArray;
	}

	public static char[][] createSchematic(String[] lines) {
		int height = lines.length;
		int width = lines[0].length();
		char[][] schematicMap = new char[height][width];
		for (int i = 0; i < lines.length; i++) {
			String actualLine = lines[i];
			for (int j = 0; j < actualLine.length(); j++) {
				schematicMap[i][j] = actualLine.charAt(j);
			}
		}
		return schematicMap;
	}

}
