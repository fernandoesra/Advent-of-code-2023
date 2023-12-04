package Day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Part1 {

	public static void main(String[] args) {
		long total = 0l;

		String[] lines = readFile("./input.txt", "\n");
		int[][] winningAndHave = new int[0][0];
		for (int i = 0; i < lines.length; i++) {
			String[] separatedNumbers = lines[i].split("\\|");
			int[] winning = separateNumbers(separatedNumbers[0].split(":")[1]);
			int[] have = separateNumbers(separatedNumbers[1]);
			winningAndHave = addGame(winningAndHave, winning, have);
		}

		for (int i = 1; i < winningAndHave.length; i += 2) {
			total += checkWorth(winningAndHave[i - 1], winningAndHave[i]);
		}

		System.out.println(total);
	}

	public static int checkWorth(int[] wining, int[] have) {
		int totalWorth = 0;
		for (int i = 0; i < have.length; i++) {
			if (contains(wining, have[i])) {
				if (totalWorth == 0) {
					totalWorth++;
				} else {
					totalWorth = totalWorth * 2;
				}
			}
		}
		return totalWorth;
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
