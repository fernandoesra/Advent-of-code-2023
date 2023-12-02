package Day02;

import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {

	public static void main(String[] args) throws Exception {
		String content = Files.readString(Path.of("./src/Day02/input.txt"));
		content = content.replace("\r", "");
		content = content.replace("\n", "@");
		String[] linesArray = content.split("@");
		long total = 0l;
		for (int i = 0; i < linesArray.length; i++) {
			total += powerOfGame(linesArray[i]);
		}
		System.out.println(total);
	}

	public static long powerOfGame(String actualGame) {
		
		int[] each = { 0, 0, 0 };		// red, green, blue
		
		String[] separatedInput = actualGame.split(":");
		String setOfGames = separatedInput[1];
		String[] separatedGames = setOfGames.split(";");
		for (int i = 0; i < separatedGames.length; i++) {
			String[] actualSection = separatedGames[i].split(",");
			for (int j = 0; j < actualSection.length; j++) {
				String clean = actualSection[j].substring(1);
				String[] numberAndColor = clean.split("\\s");
				int number = Integer.parseInt(numberAndColor[0]);
				String color = numberAndColor[1];
				switch (color) {
				case "red":
					if (number > each[0])
						each[0] = number;
					break;
				case "green":
					if (number > each[1])
						each[1] = number;
					break;
				case "blue":
					if (number > each[2])
						each[2] = number;
					break;
				default:
					break;
				}
			}
		}
		long toReturn = each[0] * each[1] * each[2];
		return toReturn;
	}

}
