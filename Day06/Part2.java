package Day06;

import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {

	public static void main(String[] args) {
		long total = 1l;

		String[] original = readFile("./input.txt", "\n");
		long time = getNumber(original[0]);
		long distance = getNumber(original[1]);
		total = getWinningRaces(time, distance);

		System.out.println(total);
	}

	public static long getNumber(String line) {
		String number = "";
		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i)))
				number += line.charAt(i);
		}
		return Long.parseLong(number);
	}

	public static long getWinningRaces(long time, long distance) {
		long winnings = 0;
		for (long i = 0; i < time; i++) {
			if ((time - i) * i > distance)
				winnings++;
		}
		System.out.println(winnings);
		return winnings;
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
