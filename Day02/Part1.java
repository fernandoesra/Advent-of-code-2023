package Day02;

import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {

	public static int[] cubesOnPossibleGame = { 12, 13, 14 };		// red, green, blue

	public static void main(String[] args) throws Exception {
		String content = Files.readString(Path.of("./input.txt"));
		String[] linesArray = content.split("\n");
		int total = 0;
		for (int i = 0; i < linesArray.length; i++) {
			if (isPossible(linesArray[i])) {
				total += (i + 1);
			}
		}
		System.out.println(total);
	}

	public static boolean isPossible(String actualGame) {
		boolean toReturn = true;
		String[] separatedInput = actualGame.split(":");
		String setOfGames = separatedInput[1];
		String[] separatedGames = setOfGames.split(";");
		for (int i = 0; i < separatedGames.length; i++) {
			int[] cubesOnActualGame = { 0, 0, 0 };
			String[] actualSection = separatedGames[i].split(",");
			for (int j = 0; j < actualSection.length; j++) {
				String clean = actualSection[j].substring(1);
				String[] numberAndColor = clean.split("\\s");
				int number = Integer.parseInt(numberAndColor[0]);
				String color = numberAndColor[1];
				switch (color) {
				case "red":
					cubesOnActualGame[0] += number;
					break;
				case "green":
					cubesOnActualGame[1] += number;
					break;
				case "blue":
					cubesOnActualGame[2] += number;
					break;
				default:
					break;
				}
			}
			int red = cubesOnPossibleGame[0] - cubesOnActualGame[0];
			int green = cubesOnPossibleGame[1] - cubesOnActualGame[1];
			int blue = cubesOnPossibleGame[2] - cubesOnActualGame[2];
			if (red < 0 || green < 0 || blue < 0) {
				toReturn = false;
			}
		}
		return toReturn;
	}

}
