package Day06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	public static void main(String[] args) {
		long total = 1l;

		String[] original = readFile("./input.txt", "\n");

		int manyRuns = 4;
		int[] time = getNumbers(original[0], manyRuns);
		int[] distance = getNumbers(original[1], manyRuns);

		for (int i = 0; i < manyRuns; i++) {
			total *= getWinningRaces(time[i], distance[i]);
		}

		System.out.println(total);
	}

	public static int getWinningRaces(int time, int distance) {
		int winnings = 0;
		for (int i = 0; i < time; i++) {
			if ((time - i) * i > distance)
				winnings++;
		}
		System.out.println(winnings);
		return winnings;
	}

	public static int[] getNumbers(String line, int length) {
		String regex = "\\b\\d+\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		int[] numbers = new int[length];
		int index = 0;
		while (matcher.find() && index < length) {
			numbers[index] = Integer.parseInt(matcher.group());
			index++;
		}
		return numbers;
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
