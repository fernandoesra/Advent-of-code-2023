package Day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import Utils.ExecutedTime;

public class Part2 {

	public static void main(String[] args) {
		long total = 0l;
		String[] lines = readFile("./input.txt", "\n");
		int[][] winningAndHave = new int[0][0];
		int totalGames = 0;

		for (int i = 0; i < lines.length; i++, totalGames++) {
			String[] separatedNumbers = lines[i].split("\\|");
			int[] winning = separateNumbers(separatedNumbers[0].split(":")[1]);
			int[] have = separateNumbers(separatedNumbers[1]);
			winningAndHave = addGame(winningAndHave, winning, have);
		}

		int[] totalCards = new int[totalGames];
		Arrays.fill(totalCards, 1);

		for (int i = 1, j = 0; i < winningAndHave.length; i += 2, j++) {
			int actualWins = checkWins(winningAndHave[i - 1], winningAndHave[i]);
			totalCards = addCards(totalCards, j, actualWins);
		}
		for (int i = 0; i < totalCards.length; i++) {
			total += totalCards[i];
		}

		System.out.println(total);
	}

	public static int[] addCards(int[] totalCards, int actualCard, int toAdd) {
		int manyCards = totalCards[actualCard];
		for (int i = actualCard, j = 0; i < totalCards.length && j < toAdd; i++, j++) {
			totalCards[i + 1] += manyCards;
		}
		return totalCards;
	}

	public static int checkWins(int[] wining, int[] have) {
		int totalWins = 0;
		for (int i = 0; i < have.length; i++) {
			if (contains(wining, have[i]))
				totalWins++;
		}
		return totalWins;
	}

	public static boolean contains(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value)
				return true;
		}
		return false;
	}

	public static int[] separateNumbers(String numbers) {
		numbers = numbers.trim();
		numbers = numbers.replace(" ", "_");
		numbers = numbers.replace("__", "_");
		String[] separated = numbers.split("_");
		int[] separatedNumbers = new int[separated.length];
		for (int i = 0; i < separated.length; i++) {
			separated[i] = separated[i].trim();
			separatedNumbers[i] = Integer.valueOf(separated[i]);
		}
		return separatedNumbers;
	}

	public static int[][] addGame(int[][] actualRegister, int[] wining, int[] have) {
		actualRegister = Arrays.copyOf(actualRegister, actualRegister.length + 1);
		actualRegister = Arrays.copyOf(actualRegister, actualRegister.length + 1);
		actualRegister[actualRegister.length - 2] = wining;
		actualRegister[actualRegister.length - 1] = have;
		return actualRegister;
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

}
