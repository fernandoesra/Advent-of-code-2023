package Day03;

import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {

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
		
		for (int i = 0; i < schematicMap.length; i++) {
			for (int j = 0; j < schematicMap[0].length; j++) {
				if (isPartNumber(schematicMap, i, j)) {
					String actualNumber = "";
					String start = readBeginning(schematicMap, i, j);
					String end = readEnd(schematicMap, i, j);
					actualNumber = start + "" + end;
					int realNumber = Integer.parseInt(actualNumber);
					total += realNumber;
					j += end.length();
				}
			}
		}

		System.out.println(total);
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

	public static boolean isPartNumber(char[][] schematicMap, int height, int width) {
		boolean isPart = false;
		if (inBounds(schematicMap, height, width)) {
			if (Character.isDigit(schematicMap[height][width])) {
				int[][] coordinates = { { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 }, { 0, +1 }, { +1, -1 }, { +1, 0 },
						{ +1, +1 } };
				for (int i = 0; i < coordinates.length; i++) {
					if (isIndicator(schematicMap, height + coordinates[i][0], width + coordinates[i][1])) {
						isPart = true;
						break;
					}
				}
			}
		}
		return isPart;
	}

	public static boolean isIndicator(char[][] schematicMap, int height, int width) {
		boolean isIndicator = false;
		if (inBounds(schematicMap, height, width)) {
			char actualCharacter = schematicMap[height][width];
			if (!Character.isDigit(actualCharacter) && actualCharacter != '.')
				isIndicator = true;
		}
		return isIndicator;
	}

	public static boolean inBounds(char[][] schematicMap, int height, int width) {
		boolean inBounds = true;
		int realHeight = schematicMap.length;
		int realWidth = schematicMap[0].length;
		if ((height < 0 || height >= realHeight) || (width < 0 || width >= realWidth))
			inBounds = false;
		return inBounds;
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
